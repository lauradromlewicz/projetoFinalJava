package controller;

import factory.ProdutoFactory;
import model.Produto;
import util.Logger;
import util.Serializacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ControllerCogumelos implements Serializable {

    private List<Produto> produtos = new ArrayList<>();

    public ControllerCogumelos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public ControllerCogumelos() {
    }

    public Optional<Produto> buscarProduto(String nome) {
        return produtos.stream().filter(p -> p.getNome().equalsIgnoreCase(nome)).findFirst();
    }

    public Optional<Produto> buscarProduto(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst();
    }

    public String adicionarCogumelo(int quant, String nome, String desc, float preco) throws Exception {
        try {
            if (buscarProduto(nome).isPresent()) {
                throw new Exception("O cogumelo já existe");
            }
            Produto prod = ProdutoFactory.criarProduto(produtos, quant, nome, desc, preco);
            produtos.add(prod);
            Logger.salvar("O produto " + prod.getNome() + " foi adicionado", "Log");
            salvarDados();
            return "O cogumelo foi adicionado com sucesso \n";
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Argumentos inválidos: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Erro ao adicionar cogumelo: " + e.getMessage());
        }
    }

    public String removerCogumelo(int id) throws Exception {
        try {
            Optional<Produto> produtoOptional = buscarProduto(id);
            if (!produtoOptional.isPresent()) {
                throw new NullPointerException("Produto não encontrado para o ID: " + id);
            }
            Produto produto = produtoOptional.get();
            Logger.salvar("O produto " + produto.getNome() + " foi removido", "Log");
            produtos.remove(produto);
            salvarDados();
            return "O produto foi removido";
        } catch (Exception e) {
            throw new Exception("Erro ao remover cogumelo: " + e.getMessage());
        }
    }

    public String listarCogumelo() {
        if (produtos == null || produtos.isEmpty()) {
            return "Não há produtos salvos";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Produto produto : produtos) {
                sb.append("_____________________________\n")
                  .append("Id: ").append(produto.getId()).append("\n")
                  .append("Nome: ").append(produto.getNome()).append("\n")
                  .append("Descrição: ").append(produto.getDesc()).append("\n")
                  .append("Preco: R$").append(produto.getPreco()).append("\n")
                  .append("Estoque: ").append(produto.getEstoque()).append(" unidades\n");
            }
            return sb.toString();
        }
    }

    public String listarCogumeloSemQuant() {
        if (produtos == null || produtos.isEmpty()) {
            return "Não há produtos salvos";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Produto produto : produtos) {
                sb.append("_____________________________\n")
                  .append("Id: ").append(produto.getId()).append("\n")
                  .append("Nome: ").append(produto.getNome()).append("\n")
                  .append("Descrição: ").append(produto.getDesc()).append("\n")
                  .append("Preco: R$").append(produto.getPreco()).append("\n");
            }
            return sb.toString();
        }
    }

    public String diminuirEstoque(int id, int estoque) throws Exception {
        try {
            Optional<Produto> produtoOptional = buscarProduto(id);
            if (!produtoOptional.isPresent()) {
                return "O produto não existe\n";
            }
            Produto produto = produtoOptional.get();
            if (produto.getEstoque() > 0) {
                if (produto.getEstoque() < estoque) {
                    return "Não é possível diminuir a quantidade, o estoque é muito pequeno\n";
                } else {
                    produto.setEstoque(produto.getEstoque() - estoque);
                    Logger.salvar("O Produto " + produto.getNome() + " foi diminuido em " + estoque + " unidades", "Log");
                    salvarDados();
                    return "O estoque foi diminuido com sucesso \n";
                }
            } else {
                return "O estoque está vazio\n";
            }
        } catch (Exception e) {
            throw new Exception("Erro ao diminuir estoque: " + e.getMessage());
        }
    }

    public String aumentarEstoque(int id, int estoque) throws Exception {
        try {
            Optional<Produto> produtoOptional = buscarProduto(id);
            if (!produtoOptional.isPresent()) {
                return "O produto não existe\n";
            }
            Produto produto = produtoOptional.get();
            produto.setEstoque(produto.getEstoque() + estoque);
            Logger.salvar("O Produto " + produto.getNome() + " foi aumentado em " + estoque + " unidades", "Log");
            salvarDados();
            return "O estoque foi aumentado com sucesso\n";
        } catch (Exception e) {
            throw new Exception("Erro ao aumentar estoque: " + e.getMessage());
        }
    }

    public String alterarCogumeloValor(int id, float valor) throws Exception {
        try {
            Optional<Produto> produtoOptional = buscarProduto(id);
            if (!produtoOptional.isPresent()) {
                return "O produto não existe\n";
            }
            Produto produto = produtoOptional.get();
            produto.setPreco(valor);
            Logger.salvar("O valor do Produto " + produto.getNome() + " foi alterado", "Log");
            salvarDados();
            return "O valor foi alterado \n";
        } catch (Exception e) {
            throw new Exception("Erro ao alterar valor: " + e.getMessage());
        }
    }

    public String alterarCogumeloDesc(int id, String desc) throws Exception {
        try {
            Optional<Produto> produtoOptional = buscarProduto(id);
            if (!produtoOptional.isPresent()) {
                return "O produto não existe\n";
            }
            Produto produto = produtoOptional.get();
            produto.setDesc(desc);
            Logger.salvar("A descrição do Produto " + produto.getNome() + " foi alterada", "Log");
            salvarDados();
            return "A descrição foi alterada \n";
        } catch (Exception e) {
            throw new Exception("Erro ao alterar descrição: " + e.getMessage());
        }
    }

    public String alterarCogumeloNome(int id, String nome) throws Exception {
        try {
            Optional<Produto> produtoOptional = buscarProduto(id);
            if (!produtoOptional.isPresent()) {
                return "O produto não existe\n";
            }
            Produto produto = produtoOptional.get();
            Logger.salvar("O nome do Produto " + produto.getNome() + " foi alterado para " + nome, "Log");
            produto.setNome(nome);
            salvarDados();
            return "O nome foi alterado \n";
        } catch (Exception e) {
            throw new Exception("Erro ao alterar nome: " + e.getMessage());
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "ControllerCogumelos [produtos=" + produtos + "]";
    }

    public void salvarDados() throws Exception {
        Serializacao.salvarProduto(produtos);
    }
}
