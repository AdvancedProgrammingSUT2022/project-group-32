package View;

import Controller.GameController;
import Controller.UserController;
import Model.User;
import enums.Responses.Response;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu extends Menu {
    public static void run(Scanner scanner) {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.startsWith("logout")) {
                logout(command);
                return;
            } else if (command.startsWith("enter")) {
                enterMenu(command);
                return;
            } else if (command.startsWith("current menu")) {
                Menu.showCurrentMenu();
            } else if (command.startsWith("new game")) {
                if (newGame(command)) {
                    Menu.setCurrentMenu(MenuType.GAME_MENU);
                    return;
                }
            } else {
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

    /**
     * @return true if newGame is created, false if not
     */
    public static boolean newGame(String command) {
        ArrayList<String> usernames = CLI.getParameters(command, "P");
        if (usernames == null) {
            Menu.invalidCommand();
            return false;
        }
        ArrayList<User> playingUsers = new ArrayList<>();
        playingUsers.add(UserController.getCurrentUser());
        ArrayList<String> nonexistenceUsernames = new ArrayList<>();
        for (String username : usernames) {
            if (UserController.getUserByUsername(username) == null)
                nonexistenceUsernames.add(username);
            else
                playingUsers.add(UserController.getUserByUsername(username));
        }

        if (nonexistenceUsernames.size() != 0) {
            String invalidUsernames = "";
            for (int i = 0; i < nonexistenceUsernames.size(); i++) {
                invalidUsernames += nonexistenceUsernames.get(i);
                if (i != nonexistenceUsernames.size() - 1) invalidUsernames += ",";
            }
            System.out.println(Response.MainMenu.NONEXISTENCE_USERS.getString(invalidUsernames));
            return false;
        }
        GameController.newGame(playingUsers);
        Menu.setCurrentMenu(MenuType.GAME_MENU);
        System.out.println(Response.MainMenu.NEW_GAME_STARTED.getString());
        return true;
    }
}
