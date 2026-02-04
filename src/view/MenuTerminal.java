package view;

import models.Banco;
import models.Cliente;

import java.util.Scanner;

public class MenuTerminal {
    private Banco banco;
    private Scanner sc = new Scanner(System.in);
    private Cliente clientelogado;
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
                    iniciar();
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
                    return false;
                } else {
                    System.out.println("Falha ao autenticar cliente");
            }
        }
    }


}
