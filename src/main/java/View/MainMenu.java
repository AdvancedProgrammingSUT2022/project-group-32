package View;

import Controller.UserController;
import enums.Responses.Response;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu extends Menu {
    public static void run(Scanner scanner) {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.startsWith("logout")){
                logout(command);
                return;
            }
            else if (command.startsWith("enter menu")){
                enterMenu(command);
                return;
            }
            else if(command.startsWith("current menu")){
                Menu.showCurrentMenu();
            }
            else{
                Menu.invalidCommand();
            }
        }
    }

    public static void enterMenu(String command) {
        ArrayList<String> parameters = CLI.getParameters(command , "m");
        if(parameters == null){
            Menu.invalidCommand();
            return;
        }
        MenuType newMenu = Menu.getType(parameters.get(0));
        if(newMenu == null){
            Menu.invalidCommand();
            return;
        }
        Menu.setCurrentMenu(newMenu);
    }

    public static void logout(String command) {
        Response.MainMenu responses = UserController.logout();
        System.out.println(responses.getString());
    }
}
