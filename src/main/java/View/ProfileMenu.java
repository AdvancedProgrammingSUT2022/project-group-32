package View;

import Controller.UserController;
import Model.User;
import View.Components.Civ6Title;
import enums.Responses.Response;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

import static View.Menu.MenuType.*;

public class ProfileMenu extends Menu{
    private static final List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("C h a n g e   N i c k n a m e", () -> changeMenu(NICK_CHANGE_MENU)),
            new Pair<String, Runnable>("C h a n g e   P a s s w o r d", () -> changeMenu(PASS_CHANGE_MENU)),
            new Pair<String, Runnable>("D e l e t e   A c c o u n t", ProfileMenu::deleteAccount),
            new Pair<String, Runnable>("B a c k", () -> changeMenu(MAIN_MENU))
    );

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

        line = addLine(lineX, lineY, 160, root);
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

    ///////////////

    private static void deleteAccount(){
        UserController.removeUser();
        changeMenu(REGISTER_MENU);
    }
}
