package model;

import java.io.Serializable;

public class Produto implements Serializable, IObjeto{
    
    private int id, estoque, quant;
    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    private String nome, desc; 
    private float preco;
    
    public Produto(int id, int estoque, String nome, String desc, float preco) {
        this.id = id;
        this.estoque = estoque;
        this.nome = nome;
        this.desc = desc;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", estoque=" + estoque  +
         ", nome=" + nome + ", desc=" + desc + ", preco=" + preco + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String listarObjeto() {
        return "Produto = " + nome + "estoque = " + estoque;
    }

}