package seng201.team0.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service class which validates name entry from setup_screen_fxml.
 */
public class NameService {
    /**
     * Checks whether name contains between 3 and 15 characters and contains no special characters.
     * Run from onStartClicked method in SetupController.java
     * @param name Player name entered during game setup
     * @return true if name is alphanumeric and between 3 and 15 characters, else, returns false
     */
    public boolean isValidName(String name) {
        Pattern allowed = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher hasOnlyAllowed = allowed.matcher(name);
        if ((name.length() >= 3) && (name.length() <= 15) && hasOnlyAllowed.find()) {
            return true;
        }
        return false;
    }
}
