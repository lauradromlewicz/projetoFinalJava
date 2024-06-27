package controller;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import model.Carrinho;
import model.Produto;
import model.Dono;
import model.IObjeto;
import util.Logger;
import util.Serializacao;

public class Controller implements Serializable, IObjeto {
    private ControllerCarrinho controllerCarrinho;
    private Dono dono;
    private String nome;
    private Scanner sc = new Scanner(System.in);

    public Controller(ControllerCarrinho contCarrinho, Dono dono, String nome) {
        this.controllerCarrinho = contCarrinho;
        this.nome = nome;
        this.dono = dono;
    }
    
    public String efetuarVenda(Carrinho carrinho, int id, List<Produto> produtos) throws Exception {
        try {
            carrinho.setValorTotal(controllerCarrinho.calcularValorTotal(carrinho));
            dono.setDinheiro(dono.getDinheiro() + carrinho.getValorTotal());
            salvarDono();
            controllerCarrinho.getCarrinhos().remove(carrinho);
            salvarCarrinho();
            Logger.salvar("Venda Efetuada - Cliente: " + carrinho.getNomeCliente() + " - Valor Total: R$" + carrinho.getValorTotal(), "LogVendas");
            for (Produto prod : carrinho.getCogumelos()) {
                Logger.salvar("Cogumelos Vendidos: " + prod.getNome() + " - Quantidade vendida: " + prod.getQuant() + " -  Novo estoque: " + prod.getEstoque(), "LogVendas");
            }
            return "Venda efetuada com sucesso\n";
        } catch (Exception e) {
            throw new Exception("Erro ao efetuar venda: " + e.getMessage());
        }
    }

    public void listarVendas() {
        try {
            List<String> vendas = Logger.ler("LogVendas");
            for (String venda : vendas) {
                System.out.println(venda);
            }
            System.out.println("_________________________");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de log não encontrado");
        } catch (Exception e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }
    }

    public void salvarDono() throws Exception {
        try {
            Serializacao.salvarDono(dono);
        } catch (Exception e) {
            throw new Exception("Erro ao salvar dados do dono: " + e.getMessage());
        }
    }

    public void salvarCarrinho() throws Exception {
        try {
            Serializacao.salvarCarrinho(controllerCarrinho.getCarrinhos());
        } catch (Exception e) {
            throw new Exception("Erro ao salvar dados do carrinho: " + e.getMessage());
        }
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public ControllerCarrinho getContCarrinho() {
        return controllerCarrinho;
    }

    public void setContCarrinho(ControllerCarrinho contCarrinho) {
        this.controllerCarrinho = contCarrinho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return "Controller [controllerCarrinho=" + controllerCarrinho + ", dono=" + dono + ", nome=" + nome + ", sc="
                + sc + "]";
    }

    @Override
    public String listarObjeto() {
        return "\nNome da Empresa: "+ nome + " - Dono: " + dono.getNome() +
                "\nClientes cadastrados: " + controllerCarrinho.getControllerCli().getClientes().size() +
                "\nCogumelos cadastrados: " + controllerCarrinho.getControllerCogu().getProdutos().size() +
                "\nDinheiro da empresa: R$" + dono.getDinheiro();
    }

}
