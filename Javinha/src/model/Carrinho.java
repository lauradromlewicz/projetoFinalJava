package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrinho implements Serializable{

    private int id;

    private Cliente cli;
    private List<Produto> cogumelos;
    private float valorTotal;

    public Carrinho(int id, Cliente cli, float valorTotal) {
        this.id = id;
        this.cli = cli;
        this.cogumelos = new ArrayList<>();
        this.valorTotal = valorTotal;
    }

    public Carrinho(Cliente cli, List<Produto> produtos, float valorTotal) {
        this.cli = cli;
        this.cogumelos = produtos;
        this.valorTotal = valorTotal;
    }

    public float getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Carrinho [produtos=" + cogumelos + ", valorTotal=" + valorTotal + "]";
    }

    public Cliente getCli() {
        return cli;
    }

    public String getNomeCliente() {
        return cli.getNome();
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }
    public List<Produto> getCogumelos() {
        return cogumelos;
    }
    public void setCogumelos(List<Produto> cogumelos) {
        this.cogumelos = cogumelos;
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
