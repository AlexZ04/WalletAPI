package ru.cft.template.configuration;

import org.springframework.context.annotation.Bean;
import org.mindrot.jbcrypt.BCrypt;

public class Security {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String enteredPassword, String hashedPassword) {
        return BCrypt.checkpw(enteredPassword, hashedPassword);
    }
}
