package utils;

public final class Settings {
    private Settings() {}
    public static String baseUrl() { return System.getProperty("baseUrl", "https://demoqa.com").replaceAll("/+$", ""); }
    public static boolean headless() { return Boolean.parseBoolean(System.getProperty("headless", "false")); }
    public static String browser() { return System.getProperty("browser", "chrome").toLowerCase(); }
    public static int timeoutSeconds() { return Integer.parseInt(System.getProperty("timeoutSeconds", "15")); }
}
