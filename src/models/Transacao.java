package models;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transacao {
    private final String id;
    private final TipoTransacao tipo;
    private Conta contaDestino;
    private final Conta contaOrigem;
    private final double valor;
    private final LocalDateTime dataHora;
    private final String descricao;

    DecimalFormat df = new DecimalFormat("0.00");
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Transacao(TipoTransacao tipo, double valor, Conta contaOrigem) {
        this.tipo = tipo;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.id = gerarId();
        this.dataHora = LocalDateTime.now();
        this.descricao = gerarDescricao();
    }

    public Transacao(TipoTransacao tipo, double valor, Conta contaOrigem, Conta contaDestino) {
        this.tipo = tipo;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.id = gerarId();
        this.dataHora = LocalDateTime.now();
        this.descricao = gerarDescricao();
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
                "," + tipo +
                " de R$ " + df.format(valor) +
                ", dataHora=" + dataHora.format(fmt) +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    private String gerarId() {
       return UUID.randomUUID().toString().substring(0, 8);
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


