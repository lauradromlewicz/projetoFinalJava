package model;


public class Dono extends Pessoa implements IObjeto{

    private float dinheiro;

    public Dono(String nome, String tel, float dinheiro) {
        super(nome, tel);
        this.dinheiro = dinheiro;
    }

    @Override
    public String toString() {
        return "Dono [dinheiro=" + dinheiro + ", getDinheiro()=" + getDinheiro() + ", getNome()=" + getNome()
                + ", getTel()=" + getTel() + "]";
    }

    public float getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(float dinheiro) {
        this.dinheiro = dinheiro;
    }

    @Override
    public String listarObjeto() {
        return "Nome = " + super.getNome() + "Saldo" + dinheiro;
    }

    
}
