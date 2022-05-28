package View;

import View.GraphicModels.Civ6MenuItem;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

import static View.MenuController.HEIGHT;
import static View.MenuController.MenuType.*;
import static View.MenuController.WIDTH;

public class RegisterMenuController {
    private static final List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("R E G I S T E R", () -> {
            }),
            new Pair<String, Runnable>("G O   T O   L O G I N", () -> {
                MenuController.changeMenu(LOGIN_MENU);
            }),
            new Pair<String, Runnable>("E x i t", () -> {
                MenuController.changeMenu(EXIT);
            })
    );


    private static Pane root;
    private static VBox menuBox;
    private static Line line;

    private static Parent createContent() {
        addBackground();
        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3;
        addLine(lineX, lineY);
        addLoginItems();
        addMenu(lineX + 5, lineY + 5);

        startAnimation();
        return root;
    }

    private static void addLoginItems() {
        VBox vBox = new VBox(10);
        Label username = new Label("username:");
        Label password = new Label("password:");
        Label nickname = new Label("nickname:");
        username.setTextFill(Color.WHITE);
        password.setTextFill(Color.WHITE);
        nickname.setTextFill(Color.WHITE);
        username.setFont(Font.font(14));
        password.setFont(Font.font(14));
        nickname.setFont(Font.font(14));
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField nicknameField = new TextField();
        vBox.getChildren().addAll(username, usernameField, nickname, nicknameField, password, passwordField, new Text());
        menuBox.getChildren().addAll(vBox);

    }

    private static void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            Civ6MenuItem item = new Civ6MenuItem(data.getKey());
            item.setTranslateX(-300);
            item.setOnAction(data.getValue());

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });
        root.getChildren().addAll(menuBox);
    }

    private static void addLine(double x, double y) {
        line = new Line(x, y, x, y + 350);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        root.getChildren().add(line);
    }

    private static void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {
            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    private static void addBackground() {
        ImageView imageView = new ImageView(MainMenuController.class.getClassLoader().getResource("images/Background_B.png").toExternalForm());
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }

    public static MenuController.MenuType start(Stage stage) throws Exception {
        root = new Pane();
        menuBox = new VBox(-5);
        createContent();
        Pane pane = root;
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.getStylesheets().add(LoginMenuController.class.getClassLoader().getResource("css/MenuStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        return MAIN_MENU;
    }
}
