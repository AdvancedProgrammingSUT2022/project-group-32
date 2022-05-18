package Controller;

import Model.User;
import View.Menu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import enums.Responses.Response;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserController {
    private static ArrayList<User> users;
    private static User currentUser;

    static {
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/users.json")));
            users = new Gson().fromJson(json, new TypeToken<List<User>>(){}.getType());
        } catch (IOException e) {
            System.err.println("Error while loading users");
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setCurrentUser(ArrayList<User> users) {
        UserController.users = users;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        UserController.currentUser = user;
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static User getUserByNickname(String nickname) {
        for (User user : users) {
            if (user.getNickname().equals(nickname)) {
                return user;
            }
        }
        return null;
    }

    public static boolean isPasswordStrong(String password) {
        if (password.length() < 8 || password.length() > 32) return false;
        if (password.matches("[^0-9]+")) return false;
        if (password.matches("[^a-z]+")) return false;
        if (password.matches("[^A-Z]+")) return false;
        return !password.matches("[^*.!@#$%^&(){}\\[\\]:;<>,?/~_+\\-=|]+");
    }

    private static boolean IsNameValid(String name) {
        return name.matches("\\w+");
    }

    private static boolean IsPasswordValid(String password) {
        return password.matches("\\S+");
    }

    public static Response.LoginMenu register(String username, String password, String nickname) { // login menu
        if (!IsNameValid(username)) {
            return Response.LoginMenu.INVALID_USERNAME_FORMAT;
        }
        if (!IsNameValid(nickname)) {
            return Response.LoginMenu.INVALID_NICKNAME_FORMAT;
        }
        if (!IsPasswordValid(password)) {
            return Response.LoginMenu.INVALID_PASSWORD_FORMAT;
        }
        if (getUserByUsername(username) != null) {
            return Response.LoginMenu.USERNAME_EXISTS;
        }
        if (getUserByNickname(nickname) != null) {
            return Response.LoginMenu.NICKNAME_EXISTS;
        }
        if (!isPasswordStrong(password)) {
            return Response.LoginMenu.WEAK_PASSWORD;
        }
        User user = new User(username, password, nickname);
        users.add(user);
        saveUsers();
        return Response.LoginMenu.REGISTER_SUCCESSFUL;
    }

    public static Response.LoginMenu login(String username, String password) { // login menu
        User user;
        if ((user = getUserByUsername(username)) == null || !user.getPassword().equals(password)) {
            return Response.LoginMenu.USERNAME_PASSWORD_DONT_MATCH;
        }
        setCurrentUser(user);
        Menu.setCurrentMenu(Menu.MenuType.MAIN_MENU);
        return Response.LoginMenu.LOGIN_SUCCESSFUL;
    }

    public static Response.ProfileMenu changePassword(String oldPW, String newPW) { // profile menu
        if (!currentUser.getPassword().equals(oldPW)) {
            return Response.ProfileMenu.WRONG_OLD_PASSWORD;
        }
        if (!IsPasswordValid(newPW)) {
            return Response.ProfileMenu.INVALID_NEW_PASSWORD_FORMAT;
        }
        if (!isPasswordStrong(newPW)) {
            return Response.ProfileMenu.WEAK_NEW_PASSWORD;
        }
        if (oldPW.equals(newPW)) {
            return Response.ProfileMenu.SAME_PASSWORD;
        }
        currentUser.setPassword(newPW);
        return Response.ProfileMenu.SUCCESSFUL_PASSWORD_CHANGE;
    }

    public static Response.ProfileMenu changeNickname(String nickname) {
        if (!IsNameValid(nickname)) {
            return Response.ProfileMenu.INVALID_NICKNAME_FORMAT;
        }
        if (getUserByNickname(nickname) != null) {
            return Response.ProfileMenu.NICKNAME_EXISTS;
        }
        currentUser.setNickname(nickname);
        return Response.ProfileMenu.SUCCESSFUL_NICKNAME_CHANGE;
    }

    public static Response.ProfileMenu removeUser(String password) {
        if (!currentUser.getPassword().equals(password)) {
            return Response.ProfileMenu.WRONG_PASSWORD;
        }
        users.remove(currentUser);
        currentUser = null;
        Menu.setCurrentMenu(Menu.MenuType.LOGIN_MENU);
        return Response.ProfileMenu.ACCOUNT_DELETED_SUCCESSFULLY;
    }

    public static ArrayList<User> getScoreboard() { // profile menu
        ArrayList<User> scoreboard = new ArrayList<>(users);
        Collections.sort(scoreboard, new User.compareByScore());
        return scoreboard;
    }

    public static Response.MainMenu logout() { // main menu
        currentUser = null;
        Menu.setCurrentMenu(Menu.MenuType.LOGIN_MENU);
        return Response.MainMenu.SUCCESSFUL_LOGOUT;
    }

    public static void saveUsers() { // should be called when exited
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/users.json");
            fileWriter.write(new Gson().toJson(users));
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error while Saving users");
            e.printStackTrace();
        }
    }

    public static void addUser(String username, String password, String nickname) {
        users.add(new User(username, password, nickname));
    }

}
