package View;

import Controller.UserController;
import View.Components.Civ6Title;
import enums.Responses.Response;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

import static View.Menu.MenuType.*;

public class MainMenu extends Menu {


    private static final List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("N e w   G a m e", () -> {
                Menu.changeMenu(NEW_GAME_MENU);
            }),
            new Pair<String, Runnable>("P r o f i l e", () -> {
                Menu.changeMenu(PROFILE_MENU);
            }),
            new Pair<String, Runnable>("S c o r e b o a r d", () -> {
                Menu.changeMenu(SCOREBOARD);
            }),
            new Pair<String, Runnable>("C h a t   R o o m", () -> {
            }),
            new Pair<String, Runnable>("C r e a t e   M a p", () -> {
            }),
            new Pair<String, Runnable>("L o g o u t", () -> {
                logout();
            }),
            new Pair<String, Runnable>("E x i t", () -> {
                Menu.changeMenu(EXIT);
            })
    );

    private static void logout() {
        Response.MainMenu response = UserController.logout();
        changeMenu(LOGIN_MENU);
    }

    private static Pane root = new Pane();
    private static final VBox menuBox = new VBox(-5);
    private static Line line;

    private static Parent createContent() {
        root = new Pane();
        menuBox.getChildren().clear();
        addBackground(root, "Background_A");
        addTitle();

        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 50;

        line = addLine(lineX, lineY, 300, root);
        addMenu(lineX + 5, lineY + 5, menuBox, menuData, root);

        startAnimation(line, menuBox);

        return root;
    }

    private static void addTitle() {
        Civ6Title title = new Civ6Title("CIVILIZATION VI");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 5);

        root.getChildren().add(title);
    }

    public static void show(Stage stage) throws Exception {
        createContent();
        Pane pane = root;
        stage.setScene(new Scene(pane, WIDTH, HEIGHT));
        stage.show();
    }
}
