package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import model.Carrinho;
import model.Cliente;
import model.Dono;
import model.Produto;

public class Serializacao {
    private static final File PRODUTO = new File("src/obj/produto.ser");
    private static final File CLIENTE = new File("src/obj/cliente.ser");
    private static final File CARRINHO = new File("src/obj/carrinho.ser");
    private static final File DONO = new File("src/obj/dono.ser");

    public static void salvarCliente(List<Cliente> cli) throws Exception {
        CLIENTE.getParentFile().mkdirs();

        try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(CLIENTE))) {
            objOutput.writeObject(cli);
        } catch (IOException e) {
            throw new Exception("Não foi possível salvar o objeto: " + e.getMessage(), e);
        }
    }

    public static void salvarProduto(List<Produto> prod) throws Exception {
        PRODUTO.getParentFile().mkdirs();

        try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(PRODUTO))) {
            objOutput.writeObject(prod);
        } catch (IOException e) {
            throw new Exception("Não foi possível salvar o objeto: " + e.getMessage(), e);
        }
    }
        
    public static void salvarCarrinho(List<Carrinho> car) throws Exception {
        CARRINHO.getParentFile().mkdirs();

        try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(CARRINHO))) {
            objOutput.writeObject(car);
        } catch (IOException e) {
            throw new Exception("Não foi possível salvar o objeto: " + e.getMessage(), e);
        }
    }
    
    public static void salvarDono(Dono dono) throws Exception {
        DONO.getParentFile().mkdirs();

        try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(DONO))) {
            objOutput.writeObject(dono);
        } catch (IOException e) {
            throw new Exception("Não foi possível salvar o objeto: " + e.getMessage(), e);
        }
    }
    
    public static List<Carrinho> carregarCarrinho() throws Exception {
        if (!CARRINHO.exists()) {
            throw new Exception("O arquivo não existe.");
        }

        try (ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(CARRINHO))) {
            return (List<Carrinho>) objInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Não foi possível carregar o objeto: " + e.getMessage());
        }
    }

    public static List<Cliente> carregarClientes() throws Exception {
        if (!CLIENTE.exists()) {
            throw new Exception("O arquivo não existe.");
        }

        try (ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(CLIENTE))) {
            return (List<Cliente>) objInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Não foi possível carregar o objeto: " + e.getMessage(), e);
        }
    }

    public static Dono carregarDono() throws Exception {
        if (!DONO.exists()) {
            throw new Exception("O arquivo não existe.");
        }

        try (ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(DONO))) {
            return (Dono) objInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Não foi possível carregar o objeto: " + e.getMessage(), e);
        }
    }

    public static List<Produto> carregarProdutos() throws Exception {
        if (!PRODUTO.exists()) {
            throw new Exception("O arquivo não existe.");
        }

        try (ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(PRODUTO))) {
            return (List<Produto>) objInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Não foi possível carregar o objeto: " + e.getMessage(), e);
        }
    }
}