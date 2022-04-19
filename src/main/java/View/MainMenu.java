package View;

import Controller.UserController;
import enums.Responses.Response;

import java.util.Scanner;

public class MainMenu extends Menu {
    public static void run(Scanner scanner) {
        String command;
        while (true) {
            command = scanner.nextLine();
            if(command.startsWith("logout")){
                logout(command);
            }
            else if (command.startsWith("enter menu")){
                enterMenu(command);
            }

        }
    }

    public static void enterMenu(String command) {

    }

    public static void logout(String command) {
        Response.MainMenu responses = UserController.logout();
        System.out.println(responses.getString());
    }
}
