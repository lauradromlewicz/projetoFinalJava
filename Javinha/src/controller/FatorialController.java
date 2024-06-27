package controller;

import model.Fatorial;

public class FatorialController {

    public long obterFatorial(int numero) {
        return Fatorial.calcularFatorial(numero);
    }
}
