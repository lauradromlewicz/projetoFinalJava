import java.util.ArrayList;
import java.util.List;

import model.*;
import controller.ControllerCogumelos;
import controller.ControllerCliente;
import controller.Controller;
import controller.ControllerCarrinho;
import util.Serializacao;
import view.View;

// Nicolas Gonçalves de Souza RGM: 33710031
// laura Christine Dromlewicz de Almeida RGM: 34262270
// Weslley Severino da Silve RGM: 33942773
// Carlos Eduardo Gonçalves de Souza RGM: 34249559


public class App {
    public static void main(String[] args) throws Exception {
        List<Produto> produtos;
        List<Cliente> clientes;
        List<Carrinho> carrinhos;
        Dono dono;

        try {
            produtos = Serializacao.carregarProdutos();
        } catch (Exception e) {
            produtos = new ArrayList<>();
            Serializacao.salvarProduto(produtos); 
        }

        try {
            clientes = Serializacao.carregarClientes();
        } catch (Exception e) {
            clientes = new ArrayList<>();
            Serializacao.salvarCliente(clientes);
        }

        try {
            carrinhos = Serializacao.carregarCarrinho();
        } catch (Exception e) {
            carrinhos = new ArrayList<>();
            Serializacao.salvarCarrinho(carrinhos);
        }

        try {
            dono = Serializacao.carregarDono();
        } catch (Exception e) {
            dono = new Dono("Wesslley Severino", "419929323", 20000);
            Serializacao.salvarDono(dono);
        }

        ControllerCliente controllerCliente = new ControllerCliente(clientes);
        ControllerCogumelos controllerCogumelos = new ControllerCogumelos(produtos);
        ControllerCarrinho controllerCarrinho = new ControllerCarrinho(carrinhos, controllerCogumelos, controllerCliente);
        Controller controller = new Controller(controllerCarrinho, dono, "Cogulândia");

        View view = new View(controller, controllerCarrinho, controllerCliente, controllerCogumelos);
        view.menuPrincipal();
    }
}
