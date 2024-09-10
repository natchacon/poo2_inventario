package cl.duoc.poo2.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainMenu {

    private List<UserCommand> userCommands;
    private Scanner scanner = new Scanner(System.in);

    public MainMenu() {
        this.userCommands = new ArrayList<>();
    }

    public void addUserCommand(UserCommand userCommand) {
        this.userCommands.add(userCommand);
    }

    public void printMainMenu() {
        System.out.println("\r");
        System.out.println("Opciones de menu:");
        userCommands.forEach(opcion -> {
            System.out.println("[" + opcion.getOptionMenuNumber() + "] " + opcion.getOptionMenuName());
        });
    }

    public UserCommand chooseMenu() {
        UserCommand opcionMenu = null;
        do {
            String opcionMenuIngresada = scanner.nextLine();
            if (isIntegerChoiceMenu(opcionMenuIngresada)) {
                Optional<UserCommand> opcionMenuOptional = this.userCommands.stream().filter(o -> o.getOptionMenuNumber().equals(Integer.valueOf(opcionMenuIngresada))).findFirst();
                if (opcionMenuOptional.isPresent()) {
                    opcionMenu = opcionMenuOptional.get();
                } else {
                    System.out.println("Opcion de menu no valida, favor reingrese");
                }
            }
        } while (opcionMenu == null);
        return opcionMenu;
    }

    private Boolean isIntegerChoiceMenu(String opcionMenu) {
        try {
            Integer.valueOf(opcionMenu);
            return Boolean.TRUE;
        } catch (Exception e) {
            System.out.println("opcion de menu inválida. Debe ingresar un numero entero entre 1 y "+ this.userCommands.size() +". Reingrese");
            return Boolean.FALSE;
        }
    }


}
