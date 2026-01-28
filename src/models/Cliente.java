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

    public boolean autenticar(String senha) {
        //verifica se a senha possui algum caracther
        boolean temCaractere = senha.chars().anyMatch(c -> "$!#@%&*".indexOf(c) >= 0);
        //verifica se a senha tem algum numeral
        boolean temDigito = senha.chars().anyMatch(Character::isDigit);
        //verifica tamanho
        boolean temTamanho = senha.length() >= 8;

        //verifica as validações para liberar
        if (!temTamanho) {
            System.out.println("A senha não possui tamanho suficiente, Min: 8");
            return false;
        }

        if (!temDigito) {
            System.out.println("A senha não tem um dígito EX: 123");
            return false;
        }

        if (!temCaractere) {
            System.out.println("A senha não tem um caractere EX: !@#$");
            return false;
        }

        return true;
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
