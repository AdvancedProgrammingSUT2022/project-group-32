package View;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

import static View.Menu.MenuType.PROFILE_MENU;

public class ScoreBoardMenu extends Menu {
    private static Pane root;
    private static VBox menuBox;
    private static Line line;
    private static Alert alert;
    private static final List<Pair<String, Runnable>> menuData = Arrays.asList(

            new Pair<String, Runnable>("B a c k", () -> changeMenu(PROFILE_MENU))
    );

    private static Parent createContent() {
        addBackground(root, "Background_A");
        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 50;
        line = addLine(lineX, lineY, 160, root);
        addFields();
        addMenu(lineX + 5, lineY + 5, menuBox, menuData, root);
        startAnimation(line, menuBox);
        return root;
    }

    private static void addFields() {
        VBox vBox = new VBox(10);

    }


    public static void show(Stage stage) throws Exception {
        root = new Pane();
//        alert = initAlert();
        menuBox = new VBox(-5);
        createContent();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(LoginMenu.class.getClassLoader().getResource("css/MenuStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
