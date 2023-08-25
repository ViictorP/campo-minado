package com.victor.cm.modelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTeste {

    private Campo campo;

    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoRealDistancia1() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia2() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia3() {
        Campo vizinho = new Campo(2, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);

        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.minar();
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();

        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1() {
        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);

        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2() {
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 2);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }

    @Test
    void testeGetLinha() {
        assertEquals(3, campo.getLinha());
    }

    @Test
    void testeGetColuna() {
        assertEquals(3, campo.getColuna());
    }

    @Test
    void testeObjetivoAlcancado() {
        Campo campo1 = new Campo(3, 3);
        campo.abrir();

        assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testeObjetivoAlcancadoFalse() {
        Campo campo1 = new Campo(3, 3);


        assertFalse(campo.objetivoAlcancado());
    }

    @Test
    void testeMinasNaVizinhanca() {
        Campo campo1 = new Campo(3, 2);
        campo.adicionarVizinho(campo1);

        campo1.minar();

        assertEquals(1, campo.minasNaVizinhanca());
    }

    @Test
    void testeMinasNaVizinhanca2() {
        Campo campo1 = new Campo(3, 2);
        Campo campo2 = new Campo(3, 4);
        campo.adicionarVizinho(campo1);
        campo.adicionarVizinho(campo2);

        campo1.minar();
        campo2.minar();

        assertEquals(2, campo.minasNaVizinhanca());
    }

    @Test
    void testeReiniciar() {
        campo.alternarMarcacao();
        campo.reiniciar();

        assertFalse(campo.isMarcado());
    }

    @Test
    void testeToString() {
        campo.alternarMarcacao();

        assertEquals("x", campo.toString());
    }

    @Test
    void testeToString2() {
        campo.abrir();
        campo.minar();

        assertEquals("*", campo.toString());
    }

    @Test
    void testeToString3() {
        Campo campo1 = new Campo(3, 2);
        Campo campo2 = new Campo(3, 4);
        campo.adicionarVizinho(campo1);
        campo.adicionarVizinho(campo2);
        campo1.minar();
        campo2.minar();
        campo.abrir();

        assertEquals("2", campo.toString());
    }

    @Test
    void testeToString4() {
        campo.abrir();

        assertEquals(" ", campo.toString());
    }

    @Test
    void testeToString5() {

        assertEquals("?", campo.toString());
    }
}
