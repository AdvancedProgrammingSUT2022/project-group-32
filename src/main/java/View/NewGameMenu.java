package View;

import Controller.GameController;
import Controller.UserController;
import Model.User;
import enums.Responses.Response;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static View.Menu.MenuType.EXIT;
import static View.Menu.MenuType.GAME_VIEW;
import static View.PastViews.Menu.setCurrentMenu;

public class NewGameMenu extends Menu {
    private static Pane root;
    private static VBox menuBox;
    private static Line line;
    private static Alert alert;
    private static DialogPane dialogPane;
    private static Stage stage;
    private static final Label invitationStatus = new Label("");
    private static TextField playerCountField;
    private static TextField playerUsernamesField;
    private static TextField mapSize;

    private static final List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("S E N D   I N V I T A T I O N S", () -> {
                try {
                    sendInvitations(Integer.parseInt(playerCountField.getText().trim()),
                            new ArrayList<String>(Arrays.stream(playerUsernamesField.getText().split(",")).toList()));
                } catch (Exception e) {
                    showAlert(alert, " invalid input for invitations");
                    System.err.println(" INVALID INPUTS FOR INVITATIONS!");
                    e.printStackTrace();
                }
            }),
            new Pair<String, Runnable>(" S T A R T   G A M E", () -> {
                startGame();
            }),
            new Pair<String, Runnable>("E x i t", () -> {
                Menu.changeMenu(EXIT);
            })
    );

    private static void startGame() {
        // TODO: 7/12/2022
        if (newGame(new ArrayList<String>(Arrays.stream(playerUsernamesField.getText().split(",")).toList()))) {

        } else {
            showAlert(alert, "game start failed");
        }
        Menu.changeMenu(GAME_VIEW);
    }

    private static void sendInvitations(int playersCount, ArrayList<String> invitationsUsernames) {
        int mapSizeInteger;
        try {
            mapSizeInteger = Integer.parseInt(mapSize.getText().trim());
        } catch (Exception e) {
            showAlert(alert, "invalid mapSize");
            return;
        }

        if (playersCount - 1 != invitationsUsernames.size()) {
            showAlert(alert, "usernames count is not valid");
            return;
        }

        if (newGame(invitationsUsernames)) {
            invitationStatus.setText("invitations sent, not accepted!");
            // TODO: 7/11/2022 seding initations and waiting for acceptence and then initing the game
        } else {
            showAlert(alert, "invalid usernames");
        }

    }


    private static Parent createContent() {
        addBackground(root, "Background_B");
        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3;
        line = addLine(lineX, lineY, 350, root);
        addNewGameItems();
        addMenu(lineX + 5, lineY + 5, menuBox, menuData, root);

        startAnimation(line, menuBox);
        return root;
    }

    private static void addNewGameItems() {
        VBox vBox = new VBox(10);
        Label playerCount = new Label("number of players:");
        Label playerUsernames = new Label("other players username(','):");
        Label mapSizeLabel = new Label("Map Size: (1-5): ");
        playerCountField = new TextField();
        playerCountField.setTooltip(new Tooltip("enter the number of all players playing"));
        playerUsernamesField = new TextField();
        playerUsernamesField.setTooltip(new Tooltip("enter other players usernames seperated bt ',' "));
        mapSize = new TextField();
        mapSize.setTooltip(new Tooltip("enter the map size in 1-5"));
        // TODO: 7/11/2022 sending and checking invitations, if initaions are send or accepted, invitationStatus changes;
        playerCount.setTextFill(Color.WHITE);
        playerUsernames.setTextFill(Color.WHITE);
        invitationStatus.setTextFill(Color.WHITE);
        mapSizeLabel.setTextFill(Color.WHITE);

        playerCount.setFont(Font.font(14));
        mapSizeLabel.setFont(Font.font(14));
        invitationStatus.setFont(Font.font(14));
        invitationStatus.setTooltip(new Tooltip("states: sent but not accepted/ accepted.\n start the game when is accepted"));
        playerUsernames.setFont(Font.font(14));
        vBox.getChildren().addAll(playerCount, playerCountField, playerUsernames, playerUsernamesField, mapSizeLabel, mapSize, invitationStatus, new Text());
        menuBox.getChildren().addAll(vBox);
    }

    public static void show(Stage primaryStage) throws Exception {
        stage = primaryStage;
        root = new Pane();
        alert = initAlert();
        menuBox = new VBox(-5);
        createContent();
        Pane pane = root;
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.getStylesheets().add(LoginMenu.class.getClassLoader().getResource("css/MenuStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    /**
     * @return true if newGame is created, false if not
     */
    public static boolean newGame(ArrayList<String> usernames) {
        if (usernames == null) {
            return false;
        }

        ArrayList<User> playingUsers = new ArrayList<>();
        playingUsers.add(UserController.getCurrentUser());
        ArrayList<String> nonexistenceUsernames = new ArrayList<>();
        for (String username : usernames) {
            if (UserController.getUserByUsername(username) == null)
                nonexistenceUsernames.add(username);
            else
                playingUsers.add(UserController.getUserByUsername(username));
        }

        if (nonexistenceUsernames.size() != 0) {
            String invalidUsernames = "";
            for (int i = 0; i < nonexistenceUsernames.size(); i++) {
                invalidUsernames += nonexistenceUsernames.get(i);
                if (i != nonexistenceUsernames.size() - 1) invalidUsernames += ",";
            }
            System.out.println(Response.MainMenu.NONEXISTENCE_USERS.getString(invalidUsernames));
            return false;
        }

        // TODO: 7/11/2022 this parts needs to fit for graphical start

        GameController.newGame(playingUsers);
        setCurrentMenu(View.PastViews.Menu.MenuType.GAME_MENU);
//        setCurrentMenu(View.PastViews.Menu.MenuType.GAME_MENU);
        System.out.println(Response.MainMenu.NEW_GAME_STARTED.getString());
        return true;
    }
    //////////////

    private static void register() {
//        Response.LoginMenu response = UserController.register(playerCountField.getText(), playerUsernamesField.getText());
//        if (response.equals(Response.LoginMenu.USERNAME_EXISTS)) {
//            showAlert(alert, response.getString(playerCountField.getText()));
//        } else if (response.equals(Response.LoginMenu.NICKNAME_EXISTS)) {
//            showAlert(alert, response.getString(playerUsernamesField.getText()));
//        } else if (!response.equals(Response.LoginMenu.REGISTER_SUCCESSFUL)) {
//            showAlert(alert, "Invalid input format");
//        } else {
//            changeMenu(MAIN_MENU);
//        }
    }

}

