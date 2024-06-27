package model;

import java.io.Serializable;

public abstract class Pessoa implements Serializable{
    private String nome, tel;
    
    public Pessoa() {
    }

    public Pessoa(String nome, String tel) {
        this.nome = nome;
        this.tel = tel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Pessoa [nome=" + nome + ", tel=" + tel + "]";
    }

}
