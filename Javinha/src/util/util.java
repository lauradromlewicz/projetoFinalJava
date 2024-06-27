package util;
import java.util.List;
import java.util.Scanner;

import model.Carrinho;
import model.Cliente;
import model.Produto;

public class util {
    static Scanner scanner = new Scanner(System.in);


    public static int gerarIdCliente(List<Cliente> clientes) {
        return clientes.stream().mapToInt(Cliente::getId).max().orElse(0)+1;
    }

    public static int gerarIdProduto(List<Produto> produtos) {
        return produtos.stream().mapToInt(Produto::getId).max().orElse(0)+1;
    }

    public static int gerarIdCarrinho(List<Carrinho> carrinhos) {
        return carrinhos.stream().mapToInt(Carrinho::getId).max().orElse(0)+1;
    }

    public static boolean validarEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public static int lerOpcao(int min, int max) {
        int opcao = 0;
        boolean entradaValida = false;
    
        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                if (opcao >= min && opcao <= max) {
                    entradaValida = true;
                } else {
                    System.out.printf("Opção inválida! Por favor, digite um número entre %d e %d.\n", min, max);
                }
            } else {
                System.out.printf("Opção inválida! Por favor, digite um número entre %d e %d.\n", min, max);
                scanner.next();
            }
        }
        scanner.nextLine();
        return opcao;
    }
    
    public static String lerStringNaoVazio(String mensagem) {
        String valor = "";
        while (valor.isEmpty()) {
            System.out.print(mensagem + ": ");
            valor = scanner.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("Valor não pode estar vazio!");
            }
        }
        return valor;
    }

    public static int lerInteiro(String mensagem) {
        int valor = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            if (!entrada.isEmpty()) {
                try {
                    valor = Integer.parseInt(entrada);
                    valido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido! Digite um número inteiro válido.");
                }
            } else {
                System.out.println("Valor não pode estar vazio! Digite um número inteiro válido.");
            }
        }

        return valor;
    }

    public static float lerFloat(String mensagem) {
        float valor = 0.0f;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            if (!entrada.isEmpty()) {
                try {
                    valor = Float.parseFloat(entrada);
                    valido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido! Digite um número float válido.");
                }
            } else {
                System.out.println("Valor não pode estar vazio! Digite um número float válido.");
            }
        }

        return valor;
    }
}
