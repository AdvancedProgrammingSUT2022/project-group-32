package View;

import enums.Responses.Response;

import java.util.Scanner;

public class Menu {
    public static MenuType currentMenu;

    public enum MenuType {
        MAIN_MENU("main menu"),
        LOGIN_MENU("login menu"),
        GAME_MENU("game menu"),
        PROFILE_MENU("profile menu"),
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
