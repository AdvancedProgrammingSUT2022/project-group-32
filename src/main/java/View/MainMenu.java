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
            }
            else if (command.startsWith("enter menu")){
                enterMenu(command);
            }
            else{
                System.out.println(Response.LoginMenu.INVALID_COMMAND);
            }
        }
    }

    public static void enterMenu(String command) {
        ArrayList<String> parameters = CLI.getParameters(command , "m");
        if (parameters == null) {
            System.out.println(Response.LoginMenu.INVALID_COMMAND);
        }
        // TODO: 4/19/2022 changing current menu in View or MenuController
    }

    public static void logout(String command) {
        Response.MainMenu responses = UserController.logout();
        System.out.println(responses.getString());
    }
}
