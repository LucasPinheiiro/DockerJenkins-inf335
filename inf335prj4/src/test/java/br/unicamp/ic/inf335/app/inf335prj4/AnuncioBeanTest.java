package br.unicamp.ic.inf335.app.inf335prj4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AnuncioBeanTest {

    private ProdutoBean produto(double valor) {
        return new ProdutoBean("COD", "Nome", "Desc", valor, "Novo");
    }

    @Test
    void getValor_semDesconto_retornaValorDoProduto() {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(produto(100.0));
        a.setDesconto(0.0);

        assertEquals(100.0, a.getValor(), 1e-9);
    }

    @Test
    void getValor_comDesconto25_retornaValorComDescontoAplicado() {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(produto(200.0));
        a.setDesconto(0.25); // 25%

        assertEquals(150.0, a.getValor(), 1e-9);
    }

    @Test
    void getValor_desconto100_porcento_retornaZero() {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(produto(50.0));
        a.setDesconto(1.0); // 100%

        assertEquals(0.0, a.getValor(), 1e-9);
    }

    @Test
    void getValor_produtoTroca_refleteNovoPreco() {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(produto(120.0));
        a.setDesconto(0.10); // 10%

        assertEquals(108.0, a.getValor(), 1e-9);

        // Troca o produto e o cálculo deve acompanhar
        a.setProduto(produto(300.0));
        assertEquals(270.0, a.getValor(), 1e-9);
    }
}
