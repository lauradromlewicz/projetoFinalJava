package model;

public class Cliente extends Pessoa{
    private static final long serialVersionUID = 1L;
    private String email;
    private int id;

    public Cliente(String nome, String tel, String email, int id) {
        super(nome, tel);
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     @Override
    public String toString() {
        return "Cliente: " + super.toString() + "[email=" + email + "]";
    }

}
