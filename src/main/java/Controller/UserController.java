package Controller;

import Model.User;
import enums.Responses.Response;

import java.util.ArrayList;
import java.util.HashMap;

public class UserController {
    private static ArrayList<User> users;
    private static User user;

    {
        // TODO: 4/17/2022 : set users from database
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        UserController.users = users;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserController.user = user;
    }

    public static User getUserByName(String username){

    }

    private static boolean IsPasswordValid(String password){

    }

    private static boolean IsNameValid(String name){

    }

    public static Response.LoginMenu register(String username, String password, String nickname){

    }

    public static Response.LoginMenu login(String username, String password){

    }

    public static Response.ProfileMenu changePassword(String oldPW, String newPW){

    }

    public static Response.ProfileMenu changeNickname(String nickname){

    }

    public static Response.ProfileMenu removeUser(String password){

    }

    public static ArrayList<User> getScoreboard(){
        // TODO: 4/17/2022 sort users
    }

    public static Response.MainMenu logout(){

    }

}
