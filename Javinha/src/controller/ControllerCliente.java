package controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import model.Cliente;
import util.Logger;
import util.Serializacao;

public class ControllerCliente implements Serializable {

    private List<Cliente> clientes;

    public ControllerCliente(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ControllerCliente() {
    }

    public Optional<Cliente> buscarCliente(int id){
        return clientes.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Optional<Cliente> buscarCliente(String nome){
        return clientes.stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).findFirst();
    }


    public String adicionarCliente(String nome, String tel, String email, int id) throws Exception{
        try {
            Cliente cliente = new Cliente(nome, tel, email, id);
            Optional<Cliente> clienteExistente = buscarCliente(id);
        
            if(clienteExistente.isPresent()) {
                throw new Exception("O cliente já existe");
            } else {
                clientes.add(cliente);
                Logger.salvar("O Cliente " + cliente.getNome() + " foi adicionado", "Log");
                salvarDados();
                return "Cliente adicionado com sucesso";
            }
        } catch (Exception e) {
            throw new Exception("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    public String listarClientes(){
        try {
            if(clientes == null || clientes.isEmpty()){
                return "Não há clientes salvos \n";
            }      
    
            StringBuilder sb = new StringBuilder();
            for (Cliente cliente : clientes) {
                sb.append("_____________________________\n")
                  .append("Id: ").append(cliente.getId()).append("\n")
                  .append("Nome: ").append(cliente.getNome()).append("\n")
                  .append("Telefone: ").append(cliente.getTel()).append("\n")
                  .append("Email: ").append(cliente.getEmail()).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Erro ao listar clientes: " + e.getMessage() + "\n";
        }
    }

    public String removerCliente(int id) throws Exception{
        try {
            Optional<Cliente> clienteOptional = buscarCliente(id);
        
            if (!clienteOptional.isPresent()) {
                throw new NullPointerException("Cliente não encontrado para o ID: " + id);
            }
        
            Cliente cliente = clienteOptional.get();
            Logger.salvar("O Cliente " + cliente.getNome() + " foi removido", "Log");
            clientes.remove(cliente);
            salvarDados();
            
            return "Cliente removido com sucesso\n";
        } catch (Exception e) {
            throw new Exception("Erro ao remover cliente: " + e.getMessage());
        }
    }
    
    public String alterarClienteEmail(int id, String email) throws Exception {
        try {
            if (clientes == null || clientes.isEmpty()) {
                return "A lista de clientes está vazia.\n";
            }
    
            for (Cliente cliente : clientes) {
                if (cliente.getId() == id) {
                    cliente.setEmail(email);
                    Logger.salvar("O email do cliente " + cliente.getNome() + " foi alterado", "Log");
                    salvarDados();
                    return "O email foi alterado \n";
                }
            }
            return "O cliente não existe \n";
        } catch (Exception e) {
            throw new Exception("Erro ao alterar email: " + e.getMessage());
        }
    }

    public String alterarClienteNome(int id, String nome) throws Exception {
        try {
            Optional<Cliente> clienteOptional = buscarCliente(id);
        
            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                Logger.salvar("O Nome do cliente " + cliente.getNome() + " foi alterado para " + nome, "Log");
                cliente.setNome(nome);
                salvarDados();
                return "O nome foi alterado";
            } else {
                return "O cliente não existe";
            }
        } catch (Exception e) {
            throw new Exception("Erro ao alterar nome: " + e.getMessage());
        }
    }

    public String alterarClienteTelefone(int id, String tel) throws Exception {
        try {
            if (clientes == null || clientes.isEmpty()) {
                return "A lista de clientes está vazia.\n";
            }
    
            for (Cliente cliente : clientes) {
                if (cliente.getId() == id) {
                    cliente.setTel(tel);
                    Logger.salvar("O telefone do cliente " + cliente.getNome() + " foi alterado", "Log");
                    salvarDados();
                    return "O telefone foi alterado \n";
                }
            }
            return "O cliente não existe \n";
        } catch (Exception e) {
            throw new Exception("Erro ao alterar telefone: " + e.getMessage());
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "ControllerCliente [clientes=" + clientes + "]";
    }

    public void salvarDados() throws Exception{
        try {
            Serializacao.salvarCliente(clientes);
        } catch (Exception e) {
            throw new Exception("Erro ao salvar dados: " + e.getMessage());
        }
    }
}
