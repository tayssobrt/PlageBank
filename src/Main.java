import models.Cliente;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Cliente cliente = new Cliente();

        String senha = "senha!!!";
        cliente.autenticar(senha);

    }
}