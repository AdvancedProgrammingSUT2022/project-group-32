package View;

import Model.User;
import enums.RequestActions;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        menuBox.getChildren().add(addFields());
        addMenu(lineX + 5, lineY + 5, menuBox, menuData, root);
        startAnimation(line, menuBox);
        return root;
    }

    private static VBox addFields() {
        VBox vBox = new VBox(10);

        ArrayList<User> users = (ArrayList<User>) Network.getResponseObjOf(RequestActions.GET_USERS.code, null);
        users = new ArrayList<>(users.stream().sorted(Comparator.comparing(User::getBestScore).thenComparing(User::getBestScoreTime).thenComparing(User::getUsername).reversed()).collect(Collectors.toList()));
        for (User user : users) {
            HBox row = new HBox(user.getImage());
            System.out.println(user.getNickname() + " " + user.getBestScore() + " " + user.getBestScoreTime());
            Label thisRow = new Label(user.getNickname() + " " + user.getBestScore() + " " + user.getBestScoreTime());
            vBox.getChildren().add(thisRow);
        }
        return vBox;

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
