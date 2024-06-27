package view;

import java.util.Scanner;
import controller.ControllerCogumelos;
import model.Carrinho;
import controller.ControllerCliente;
import controller.Controller;
import controller.ControllerCarrinho;
import util.util;
import controller.FatorialController;

public class View {
    private static Scanner scanner = new Scanner(System.in);
    private Controller controller;
    private ControllerCarrinho controllerCarrinho;
    private ControllerCliente controllerCliente;
    private ControllerCogumelos controllerCogumelos;
     private FatorialController fatorialController;

    public View(Controller controller, ControllerCarrinho controllerCarrinho, ControllerCliente controllerCliente,
            ControllerCogumelos controllerCogumelos, FatorialController fatorialController) {
        this.controller = controller;
        this.controllerCarrinho = controllerCarrinho;
        this.controllerCliente = controllerCliente;
        this.controllerCogumelos = controllerCogumelos;
        his.fatorialController = fatorialController;
    }

    public void menuPrincipal() {
        while (true) {
            try {
                System.out.println("Bem vindo a " + controller.getNome());
                System.out.println("1. Gerenciar Cogumelos");
                System.out.println("2. Gerenciar Clientes");
                System.out.println("3. Gerenciar Carrinhos");
                System.out.println("4. Efetuar Venda");
                System.out.println("5. Listar Vendas");
                System.out.println("6. Informação da Empresa");
                System.out.println("7. Calcular Fatorial");
                System.out.println("8. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = util.lerOpcao(1, 8);

                switch (opcao) {
                    case 1:
                        menuCogumelos();
                        break;
                    case 2:
                        menuClientes();
                        break;
                    case 3:
                        menuCarrinhos();
                        break;
                    case 4:
                        efetuarVenda();
                        break;
                    case 5:
                        listarVendas();
                        break;
                    case 6:
                        infoEmpresa();
                        System.out.println("__________________________");
                        break;
                        case 7:
                        calcularFatorial();
                        break;
                    case 8:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void calcularFatorial() {
        try {
            int numero = util.lerInteiro("Digite um número para calcular o fatorial: ");
            long resultado = fatorialController.obterFatorial(numero);
            exibirFatorial(numero, resultado);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void exibirFatorial(int numero, long resultado) {
        System.out.println("O fatorial de " + numero + " é " + resultado);
    }

    private void menuCogumelos() {
        while (true) {
            try {
                System.out.println("Gerenciamento de Cogumelos");
                System.out.println("1. Adicionar Cogumelo");
                System.out.println("2. Remover Cogumelo");
                System.out.println("3. Listar Cogumelos");
                System.out.println("4. Alterar Cogumelos");
                System.out.println("5. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");

                int opcao = util.lerOpcao(1, 5);

                switch (opcao) {
                    case 1:
                        adicionarCogumelo();
                        break;
                    case 2:
                        removerCogumelo();
                        break;
                    case 3:
                        listarCogumelos();
                        break;
                    case 4:
                        menuAlterarCogumelos();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void menuClientes() {
        while (true) {
            try {
                System.out.println("Gerenciamento de Clientes");
                System.out.println("1. Adicionar Cliente");
                System.out.println("2. Remover Cliente");
                System.out.println("3. Listar Clientes");
                System.out.println("4. Alterar Clientes");
                System.out.println("5. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");

                int opcao = util.lerOpcao(1, 5);

                switch (opcao) {
                    case 1:
                        adicionarCliente();
                        break;
                    case 2:
                        removerCliente();
                        break;
                    case 3:
                        listarClientes();
                        break;
                    case 4:
                        menuAlterarClientes();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void menuAlterarCogumelos() {
        while (true) {
            try {
                System.out.println("1. Alterar Nome");
                System.out.println("2. Alterar Descrição");
                System.out.println("3. Alterar Valor");
                System.out.println("4. Aumentar Estoque");
                System.out.println("5. Diminuir Estoque");
                System.out.println("6. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");

                int opcao = util.lerOpcao(1, 6);

                switch (opcao) {
                    case 1:
                        
                        int idProd1 = util.lerInteiro("Digite o id do produto que deseja alterar: ");
                        String nome = util.lerStringNaoVazio("Digite o novo nome: ");
    
                        System.out.println(controllerCogumelos.alterarCogumeloNome(idProd1,nome));
                        return;
                    case 2:
                        int idProd2 = util.lerInteiro("Digite o id do produto que deseja alterar: ");
                        String desc = util.lerStringNaoVazio("Digite a nova descrição: ");
    
                        System.out.println(controllerCogumelos.alterarCogumeloDesc(idProd2,desc));
                        return;
                    case 3:
                        int idProd3 = util.lerInteiro("Digite o id do produto que deseja alterar: ");
                        Float valor = util.lerFloat("Digite o novo valor: ");
    
                        System.out.println(controllerCogumelos.alterarCogumeloValor(idProd3, valor));
                        return;
                    case 4:
                        int idProd4 = util.lerInteiro("Digite o id do produto que deseja alterar: ");
                        int estoque = util.lerInteiro("Digite o estoque: ");
    
                        System.out.println(controllerCogumelos.aumentarEstoque(idProd4, estoque));
                        return;
                    case 5:
                        int idProd5 = util.lerInteiro("Digite o id do produto que deseja alterar: ");
                        int estoque2 = util.lerInteiro("Digite o estoque: ");
    
                        System.out.println(controllerCogumelos.diminuirEstoque(idProd5, estoque2));
                        return;
                    case 6:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void menuAlterarClientes() {
        while (true) {
            try {
                System.out.println("1. Alterar Nome");
                System.out.println("2. Alterar Email");
                System.out.println("3. Alterar Telefone");
                System.out.println("4. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");

                int opcao = util.lerOpcao(1, 4);

                switch (opcao) {
                    case 1:
                        int idCliente1 = util.lerInteiro("Digite o ID do cliente: ");
                        String nome = util.lerStringNaoVazio("Digite o novo nome do cliente: ");
    
                        controllerCliente.alterarClienteNome(idCliente1, nome);
                        return;
                    case 2:
                        int idCliente3 = util.lerInteiro("Digite o ID do cliente: ");
                        String email;
                        while (true) {
                            System.out.print("Email do Cliente: ");
                            email = scanner.nextLine().trim();
                            if (util.validarEmail(email)) {
                                break;
                            } else {
                                System.out.println("Email inválido! Certifique-se de que contém '@' e '.'.");
                            }
                        }
    
                        controllerCliente.alterarClienteEmail(idCliente3, email);
                        return;
                    case 3:
                        int idCliente4 = util.lerInteiro("Digite o ID do cliente: ");
                        String telefone = util.lerStringNaoVazio("Digite o novo telefone: ");
    
                        controllerCliente.alterarClienteTelefone(idCliente4, telefone);
                        return;
                    case 4:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void menuCarrinhos() {
        while (true) {
            try {
                System.out.println("Gerenciamento de Carrinhos");
                System.out.println("1. Novo Carrinho");
                System.out.println("2. Excluir Carrinho");
                System.out.println("3. Adicionar Produto ao Carrinho");
                System.out.println("4. Remover Produto do Carrinho");
                System.out.println("5. Listar Carrinhos");
                System.out.println("6. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");

                int opcao = util.lerOpcao(1, 6);

                switch (opcao) {
                    case 1:
                        novoCarrinho();
                        break;
                    case 2:
                        excluirCarrinho();
                        break;
                    case 3:
                        adicionarProdutoCarrinho();
                        break;
                    case 4:
                        removerProdutoCarrinho();
                        break;
                    case 5:
                        listarCarrinhos();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void adicionarCogumelo() {
        try {
            String nome = util.lerStringNaoVazio("Nome do Cogumelo:");
            String desc = util.lerStringNaoVazio("Descrição do Cogumelo");
            float preco = util.lerFloat("Preço do Cogumelo:");
            int quant = util.lerInteiro("Quantidade do Cogumelo:");

            String resultado = controllerCogumelos.adicionarCogumelo(quant, nome, desc, preco);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerCogumelo() {
        try {
            int id = util.lerInteiro("Id do Cogumelo a remover: ");
            controllerCogumelos.removerCogumelo(id);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarCogumelos() {
        try {
            System.out.println(controllerCogumelos.listarCogumelo());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void adicionarCliente() {
        try {
            String nome = util.lerStringNaoVazio("Nome do Cliente");
            String tel = util.lerStringNaoVazio("Telefone do Cliente");
            String email;
            while (true) {
                System.out.print("Email do Cliente: ");
                email = scanner.nextLine().trim();
                if (util.validarEmail(email)) {
                    break;
                } else {
                    System.out.println("Email inválido! Certifique-se de que contém '@' e '.'.");
                }
            }

            String resultado = controllerCliente.adicionarCliente(nome, tel, email, util.gerarIdCliente(controllerCliente.getClientes()));
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerCliente() {
        try {
            int idCliente = util.lerInteiro("Id do Cliente a remover: ");

            controllerCliente.removerCliente(idCliente);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarClientes() {
        try {
            System.out.println(controllerCliente.listarClientes());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarCarrinhos() {
        try {
            controllerCarrinho.listarCarrinhos();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void adicionarProdutoCarrinho() {
        System.out.println(controllerCogumelos.listarCogumeloSemQuant());
        try {
            int idProdu = util.lerInteiro("Id do produto: ");
            int idCarrinho = util.lerInteiro("Id do Carrinho: ");
            int quant = util.lerInteiro("Quantidade de Cogumelos: ");

            String resultado = controllerCarrinho.adicionarProduto(idProdu, idCarrinho, quant);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerProdutoCarrinho() {
        try {
            int idProdu = util.lerInteiro("Id do produto: ");
            int idCarrinho = util.lerInteiro("Id do Carrinho: ");

            String resultado = controllerCarrinho.removerProduto(idProdu, idCarrinho);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void novoCarrinho() {
        try {
            int idClienteAdd = util.lerInteiro("Digite o Id do Cliente: ");

            String resultado = controllerCarrinho.adicionarCarrinho(util.gerarIdCarrinho(controllerCarrinho.getCarrinhos()), idClienteAdd);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void excluirCarrinho() {
        try {
            int idCarrinhoRemove = util.lerInteiro("Digite o Id do carrinho: ");

            String resultado = controllerCarrinho.removerCarrinho(idCarrinhoRemove);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void efetuarVenda() {
        try {
            int idCarrinho = util.lerInteiro("Id do Carrinho: ");
            Carrinho carri = controllerCarrinho.buscarCarrinho(idCarrinho);

            System.out.println("Você tem certeza que deseja efetuar a venda?");
            System.out.println("[ 1 ] SIM");
            System.out.println("[ 2 ] NÃO");
            int opcao = util.lerInteiro("Decisão: ");

            switch (opcao) {
                case 1:
                    String resultado = controller.efetuarVenda(carri, idCarrinho, controllerCogumelos.getProdutos());
                    System.out.println(resultado);
                    break;
                case 2:
                    System.out.println("Venda cancelada, voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarVendas() {
        try {
            controller.listarVendas();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void infoEmpresa() {
        try {
            System.out.println(controller.listarObjeto());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void exibirFatorial(int numero, long resultado) {
        System.out.println("O fatorial de " + numero + " é " + resultado);
    }
}

