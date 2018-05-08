package se.frost.falldetectionproxyapi.utils;

import se.frost.falldetectionproxyapi.entities.User;

import java.util.ArrayList;

public class UserTestUtils {

    public final static String TEST_USER_UNAME = "pebo0602";
    public final static String TEST_USER_PASSWORD = "password";
    public final static String TEST_USER_FIRST_NAME = "Testuser";
    public final static String TEST_USER_LAST_NAME = "Lastname";
    public final static User TEST_USER_WITH_ID;
    public final static User TEST_USER_WITHOUT_ID;

    static {
        TEST_USER_WITH_ID = testUserWithId();
        TEST_USER_WITHOUT_ID = testUserWithoutId();
    }

    public static User testUserWithId() {
        User user = testUserWithoutId();
        user.setId(1);
        return user;
    }

    public static User testUserWithoutId() {
        User user = new User(TEST_USER_UNAME, TEST_USER_PASSWORD, TEST_USER_FIRST_NAME, TEST_USER_LAST_NAME, new ArrayList<>());
        user.setId(1);
        return user;
    }

}
