package View;

import Controller.UserController;
import enums.Responses.Response;

import java.util.ArrayList;
import java.util.Scanner;

public class ProfileMenu extends Menu {
    public static void run(Scanner scanner) {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.startsWith("back")){
                back(command);
            }
            else if (command.startsWith("change password")){
                changePassword(command);
            }
            else if (command.startsWith("change nickname")){

            }
            else if (command.startsWith("delete account")){
                deleteAccount(command);
            }
            else if (command.startsWith("show scoreboard")){
                showScoreboard(command);
            }
        }
    }

    public static void back(String command) {
            setCurrentMenu(MenuType.MAIN_MENU);
    }

    public static void changePassword(String command) {
        ArrayList<String> parameters = CLI.getParameters(command , "p" , "p");
        Response.ProfileMenu response = UserController.changePassword(parameters.get(1) , parameters.get(1));
        System.out.println(response.getString());
    }

    public static void changeNickname(String command) {
        ArrayList<String> parameters = CLI.getParameters(command , "n");
        Response.ProfileMenu response = UserController.changeNickname(parameters.get(2));
        System.out.println(response.getString());
    }

    public static void deleteAccount(String command) {
        ArrayList<String> parameters = CLI.getParameters(command , "p");
        Response.ProfileMenu response = UserController.removeUser(parameters.get(2));
        System.out.println(response.getString());
    }

    public static void showScoreboard(String command) {

    }
}
