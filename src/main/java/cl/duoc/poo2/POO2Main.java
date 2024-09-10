package cl.duoc.poo2;


import cl.duoc.poo2.view.*;

public class POO2Main {

    private MainMenu mainMenu;

    public POO2Main() {
        mainMenu = new MainMenu();
        mainMenu.addUserCommand(new AddProductToInventoryCommand(1, "Agregar producto al inventario"));
        mainMenu.addUserCommand(new UpdateProductQuantityFromInventoryCommand(2, "Actualizar cantidad de un producto"));
        mainMenu.addUserCommand(new UpdateProductPriceFromInventoryCommand(3, "Actualizar precio de un producto"));
        mainMenu.addUserCommand(new PrintInventoryCommand(4, "Imprimir inventario"));
        mainMenu.addUserCommand(new FindProductFromInventoryCommand(5, "Buscar producto"));
        mainMenu.addUserCommand(new DeleteProductFromInventoryCommand(6, "Eliminar producto"));
        mainMenu.addUserCommand(new ExitCommand(7, "Salir"));
    }

    public static void main(String[] args) {
        try {
            System.out.println("...::: Bienvenido a Sistema de Inventario DUOC POO2 :::...");
            POO2Main poo2Main = new POO2Main();
            do {
                poo2Main.mainMenu.printMainMenu();
                UserCommand userCommand = poo2Main.mainMenu.chooseMenu();
                userCommand.executeCommand();
            } while (true);
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getCause());
        }


    }
}
