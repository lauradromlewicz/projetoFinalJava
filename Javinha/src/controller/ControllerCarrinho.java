package controller;

import java.util.List;
import java.util.Optional;
import model.Carrinho;
import model.Cliente;
import model.Produto;
import util.Logger;
import util.Serializacao;

public class ControllerCarrinho {
    private List<Carrinho> carrinhos;
    private ControllerCogumelos controllerCogu;
    private ControllerCliente controllerCli;

    public ControllerCarrinho(List<Carrinho> carrinhos, ControllerCogumelos controllerCogu, ControllerCliente controllerCli) {
        this.carrinhos = carrinhos;
        this.controllerCogu = controllerCogu;
        this.controllerCli = controllerCli;
    }

    public Carrinho buscarCarrinho(int id) {
        return carrinhos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public Produto buscarProdutoCarrinho(Carrinho carrinho, int id) {
        return carrinho.getCogumelos().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public Carrinho buscarCarrinho(String nome) {
        return carrinhos.stream().filter(c -> c.getCli().getNome().equals(nome)).findFirst().orElse(null);
    }

    public float calcularValorTotal(Carrinho carrinho) {
        float total = 0;
        for (Produto item : carrinho.getCogumelos()) {
            total += item.getPreco() * item.getQuant();
        }
        return total;
    }

    public String adicionarCarrinho(int id, int idCli) throws Exception {
        try {
            Optional<Cliente> clienteOptional = controllerCli.buscarCliente(idCli);

            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                Carrinho car = new Carrinho(id, cliente, 0);
                carrinhos.add(car);

                Logger.salvar("O carrinho do cliente " + car.getCli().getNome() + " foi criado", "LogCarrinhos");
                salvarDados();
                return "Carrinho criado com sucesso";
            } else {
                return "Cliente não existe";
            }
        } catch (Exception e) {
            throw new Exception("Erro ao adicionar carrinho: " + e.getMessage());
        }
    }

    public String listarCarrinhos() {

        if (carrinhos.isEmpty()) {
            return "Não há carrinhos salvos";
        }
        try {
            for (Carrinho c : carrinhos) {
                System.out.println("----------------------------- \n" + "Id: " + c.getId());
                System.out.println("Nome do cliente: " + c.getNomeCliente());
                for (Produto cogu : c.getCogumelos()) {
                    System.out.println("ID: " + cogu.getId() + " Produto: " + cogu.getNome() + 
                        " - Quantidade: " + cogu.getQuant() + "\nValor Unitário: R$" + cogu.getPreco() +
                        " - Valor: R$" + cogu.getPreco() * cogu.getQuant());
                }
                c.setValorTotal(calcularValorTotal(c));
                System.out.println("Valor Total: R$ " + c.getValorTotal() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar carrinhos: " + e.getMessage());
        }
        return "------------------------------";
    }

    public String removerCarrinho(int idCarrinho) throws Exception {
        try {
            Carrinho carr = buscarCarrinho(idCarrinho);
            if (carr == null) {
                throw new NullPointerException("Não foi possível remover o carrinho, carrinho não existe");
            }
            carrinhos.remove(carr);
            salvarDados();
            Logger.salvar("O carrinho do cliente " + carr.getNomeCliente() + " foi removido", "LogCarrinhos");
            return "O carrinho foi excluído com sucesso";
        } catch (Exception e) {
            throw new Exception("Erro ao remover carrinho: " + e.getMessage());
        }
    }

    public String adicionarProduto(int idProduto, int idCarrinho, int quantidade) throws Exception {
        try {
            Optional<Produto> produtoOptional = controllerCogu.buscarProduto(idProduto);
            if (!produtoOptional.isPresent()) {
                return "O id não pertence a um produto";
            }
            Produto cogumelo = produtoOptional.get();
            if (cogumelo.getEstoque() < quantidade) {
                return "O produto esta indisponível ou estoque está baixo";    
            }
            
            Carrinho car = buscarCarrinho(idCarrinho);
            if (car == null) {
                return "O cliente não possui um carrinho";
            }
            cogumelo.setEstoque(cogumelo.getEstoque() - quantidade);
            cogumelo.setQuant(quantidade);
            car.getCogumelos().add(cogumelo);
            car.setValorTotal(calcularValorTotal(car));
                Logger.salvar("O " + cogumelo.getNome() + " foi adicionado ao carrinho do cliente " + car.getNomeCliente(), "LogCarrinhos");
                Logger.salvar("O estoque do " + cogumelo.getNome() + " foi diminuido em " + cogumelo.getQuant() + " - Novo Estoque: " + cogumelo.getEstoque() , "Log");
            salvarDados();
            return "O produto foi adicionado ao carrinho \n";
        } catch (Exception e) {
            throw new Exception("Erro ao adicionar produto ao carrinho: " + e.getMessage());
        }
    }

    public String removerProduto(int idProduto, int idCarrinho) throws Exception {
        try {
            Carrinho car = buscarCarrinho(idCarrinho);
            if (car == null) {
                return "O cliente não possui um carrinho";
            }
            Produto cogumelo = buscarProdutoCarrinho(car, idProduto);
            if (cogumelo == null) {
                return "O id não pertence a um produto";
            }

            for (Produto produto : controllerCogu.getProdutos()) {
                if (produto.getId() == idProduto) {
                    produto.setEstoque(produto.getEstoque() + produto.getQuant());
                }
            }
            car.getCogumelos().remove(cogumelo);
            car.setValorTotal(calcularValorTotal(car));
            Logger.salvar("O " + cogumelo.getNome() + " foi removido do carrinho do cliente " + car.getNomeCliente(), "LogCarrinhos");
            Logger.salvar("O estoque do " + cogumelo.getNome() + " foi aumentado em " + cogumelo.getQuant() + " - Novo Estoque: " + cogumelo.getEstoque() , "Log");
            salvarDados();
            return "O produto foi removido do carrinho \n";
        } catch (Exception e) {
            throw new Exception("Erro ao remover produto do carrinho: " + e.getMessage());
        }
    }

    public List<Carrinho> getCarrinhos() {
        return carrinhos;
    }

    public void setCarrinhos(List<Carrinho> carrinhos) {
        this.carrinhos = carrinhos;
    }

    public ControllerCogumelos getControllerCogu() {
        return controllerCogu;
    }

    public void setControllerCogu(ControllerCogumelos controllerCogu) {
        this.controllerCogu = controllerCogu;
    }

    public ControllerCliente getControllerCli() {
        return controllerCli;
    }

    public void setControllerCli(ControllerCliente controllerCli) {
        this.controllerCli = controllerCli;
    }

    public void salvarDados() throws Exception {
        try {
            Serializacao.salvarCarrinho(carrinhos);
        } catch (Exception e) {
            throw new Exception("Erro ao salvar dados do carrinho: " + e.getMessage());
        }
    }
}
