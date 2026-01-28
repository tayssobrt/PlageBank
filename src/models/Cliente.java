package models;

public class Cliente {
    private String nome;
    private String cpf;
    private String senha;
    private int tentativas;
    private boolean bloqueado;

    public Cliente() {

    }

    public Cliente(String nome, String cpf, String senha, int tentativas, boolean bloqueado) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.tentativas = tentativas;
        this.bloqueado = bloqueado;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }



}
