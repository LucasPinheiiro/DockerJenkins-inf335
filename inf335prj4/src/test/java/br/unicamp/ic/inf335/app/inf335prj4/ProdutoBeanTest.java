package br.unicamp.ic.inf335.app.inf335prj4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProdutoBeanTest {

    @Test
    void testCompareToSmaller() {
        ProdutoBean p1 = new ProdutoBean("001", "Prod A", "Desc A", 100.0, "Novo");
        ProdutoBean p2 = new ProdutoBean("002", "Prod B", "Desc B", 200.0, "Usado");

        assertTrue(p1.compareTo(p2) < 0, "p1 should be cheaper than p2");
    }

    @Test
    void testCompareToEqual() {
        ProdutoBean p1 = new ProdutoBean("003", "Prod C", "Desc C", 150.0, "Novo");
        ProdutoBean p2 = new ProdutoBean("004", "Prod D", "Desc D", 150.0, "Novo");

        assertEquals(0, p1.compareTo(p2), "Products with same value should be equal");
    }
}
