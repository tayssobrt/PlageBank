package models;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String numero;
    private Double saldo;
    private StatusConta status;
    private Cliente titular;

    private List<Transacao> historico = new ArrayList<>();

    public Conta(String numero, Double saldoInicial, Cliente titular) {
        this.numero = numero;
        this.saldo = saldoInicial;
        this.titular = titular;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public StatusConta getStatus() {
        return status;
    }

    public void bloquear() {
        this.status = StatusConta.BLOQUEADA;
    }

    public void desbloquear() {
        this.status = StatusConta.ATIVA;
    }

    public boolean isAtiva() {
        if (this.status == StatusConta.BLOQUEADA) {
            return false;
        } else {
            return true;
        }
    }

    private boolean sacar(Double valor) {
       //Valida os metdos
       if (!validarSaldo(valor)|| !validarValor(valor) || !validarContaAtiva()) {
           System.out.println("O saque não pode ser efetuado");
           return false;
       }

           this.saldo -= valor;
           return true;
    }

    private boolean depositar(Double valor) {
        //Valida os metdos
        if (!validarContaAtiva()) {
            System.out.println("O saque não pode ser efetuado");
            return false;
        }

        this.saldo += valor;
        return true;
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

    private  boolean validarContaAtiva(){
        if (!isAtiva()) {
            return false;
        }

        return true;
    }

}
