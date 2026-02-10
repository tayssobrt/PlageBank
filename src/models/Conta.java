package models;
import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String numero;
    private Double saldo;
    private StatusConta status;
    private Cliente titular;

    private List<Transacao> historico = new ArrayList<>();

    public Conta(String numero, Cliente titular, Double saldoInicial) {
        this.numero = numero;
        this.saldo = saldoInicial;
        this.titular = titular;
        this.status = StatusConta.ATIVA;
        this.historico = new ArrayList<>();
    }

    public String getNomeTitular() {
        return titular.getNome();
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public List<Transacao> getHistorico() {
        return new ArrayList<>(historico);
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

    public boolean sacar(Double valor) {
       //Valida os metdos
       if (!validarSaldo(valor)|| !validarValor(valor) || !validarContaAtiva()) {
           System.out.println("O saque não pode ser efetuado");
           return false;
       }

           this.saldo -= valor;
       Transacao t = new Transacao(TipoTransacao.SAQUE, valor, this);
       adicionarTransacao(t);
           return true;
    }

    public boolean depositar(Double valor) {
        //Valida os metdos
        if (valor <= 0) {
            System.out.println("Você não pode depositar um valor negativo");
            return false;
        }

        Transacao t = new Transacao(TipoTransacao.DEPOSITO, valor, this);
        adicionarTransacao(t);
        this.saldo += valor;
        return true;
    }

    public void adicionarTransacao(Transacao transacao) {
        this.historico.add(transacao);
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

    public double consultarSaldo() {
        return this.saldo;
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

    private boolean validarContaAtiva(){
        if (!isAtiva()) {
            return false;
        }

        return true;
    }

    public boolean transferir(Conta contaDestino, Double valor) {
        if (!validarSaldo(valor) || !validarValor(valor) || !validarContaAtiva()) {
            return false;
        }
        
        if (contaDestino == null || !contaDestino.isAtiva()) {
            System.out.println("Conta destino inválida ou inativa");
            return false;
        }
        
        if (contaDestino.getNumero().equals(this.numero)) {
            System.out.println("Não pode transferir para a mesma conta");
            return false;
        }

        this.saldo -= valor;
        contaDestino.saldo += valor;

        Transacao transacaoDebito = new Transacao(TipoTransacao.TRANSFERENCIA_DEBITO, valor, this, contaDestino);
        Transacao transacaoCredito = new Transacao(TipoTransacao.TRANSFERENCIA_CREDITO, valor, this, contaDestino);

        this.adicionarTransacao(transacaoDebito);
        contaDestino.adicionarTransacao(transacaoCredito);

        return true;
    }


}
