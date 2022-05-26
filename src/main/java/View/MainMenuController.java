package View;

import View.GraphicModels.Civ6MenuItem;
import View.GraphicModels.Civ6Title;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

import static View.MenuController.HEIGHT;
import static View.MenuController.MenuType.MAIN_MENU;
import static View.MenuController.WIDTH;

public class MainMenuController {


    private static final List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Login", () -> {
            }),
            new Pair<String, Runnable>("New Game", () -> {
            }),
            new Pair<String, Runnable>("Profile", () -> {
            }),
            new Pair<String, Runnable>("Scoreboard", () -> {
            }),
            new Pair<String, Runnable>("Chat Room", () -> {
            }),
            new Pair<String, Runnable>("Create a Map", () -> {
            }),
            new Pair<String, Runnable>("Exit", () -> {
            })
    );

    private static final Pane root = new Pane();
    private static final VBox menuBox = new VBox(-5);
    private static Line line;

    private static Parent createContent() {
        addBackground();
        addTitle();

        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 50;

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        startAnimation();

        return root;
    }

    private static void addBackground() {
        ImageView imageView = new ImageView(MainMenuController.class.getClassLoader().getResource("images/Background_A.png").toExternalForm());
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }

    private static void addTitle() {
        Civ6Title title = new Civ6Title("CIVILIZATION VI");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 5);

        root.getChildren().add(title);
    }

    private static void addLine(double x, double y) {
        line = new Line(x, y, x, y + 300);
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

    private static void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            Civ6MenuItem item = new Civ6MenuItem(data.getKey());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });
        root.getChildren().add(menuBox);
    }

    public static MenuController.MenuType start(Stage stage) throws Exception {
        createContent();
        Pane pane = root;
        stage.setScene(new Scene(pane, WIDTH, HEIGHT));
        stage.show();
        return MAIN_MENU;
    }
}
