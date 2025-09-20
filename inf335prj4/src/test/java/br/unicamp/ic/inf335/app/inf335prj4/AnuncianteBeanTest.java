package br.unicamp.ic.inf335.app.inf335prj4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AnuncianteBeanTest {

    private ProdutoBean produto(double valor) {
        return new ProdutoBean("C", "N", "D", valor, "Novo");
    }

    private AnuncioBean anuncio(double valorProduto, double desconto) {
        AnuncioBean a = new AnuncioBean();
        a.setProduto(produto(valorProduto));
        a.setDesconto(desconto);
        return a;
    }

    @Test
    void valorMedioAnuncios_listaVazia_retornaZero() {
        AnuncianteBean an = new AnuncianteBean();
        an.setNome("Fulano");
        an.setCPF("000.000.000-00");
        an.setAnuncios(new ArrayList<>());

        assertEquals(0.0, an.valorMedioAnuncios(), 1e-9);
    }

    @Test
    void valorMedioAnuncios_umAnuncio_mediaIgualAoProprioValor() {
        AnuncianteBean an = new AnuncianteBean();
        ArrayList<AnuncioBean> lista = new ArrayList<>();
        lista.add(anuncio(100.0, 0.0)); // valor = 100
        an.setAnuncios(lista);

        assertEquals(100.0, an.valorMedioAnuncios(), 1e-9);
    }

    @Test
    void valorMedioAnuncios_variosAnuncios_mediaCorreta() {
        // Anúncios: (200, 25%) => 150 ; (100, 0%) => 100 ; (50, 100%) => 0
        AnuncianteBean an = new AnuncianteBean();
        ArrayList<AnuncioBean> lista = new ArrayList<>();
        lista.add(anuncio(200.0, 0.25));
        lista.add(anuncio(100.0, 0.0));
        lista.add(anuncio(50.0, 1.0));
        an.setAnuncios(lista);

        double esperado = (150.0 + 100.0 + 0.0) / 3.0;
        assertEquals(esperado, an.valorMedioAnuncios(), 1e-9);
    }

    @Test
    void valorMedioAnuncios_atualizaQuandoListaMuda() {
        AnuncianteBean an = new AnuncianteBean();
        ArrayList<AnuncioBean> lista = new ArrayList<>();
        lista.add(anuncio(100.0, 0.0)); // 100
        lista.add(anuncio(300.0, 0.5)); // 150
        an.setAnuncios(lista);

        assertEquals(125.0, an.valorMedioAnuncios(), 1e-9);

        // Remove um anúncio e a média deve mudar
        lista.remove(1); // remove o de 150
        an.setAnuncios(lista);
        assertEquals(100.0, an.valorMedioAnuncios(), 1e-9);
    }
}
