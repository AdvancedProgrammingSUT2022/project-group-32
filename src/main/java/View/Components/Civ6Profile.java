package View.Components;

import Model.User;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Civ6Profile extends VBox {
    private final float width = 300, height = 200;
    private ImageView photo;
    private Text name;

    public Civ6Profile(User user){
        this.setWidth(width);
        this.setHeight(height);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        photo = user.getImage();
        photo.setFitWidth(300); // TODO: 6/3/2022 add border around picture
        photo.setFitHeight(175);
        name = new Text(user.getUsername() + " (aka." + user.getNickname() + ")");
        name.setFont(Font.loadFont(getClass().getClassLoader().getResource("fonts/Menu.ttf").toExternalForm(), 48));
        name.setFill(Color.WHITE);
        name.setFont(Font.font(20));
        this.getChildren().addAll(photo, name);
    }
}
