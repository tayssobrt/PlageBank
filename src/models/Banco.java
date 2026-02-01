package models;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nome;
    private String codigo;

    private List<Conta> contas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();


    public Banco(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public Conta criarConta(Cliente cliente, double saldoinicial) {
        //validações
       if (cliente == null) {
           System.out.println("Cliente invalido");
           return null;
       }
       if (saldoinicial < 0) {
           System.out.println("O saldo inicial deve ser maior ou igual a 0");
           return null;
       }
        if (!clientes.contains(cliente)) {
            System.out.println("Cliente não cadastrado no banco!");
            return null;
        }

        //gera o numero da conta
        String numeroConta = String.format("%06d", contas.size() + 1);

       //finalmente cria a conta
       Conta conta = new Conta(numeroConta, cliente, saldoinicial);
        contas.add(conta);
        return conta;
    }

    public Cliente criarCliente(String nome, String cpf, String senha) {
        Cliente cliente = new Cliente(nome, cpf, senha);
        return cliente;
    }

    public Conta buscarConta(String numero) {
        for (Conta c : contas) {

            if (c.getNumero().equals(numero)) {
                return c;
            }
        }
        System.out.println("Conta não encontrada");
        return null;
    }

    public Cliente buscarCliente(String cpf) {
        for (Cliente c : clientes) {

            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        System.out.println("Conta não encontrada");
        return null;
    }

}
