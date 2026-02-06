package view;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuTerminal {
    private Banco banco;
    private Scanner sc;
    private Cliente clientelogado;

    public MenuTerminal(Banco banco) {
        this.banco = banco;
        this.sc = new Scanner(System.in);
        this.clientelogado = null;
    }

    public void iniciar() {
        boolean sistemaRodando = true;

        while (sistemaRodando) {
            System.out.println("=== PLAGE BANK ===\n" +
                    "1. Criar novo cliente\n" +
                    "2. Fazer login\n" +
                    "3. Sair do sistema");
            System.out.println("Escolha: ");

            String escolha = sc.nextLine();
            switch (escolha) {
                case "1":
                    cadastro();
                    break;

                case "2":
                    login();
                    break;

                case "3":
                    System.out.println("Encerrando sistema...");
                    sistemaRodando = false;
                    break;

                default:
                    System.out.println("Opção invalida");
                    break;

            }
        }

        sc.close();

    }

    private void cadastro() {
        System.out.println("");
        System.out.println("  === PLAGE BANK ===");
        System.out.println("= Criar novo cliente =");
        System.out.println("");

        try {

            System.out.print("Nome completo: ");
            String nome = sc.nextLine();

            System.out.print("Cpf: ");
            String cpf = sc.nextLine();

            if (banco.buscarCliente(cpf) != null) {
                System.out.println("O cpf " + cpf + " já foi cadastrado");
                return;
            }

            System.out.print("Senha: ");
            String senha = sc.nextLine();

            Cliente cliente = banco.criarCliente(nome, cpf, senha);

            if (cliente != null) {
                System.out.println("Cadastro realizado com sucesso!");
                System.out.println("Use a opção 2 para fazer login.");
            } else {
                System.out.println("Erro ao criar novo cliente!");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar novo cliente");
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void login() {
        System.out.println("");
        System.out.println("=== PLAGE BANK ===");
        System.out.println("===    Login   ===");
        System.out.println("");

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Cliente cliente = banco.autenticarCliente(cpf, senha);

        if (cliente != null) {
            this.clientelogado = cliente;
            System.out.println("Login realizado com sucesso!");
            menuLogado();
        } else {
            System.out.println("Falha ao autenticar cliente");
        }
    }

    private void menuLogado() {
        boolean logado = true;
        System.out.println("");
        System.out.println("=== PLAGE BANK ===");
        System.out.println("\n=== BEM-VINDO, " + this.clientelogado.getNome() + " ===");


        while (logado) {
            System.out.println("1. Criar conta");
            System.out.println("2. Listar minhas contas");
            System.out.println("3. Acessar conta");
            System.out.println("4. Logout");
            System.out.print("Escolha uma opção: ");
            String escolha = sc.nextLine();

            switch (escolha) {
                case "1":
                    criarConta();
                    break;

                case "2":
                    listarMinhasContas();
                    break;

                case "3":
                    acessarConta();
                    break;

                case "4":
                    System.out.println("Logout realizado com sucesso!");
                    clientelogado = null;
                    logado = false;
                    break;

                default:
                    System.out.println("Digite uma opção valida!");
                    break;

            }
        }
    }

    private void criarConta() {
        System.out.println("");
        System.out.println("=== PLAGE BANK ===");
        System.out.println(" Criar nova conta");
        System.out.println("");

        System.out.println("Saldo inicial (minimo R$ 0,00): R$ ");
        try {
            double saldoInicial = Double.parseDouble(sc.nextLine());

            Conta conta = banco.criarConta(clientelogado, saldoInicial);

            if (conta != null) {
                System.out.println("Conta criada com sucesso!");
                System.out.println("Numero da conta: " + conta.getNumero());
                System.out.println("Saldo inicial: R$ " + saldoInicial + "\n");
            } else {
                System.out.println("Erro ao criar novo conta");
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor invalido!");
        }
    }

    private void listarMinhasContas() {
        System.out.println("");
        System.out.println("=== PLAGE BANK ===");
        System.out.println("   Suas contas: ");
        System.out.println("");

        List<Conta> minhasContas = buscarContasDoCliente();

        if (minhasContas.isEmpty()) {
            System.out.println("Nenhum conta foi encontrado!");
            System.out.println("Crie uma conta na opção 1");
            return;
        }

        for (Conta c : minhasContas) {
            System.out.println("Conta: " + c.getNumero() +
                    " | Saldo: R$ " + String.format("%.2f", c.consultarSaldo()) +
                    " | Status: " + c.getStatus());
        }
    }

    private void acessarConta() {
        System.out.println("");
        System.out.println("=== PLAGE BANK ===");
        System.out.println("   Suas contas: ");
        System.out.println("");

        List<Conta> minhasContas = buscarContasDoCliente();

        if (minhasContas.isEmpty()) {
            System.out.println("Nenhum conta foi encontrado!");
            return;
        }

        for (Conta c : minhasContas) {
            System.out.println("Conta: " + c.getNumero() +
                    " | Saldo: R$ " + String.format("%.2f", c.consultarSaldo()) +
                    " | Status: " + c.getStatus());
        }
        System.out.print("Digite o número da conta (ou 'voltar'): ");
        String numeroConta = sc.nextLine();

        if (numeroConta.equalsIgnoreCase("voltar")) {
            return;
        }
        Conta contaEscolhida = banco.buscarConta(numeroConta);

        if (!contaEscolhida.getTitular().getCpf().equals(clientelogado.getCpf())) {
            System.out.println("Essa conta não pertence a você");
            return;
        }

        // verifica se a conta esta bloqueada
        if (contaEscolhida.getStatus() == StatusConta.BLOQUEADA) {
            System.out.println("ATENÇÃO: Esta conta está BLOQUEADA!");
            System.out.println("Você pode consultar saldo e histórico, mas não pode sacar ou transferir.");
        }

        menuConta(contaEscolhida);
    }

    private void menuConta(Conta conta) {
        boolean operando = true;

        while (operando) {
            System.out.println("\n=== CONTA: " + conta.getNumero() +
                    " | SALDO: R$ " + String.format("%.2f", conta.consultarSaldo()) + " ===");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir");
            System.out.println("5. Ver histórico");
            System.out.println("6. Voltar");
            System.out.print("Escolha: ");

            String escolha = sc.nextLine();

            switch (escolha) {
                case "1":
                    consultarSaldo(conta);
                    break;
                case "2":
                    depositar(conta);
                    break;
                case "3":
                    sacar(conta);
                    break;
                case "4":
                    transferir(conta);
                    break;
                case "5":
                    verHistorico(conta);
                    break;
                case "6":
                    operando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private void consultarSaldo(Conta conta) {
        System.out.println("");
        System.out.println("=== PLAGE BANK ===");
        System.out.println("      Saldo ");
        System.out.println("");

        System.out.println("Saldo atual: R$ " + String.format("%.2f", conta.consultarSaldo()));
    }

    private void depositar(Conta conta) {
        System.out.println("");
        System.out.println("=== PLAGE BANK ===");
        System.out.println("     Depositar ");
        System.out.println("");
        System.out.println("Valor a depositar: ");

        try {
            double valor = Double.parseDouble(sc.nextLine());

            if (conta.depositar(valor)) {
                System.out.println("Depositado com sucesso!");
                System.out.println("Novo saldo: " + String.format("%.2f", conta.consultarSaldo()));
            } else {
                System.out.println("Erro ao depositar!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor invalido!");
        }
    }

    private void sacar(Conta conta) {
        System.out.println("Valor a sacar: ");

        try {
            double valor = Double.parseDouble(sc.nextLine());

            if (conta.sacar(valor)) {
                System.out.println("Sacado com sucesso!");
                System.out.println("Novo saldo: " + String.format("%.2f", conta.consultarSaldo()));
            } else {
                System.out.println("Saldo insuficiente ou valor invalido");
            }

        } catch (NumberFormatException e) {
            System.out.println("Valor invalido!");
        }
    }

    private void transferir(Conta conta) {
        System.out.println("");
        System.out.println("=== PLAGE BANK ===");
        System.out.println("     Depositar ");
        System.out.println("");

        System.out.println("Numero da conta destino: ");
        String numeroConta = sc.nextLine();

        Conta contaDestino = banco.buscarConta(numeroConta);

        if (contaDestino == null) {
            System.out.println("Conta destino não encontrada!");
            return;
        }

        if (contaDestino.getNumero().equals(conta.getNumero())) {
            System.out.println("✗ Não pode transferir para a mesma conta!");
            return;
        }

        System.out.println("Valor a transferir: ");

        try {
            double valor = Double.parseDouble(sc.nextLine());

            if (conta.transferir(contaDestino, valor)) {
                System.out.println("✓ Transferência realizada com sucesso!");
                System.out.println("Novo saldo: R$ " + String.format("%.2f", conta.consultarSaldo()));
            } else {
                System.out.println("✗ Saldo insuficiente ou valor inválido!");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Valor inválido!");
        }
    }

    private void verHistorico(Conta conta) {
        System.out.println("\n=== HISTÓRICO DE TRANSAÇÕES ===");

        List<Transacao> historico = conta.getHistorico();

        if (historico.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
            return;
        }
        for (Transacao t : historico) {
            System.out.println(t);
        }
    }

    private List<Conta> buscarContasDoCliente() {
        List<Conta> minhasContas = new ArrayList<>();
        for (Conta c : banco.listarContas()) {
            if (c.getTitular().getCpf().equals(clientelogado.getCpf())) {
                minhasContas.add(c);
            }
        }
        return minhasContas;
    }

}



