package ru.cft.template.utility;

import org.mindrot.jbcrypt.BCrypt;

public class SecurityUtility {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String enteredPassword, String hashedPassword) {
        return BCrypt.checkpw(enteredPassword, hashedPassword);
    }
}
