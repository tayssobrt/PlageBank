package models;

public class Cliente {
    private String nome;
    private String cpf;
    private String senha;
    private int tentativas;
    private boolean bloqueado;

    public Cliente(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.tentativas = 0;
        this.bloqueado = false;

        if (!validarCpf(cpf)) {
            throw new IllegalArgumentException("Cpf invalido!");
        }

        if (!validarSenha(senha)) {
            throw new IllegalArgumentException("Senha invalida!");
        }
    }


    public boolean validarSenha(String senha) {
        //verifica se a senha possui algum caracther
        boolean temCaractere = senha.chars().anyMatch(c -> "$!#@%&*".indexOf(c) >= 0);
        //verifica se a senha tem algum numeral
        boolean temDigito = senha.chars().anyMatch(Character::isDigit);
        //verifica tamanho
        boolean temTamanho = senha.length() >= 8;

        //verifica as validações para liberar
        if (!temTamanho) {
            System.out.println("A senha deve possuir 8 Caracteres");
            return false;
        }

        if (!temDigito) {
            System.out.println("A senha deve ter um numero EX: 123");
            return false;
        }

        if (!temCaractere) {
            System.out.println("A senha deve ter um caractere EX: !@#$");
            return false;
        }

        return true;
    }

    public boolean autenticar(String senha) {
        if (senha.equals(this.senha)) {
            return true;
        } else {
            System.out.println("SENHA INVALIDA");
            return false;
        }

    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getTentativas() {
        return tentativas;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void bloquear(Boolean bloqueado) {
        this.bloqueado = true;
    }

    public void resetarTentativas() {
        this.tentativas = 0;
    }

    public void incrementarTentativas() {
        this.tentativas = tentativas + 1;

        if (this.tentativas >= 3) {
            bloquear(bloqueado);
        }
    }

    public boolean validarCpf(String cpf) {
        boolean tamanho = cpf.length() == 11;

        if (!tamanho) {
            System.out.println("O cpf não tem um formato valido!");
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }

        int dv1 = 11 - (soma % 11);
        if (dv1 >= 10) dv1 = 0;

        soma = 0;

        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }

        int dv2 = 11 - (soma % 11);
        if (dv2 >= 10) dv2 = 0;

        return (cpf.charAt(9) - '0') == dv1
                && (cpf.charAt(10) - '0') == dv2;
    }
}
