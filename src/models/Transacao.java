package models;

import java.time.LocalDateTime;

public class Transacao {
    private String id;
    private TipoTransacao tipo;
    private Conta contaDestino;
    private Conta contaOrigem;
    private double valor;
    private LocalDateTime dataHora;
    private String descricao;

    public Transacao(TipoTransacao tipo, double valor, Conta contaOrigem) {
        this.tipo = tipo;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
    }

    public Transacao(TipoTransacao tipo, double valor, Conta contaOrigem, Conta contaDestino) {
        this.tipo = tipo;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
    }

    public String getId() {
        return id;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {return descricao;}

    public Conta getContaDestino() {
        return contaDestino;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id='" + id + '\'' +
                ", tipo=" + tipo +
                ", valor=" + valor +
                ", dataHora=" + dataHora +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    private String gerarId() {
            int idNumero = Integer.parseInt(this.id);
            idNumero++;
            this.id = String.valueOf(idNumero);

            return this.id;
        }

    private String gerarDescricao() {
        switch (this.tipo) {
            case SAQUE -> {
                return "Saque de" + getValor();
            }
            case DEPOSITO -> {
                return "Deposito de" + getValor();
            }
            case TRANSFERENCIA_DEBITO -> {
                return "Transferencia enviada de R$ " + getValor() + " para " + getContaDestino();
            }
            case TRANSFERENCIA_CREDITO -> {
                return "Transferencia recebiida de R$ " + getValor() + " de " + getContaOrigem();
            }
        }
        return "";
    }

    }


