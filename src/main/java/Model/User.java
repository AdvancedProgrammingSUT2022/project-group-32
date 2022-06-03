package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.util.Comparator;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String photoAddress;
    private int score;

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.photoAddress = null;
        this.score = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPhotoAddress() {
        return photoAddress;
    }

    public void setPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress;
    }

    public ImageView getImage(){
        if(photoAddress == null){
            return new ImageView(new Image(getClass().getResource("/images/Default_Pic.png").toExternalForm()));
        } else {
            return new ImageView(new Image(new File(photoAddress).toURI().toString()));
        }
    }

    public static class compareByScore implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return o1.getScore() - o2.getScore();
        }
    }
}
