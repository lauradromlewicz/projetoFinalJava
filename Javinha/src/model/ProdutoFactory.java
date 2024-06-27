package factory;

import model.Produto;
import util.util;

import java.util.List;

public class ProdutoFactory {

    public static Produto criarProduto(List<Produto> produtos, int quant, String nome, String desc, float preco) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        }
        if (desc == null || desc.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição do produto não pode ser vazia.");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("Preço do produto não pode ser negativo.");
        }
        if (quant < 0) {
            throw new IllegalArgumentException("Quantidade do produto não pode ser negativa.");
        }

        int id = util.gerarIdProduto(produtos);
        return new Produto(id, quant, nome, desc, preco);
    }
}
