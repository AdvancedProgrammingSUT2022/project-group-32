package View;

import javafx.application.Application;
import javafx.stage.Stage;

public class MenuController extends Application {
    public static final int WIDTH = 1440;
    public static final int HEIGHT = 720;

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

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Civilization VI");
        stage.setResizable(false);
        MenuType currentMenu = MenuType.MAIN_MENU;
        switch (currentMenu) {
            case MAIN_MENU -> currentMenu = MainMenuController.start(stage);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
