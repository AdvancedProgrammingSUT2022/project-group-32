package View;

import Controller.UserController;
import enums.Responses.Response;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginMenu extends Menu {
    public static void run(Scanner scanner) {
        String command;
        while (true) {
            command = scanner.nextLine();
            if(command.startsWith("user login")){
                login(command);
            }
            else if (command.startsWith("user create")){
                register(command);
            }
            else if (command.startsWith("exit")){
                exit(command);
            }
        }

    }

    public static void login(String command) {
        ArrayList<String> parameters = CLI.getParameters(command , "u" , "p");
        Response.LoginMenu response = UserController.login(parameters.get(0) , parameters.get(1));
        System.out.println(response.getString());
    }

    public static void register(String command) {
        ArrayList<String> parameters = CLI.getParameters(command,"u" , "p" , "n" );
        Response.LoginMenu response = UserController.register(parameters.get(0), parameters.get(1), parameters.get(2));
        if(response.equals(Response.LoginMenu.USERNAME_EXISTS)){
            System.out.println(response.getString(parameters.get(0)));
        }
        else if (response.equals(Response.LoginMenu.NICKNAME_EXISTS)){
            System.out.println(response.getString(parameters.get(2)));
        }
        else{
            System.out.println(response.getString());
        }
    }

    public static void exit(String command) {
            setCurrentMenu(MenuType.EXIT);
    }
}
