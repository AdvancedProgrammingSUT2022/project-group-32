package View;

import javafx.application.Application;
import javafx.stage.Stage;

public class MenuController extends Application {
    public static final int WIDTH = 1440;
    public static final int HEIGHT = 720;
    private static Stage stage;

    public enum MenuType {
        MAIN_MENU("mainMenu"),
        LOGIN_MENU("loginMenu"),
        REGISTER_MENU("registerMenu"),
        GAME_MENU("gameMenu"),
        PROFILE_MENU("profileMenu"),
        EXIT("exit");

        String name;

        MenuType(String name) {
            this.name = name;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Civilization VI");
        stage.setResizable(false);
        MenuType currentMenu = MenuType.REGISTER_MENU;
        switch (currentMenu) {
            case MAIN_MENU -> currentMenu = MainMenuController.start(stage);
            case LOGIN_MENU -> currentMenu = LoginMenuController.start(stage);
            case REGISTER_MENU -> currentMenu = RegisterMenuController.start(stage);
        }
    }

    public static void changeMenu(MenuType menuType) {
        try {
            switch (menuType) {
                case MAIN_MENU -> MainMenuController.start(stage);
                case LOGIN_MENU -> LoginMenuController.start(stage);
                case REGISTER_MENU -> RegisterMenuController.start(stage);
                case EXIT -> System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
