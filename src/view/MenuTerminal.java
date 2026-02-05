package view;

import models.Banco;
import models.Cliente;
import models.Conta;
import models.StatusConta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuTerminal {
    private Banco banco;
    private Scanner sc = new Scanner(System.in);
    private Cliente clientelogado;
    private Conta contalogado;
    private boolean sistemaRodando;

    public MenuTerminal(Banco banco) {
        this.banco = banco;
        this.sistemaRodando = false;
    }

    public MenuTerminal(Scanner sc, Cliente clientelogado, boolean sistemaRodando) {
        this.banco = banco;
        this.sc = sc;
        this.clientelogado = clientelogado;
    }

    public void iniciar() {
        while (true) {
            System.out.println("=== PLAGE BANK ===\n" +
                    "1. Criar novo cliente\n" +
                    "2. Fazer login\n" +
                    "3. Sair do sistema");

            String escolha = sc.nextLine();
            switch (escolha) {
                case "1":
                    cadastro();
                    break;

                case "2":
                    login();
                    break;

                case "3":
                    return;

                default:
                    System.out.println("Menu não encontrado!");
                    break;

            }
        }
    }

    public void cadastro() {
        while (!sistemaRodando) {
            try {
                System.out.println("");
                System.out.println("  === PLAGE BANK ===");
                System.out.println("= Criar novo cliente =");
                System.out.println("");
                System.out.print("Nome completo: ");
                String nome = sc.nextLine();

                System.out.print("Cpf: ");
                String cpf = sc.nextLine();

                System.out.print("Senha: ");
                String senha = sc.nextLine();

                banco.criarCliente(nome, cpf, senha);
                this.sistemaRodando = true;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao criar novo cliente");
                System.out.println("");
                this.sistemaRodando = false;
            }
        }

        if (sistemaRodando) {
            login();
        }

    }

    public boolean login() {
        while (true) {
            System.out.println("");
            System.out.println("=== PLAGE BANK ===");
            System.out.println("===    Login   ===");
            System.out.println("");

            System.out.print("CPF: ");
            String cpf = sc.nextLine();

            System.out.print("Senha: ");
            String senha = sc.nextLine();

            Cliente clienteAutenticado = banco.autenticarCliente(cpf, senha);
            if (clienteAutenticado != null) {
                this.clientelogado = clienteAutenticado;
                System.out.println("Login realizado com sucesso!");
                menuLogado();
                return false;
            } else {
                System.out.println("Falha ao autenticar cliente");
            }
        }
    }

    public void menuLogado() {
        while (true) {
            System.out.println("");
            System.out.println("=== PLAGE BANK ===");
            System.out.println("\n=== BEM-VINDO, " + this.clientelogado.getNome() + " ===");
            System.out.println("1. Criar conta");
            System.out.println("2. Listar minhas contas");
            System.out.println("3. Acessar conta");
            System.out.println("4. Logout");
            System.out.print("Escolha uma opção: ");
            String escolha = sc.nextLine();

            switch (escolha) {
                case "1":
                    banco.criarConta(clientelogado, 0);
                    break;

                case "2":
                    banco.listarContas();
                    break;

                case "3":
                    acessarConta();
                    break;

                case "4":
                    clientelogado = null;
                    return;

                default:
                    System.out.println("Digite uma opção valida!");
                    break;

            }
        }
    }

    public void acessarConta() {
        System.out.println("Suas contas: ");

        List<Conta> minhasContas = new ArrayList<>();
        for (Conta c : banco.listarContas()) {
            if (c.getTitular().getCpf().equals(clientelogado.getCpf())) {
                minhasContas.add(c);
            }
        }
        for (Conta c : banco.listarContas()) {
            if (c.getTitular().getCpf().equals(clientelogado.getCpf())) {
                System.out.println("Nome: " + c.getTitular().getNome());
                System.out.println("Número: " + c.getNumero());
                System.out.println("Status: " + c.getStatus());
                System.out.println("-------------------");
            }
        }

        System.out.print("Digite o número da conta (ou 'voltar'): ");
        String numeroConta = sc.nextLine();

        if (!numeroConta.equalsIgnoreCase("voltar")) {
            Conta contaEscolhida = banco.buscarConta(numeroConta);
            if (contaEscolhida != null &&
                    contaEscolhida.getTitular().getCpf().equals(clientelogado.getCpf())) {

                // verifica se a conta esta bloqueada
                if (contaEscolhida.getStatus() == StatusConta.BLOQUEADA) {
                    System.out.println("ATENÇÃO: Esta conta está BLOQUEADA!");
                    System.out.println("Você pode consultar saldo e histórico, mas não pode sacar ou transferir.");
                }

                System.out.println("Conta selecionada: " + numeroConta);
            }
        }
    }

}
