import models.Banco;
import view.MenuTerminal;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Plage Bank", "001");
        MenuTerminal menu = new MenuTerminal(banco);
        menu.iniciar();
    }
}