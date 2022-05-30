package View;

import View.Components.Civ6MenuItem;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.List;

public class Menu extends Application {
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
            case MAIN_MENU -> MainMenu.show(stage);
            case LOGIN_MENU -> LoginMenu.show(stage);
            case REGISTER_MENU -> RegisterMenu.show(stage);
        }
    }

    public static void changeMenu(MenuType menuType) {
        try {
            switch (menuType) {
                case MAIN_MENU -> MainMenu.show(stage);
                case LOGIN_MENU -> LoginMenu.show(stage);
                case REGISTER_MENU -> RegisterMenu.show(stage);
                case EXIT -> System.exit(0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void addBackground(Pane root) {
        ImageView imageView = new ImageView(MainMenu.class.getClassLoader().getResource("images/Background_B.png").toExternalForm());
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }


    protected static void startAnimation(Line line, VBox menuBox) {
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

    protected static void addMenu(double x, double y, VBox menuBox, List<Pair<String, Runnable>> menuData, Pane root) {
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

    protected static Alert initAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input", ButtonType.OK);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(RegisterMenu.class.getClassLoader().getResource("css/MenuStyle.css").toExternalForm());
        return alert;
    }

    protected static void showAlert(Alert alert, String message) {
        alert.setHeaderText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
