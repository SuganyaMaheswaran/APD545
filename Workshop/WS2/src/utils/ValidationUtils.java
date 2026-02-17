package utils;

import java.time.LocalDate;
import java.time.Year;

public final class ValidationUtils {

    // Private constructor prevents instantiation
    private ValidationUtils() {
    }

    /*
     * =========================
     * Basic String Validation
     * =========================
     */

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static boolean isNotEmpty(String text) {
        return !isNullOrEmpty(text);
    }

    /*
     * =========================
     * Number Validation
     * =========================
     */

    public static boolean isDouble(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPositiveDouble(String text) {
        try {
            return Double.parseDouble(text) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPositiveInteger(String text) {
        try {
            return Integer.parseInt(text) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * =========================
     * Vehicle-Specific Validation
     * =========================
     */

    public static boolean isValidYear(String text) {
        try {
            int year = Integer.parseInt(text);
            int currentYear = Year.now().getValue();
            return year >= 1900 && year <= currentYear;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * =========================
     * Date Validation
     * =========================
     */

    public static boolean isValidDateRange(LocalDate start, LocalDate end) {
        return start != null &&
                end != null &&
                !end.isBefore(start);
    }

    /*
     * =========================
     * Safe Parsing (Optional but Useful)
     * =========================
     */

    public static Double parseDoubleOrNull(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer parseIntegerOrNull(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
