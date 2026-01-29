package models;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String string;
    private Double saldo;
    private StatusConta status;
    private Cliente titular;

    private List<Transacao> historico = new ArrayList<>();

    public Conta(String string, Double saldoInicial, Cliente titular) {
        this.string = string;
        this.saldo = saldoInicial;
        this.titular = titular;
    }

    private boolean validarValor(Double valor) {
        //valida os valores de saque
        if (valor == 0) {
            System.out.println("Você não pode sacar um valor nulo");
            return false;
        } else if (valor < 0) {
            System.out.println("Você não pode sacar um valor negativo");
            return false;
        } else {
            return true;
        }
    }

    private boolean validarSaldo(Double valor) {
        //valida se o user tem saldo suficiente para o saldo
        if (valor > saldo) {
            System.out.println("Você não tem saldo suficiente!");
            return false;
        } else {
            return true;
        }
    }



}
