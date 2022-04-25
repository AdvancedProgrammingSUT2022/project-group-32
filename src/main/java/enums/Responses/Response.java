package enums.Responses;

public class Response {
    // enums with $ have dynamic parts
    public enum GameMenu {
        INVALID_COMMAND("Invalid Command"),
        GAME_CREATED("game created"),

        UNIT_SELECTED("Unit selected successfully"),
        CITY_SELECTED("City selected successfully"),
        TURN_PASSED("turn passed successfully"),
        // CURRENT MENU
        CURRENT_MENU("Game menu"),

        // MOVE MAP
        AMOUNT_IS_NOT_POSITIVE("map movement amount must be positive"),
        SUCCESSFUL_MOVE("map moved successfully"),
        MOVEMENT_OUT_OF_RANGE("can't move outside of map");

        private final String message;

        GameMenu(String message) {
            this.message = message;
        }

        // can get a string and adds it to corresponding location in the response message
        public String getString(String... dynamicSubstring) {
            String messageText = this.message;
            if (dynamicSubstring.length == 0 || !messageText.contains("$")) return this.message;
            return messageText.replaceFirst("\\$", dynamicSubstring[0]);
        }
    }


    public enum LoginMenu {
        INVALID_COMMAND("Invalid Command"),

        // LOGIN
        USERNAME_PASSWORD_DONT_MATCH("Username and password don't match"), // for nonexistence username and mismatch
        LOGIN_SUCCESSFUL("Logged in successfully"),

        // REGISTER
        INVALID_USERNAME_FORMAT("Username format is invalid"),
        INVALID_PASSWORD_FORMAT("Password format is invalid"),
        INVALID_NICKNAME_FORMAT("Nickname format is invalid"),
        WEAK_PASSWORD("Password is weak"),
        USERNAME_EXISTS("Username \"$\" already exists"),
        NICKNAME_EXISTS("Nickname \"$\" already exists"),
        REGISTER_SUCCESSFUL("User registered successfully"),

        // CURRENT MENU
        CURRENT_MENU("Login menu");

        private final String message;

        LoginMenu(String message) {
            this.message = message;
        }

        /**
         * can get a string and adds it to corresponding location in the response message
         */
        public String getString(String... dynamicSubstring) {
            String messageText = this.message;
            if (dynamicSubstring.length == 0 || !messageText.contains("$")) return this.message;
            return messageText.replaceFirst("\\$", dynamicSubstring[0]);
        }
    }

    public enum ProfileMenu {
        INVALID_COMMAND("Invalid Command"),

        // MAIN MENU RETURN
        ENTERED_MAIN_MENU("Entered main menu"),

        // CHANGE PASSWORD
        INVALID_NEW_PASSWORD_FORMAT("New password format is invalid"),
        WRONG_OLD_PASSWORD("Old password is wrong"),
        WEAK_NEW_PASSWORD("New password is weak"),
        SUCCESSFUL_PASSWORD_CHANGE("Password changed successfully"),

        // CHANGE NICKNAME
        INVALID_NICKNAME_FORMAT("Nickname format is invalid"),
        NICKNAME_EXISTS("Nickname \"$\" already exists"),
        SUCCESSFUL_NICKNAME_CHANGE("Nickname changed successfully"),

        // DELETE ACCOUNT
        WRONG_PASSWORD("Password is wrong"),
        ACCOUNT_DELETED_SUCCESSFULLY("Account deleted successfully"),

        // SHOW SCOREBOARD
        // no response

        // CURRENT MENU
        CURRENT_MENU("Profile menu");

        private final String message;

        ProfileMenu(String message) {
            this.message = message;
        }

        public String getString(String... dynamicSubstring) {
            String messageText = this.message;
            if (dynamicSubstring.length == 0 || !messageText.contains("$")) return this.message;
            return messageText.replaceFirst("\\$", dynamicSubstring[0]);
        }
    }

    public enum MainMenu {
        INVALID_COMMAND("Invalid Command"),

        // MENU NAVIGATION
        MENU_NAVIGATION_NOT_POSSIBLE("Menu navigation not possible"),
        SUCCESSFULLY_ENTERED_GAME("Entered Game menu"),
        SUCCESSFULLY_ENTERED_PROFILE("Entered Profile menu"),

        // LOGOUT
        SUCCESSFUL_LOGOUT("Logged out successfully "),

        // CURRENT MENU
        CURRENT_MENU("Main menu"),

        // NEW GAME
        NEW_GAME_STARTED("new game created"),
        NONEXISTENCE_USERS("user(s) \"$\" not found");

        private final String message;

        MainMenu(String message) {
            this.message = message;
        }

        // can get a string and adds it to corresponding location in the response message
        public String getString(String... dynamicSubstring) {
            String messageText = this.message;
            if (dynamicSubstring.length == 0 || !messageText.contains("$")) return this.message;
            return messageText.replaceFirst("\\$", dynamicSubstring[0]);
        }
    }
}
