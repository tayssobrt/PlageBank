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
        System.out.println("=== PLAGE BANK ===\n" +
                "1. Criar novo cliente\n" +
                "2. Fazer login\n" +
                "3. Sair do sistema");

        String escolha = sc.nextLine();
       switch (escolha) {
           case "1":
               while (!sistemaRodando) {
                   try {
                       System.out.print("Nome completo: ");
                       String nome = sc.nextLine();

                       System.out.println("Cpf: ");
                       String cpf = sc.nextLine();

                       System.out.println("Senha: ");
                       String senha = sc.nextLine();

                       banco.criarCliente(nome, cpf, senha);
                       this.sistemaRodando = true;
                       break;
                   } catch (IllegalArgumentException e) {
                       System.out.println("Erro ao criar novo cliente");
                       this.sistemaRodando = false;
                   }
               }
           case "2":


       }
    }

}
