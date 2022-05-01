import Controller.UserController;
import Model.User;
import enums.Responses.Response;
import org.junit.Assert;
import org.junit.Test;

import static enums.Responses.Response.ProfileMenu.*;

public class UserControllerTest {

    User user = new User("username", "password", "niki");

    @Test
    public void testIsPasswordStrong() {
        Assert.assertFalse(UserController.isPasswordStrong("password"));
        Assert.assertFalse(UserController.isPasswordStrong("password1"));
        Assert.assertFalse(UserController.isPasswordStrong("password1%"));
        Assert.assertTrue(UserController.isPasswordStrong("Password123%"));
    }

    @Test
    public void testChangePassword() {
        String oldPass = "password";
        String newPass = "pP123123%";
        UserController.setCurrentUser(user);
        Assert.assertEquals(UserController.changePassword("wrongPass", "pass"), Response.ProfileMenu.WRONG_OLD_PASSWORD);
        Assert.assertEquals(UserController.changePassword(oldPass, " sadf sadf sadf"), INVALID_NEW_PASSWORD_FORMAT);
        Assert.assertEquals(UserController.changePassword(oldPass, "123"), WEAK_NEW_PASSWORD);
        Assert.assertEquals(UserController.changePassword(oldPass, "pP123123%"), SUCCESSFUL_PASSWORD_CHANGE);
    }
}
