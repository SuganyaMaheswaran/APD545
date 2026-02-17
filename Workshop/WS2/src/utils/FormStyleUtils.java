package utils;

import javafx.scene.control.Control;
import javafx.scene.Node;

public class FormStyleUtils {

    public static final String BASE_STYLE = "-fx-background-color: white; -fx-border-radius: 5; -fx-pref-height: 35;";

    public static final String ERROR_STYLE = BASE_STYLE + "-fx-border-color: red;";

    public static void reset(Control control) {
        control.setStyle(BASE_STYLE);
    }

    public static void markError(Control control) {
        control.setStyle(ERROR_STYLE);
    }

    public static void resetAll(Control... controls) {
        for (Control c : controls) {
            reset(c);
        }
    }
}