package View;

import enums.Responses.Response;

import java.util.Scanner;

public class Menu {
    public static MenuType currentMenu;

    // TODO: 4/21/2022 Invalid command structure must be detected: register aslgfkhs;dfl -u user -p pP123123$ -n niki must be invalid
    public enum MenuType {
        MAIN_MENU("mainMenu"),
        LOGIN_MENU("loginMenu"),
        GAME_MENU("gameMenu"),
        PROFILE_MENU("profileMenu"),
        EXIT("exit");

        String name;

        MenuType(String name) {
            this.name = name;
        }
    }

    public static MenuType getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(MenuType currentMenu) {
        Menu.currentMenu = currentMenu;
    }

    public static void run(Scanner scanner) {
        currentMenu = MenuType.LOGIN_MENU;
        while(currentMenu != MenuType.EXIT){
            switch (currentMenu) {
                case LOGIN_MENU -> LoginMenu.run(scanner);
                case MAIN_MENU ->  MainMenu.run(scanner);
                case GAME_MENU ->  GameMenu.run(scanner);
                case PROFILE_MENU -> ProfileMenu.run(scanner);
            }
        }
    }

    public static MenuType getType(String name){ // returns menuType based on name
        for(MenuType type : MenuType.values()){
            if(type.name.equals(name)){
                return type;
            }
        }
        return null;
    }

    public static void showCurrentMenu(String... command) {
        System.out.println(currentMenu.name);
    }

    public static void invalidCommand(String... command){
        System.out.println(Response.MainMenu.INVALID_COMMAND.getString());
    }
}
