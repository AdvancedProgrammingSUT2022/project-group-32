package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class User implements Serializable {
    public static final long serialVersionUID = 3L;

    private String username;
    private String password;
    private String nickname;
    private String photoAddress;
    private String lastUpdate;
    private int bestScore;
    private String bestScoreTime;

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.photoAddress = null;
        this.bestScoreTime = "123-111-65";
        this.lastUpdate = java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.bestScore = 0;
    }

    public String getBestScoreTime() {
        return bestScoreTime;
    }

    public void setBestScoreTime(String bestScoreTime) {
        this.bestScoreTime = bestScoreTime;
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

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public String getPhotoAddress() {
        return photoAddress;
    }


    public void setPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress;
    }

    public void setPhotoToDeafault() {
        this.photoAddress = "src/main/resources/images/Default_Pic.png";
    }

    public ImageView getImage() {
        if (photoAddress == null) {
            return new ImageView(new Image(getClass().getResource("/images/Default_Pic.png").toExternalForm()));
        } else {
            return new ImageView(new Image(new File(photoAddress).toURI().toString()));
        }
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public static class compareByScore implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            if (o1.getBestScore() != o2.getBestScore()) return o1.getBestScore() - o2.getBestScore();
            return o1.getLastUpdate().compareTo(o2.getLastUpdate());
        }
    }
}
