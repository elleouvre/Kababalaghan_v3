package util;

public class Utils {
    public static void delay(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public static String colorizeBlocks(String line) {
        return line
                .replace("█", Colors.BLACK + "█" + Colors.RESET)
                .replace("▓", Colors.BLACK + "▓" + Colors.RESET)
                .replace("▒", Colors.BLACK + "▒" + Colors.RESET)
                .replace("░", Colors.BLACK + "░" + Colors.RESET)
                .replace("▀", Colors.BLACK + "▀" + Colors.RESET)
                .replace("▄", Colors.BLACK + "▄" + Colors.RESET)
                .replace("▐▌", Colors.BLACK + "▐▌" + Colors.RESET)

                .replace("✠", Colors.MOON_SILVER + "✠" + Colors.RESET)
                .replace("—", Colors.MOON_GREY + "—" + Colors.RESET)

                .replace("╔", Colors.SILVER + "╔" + Colors.RESET)
                .replace("╗", Colors.SILVER + "╗" + Colors.RESET)
                .replace("╚", Colors.SILVER + "╚" + Colors.RESET)
                .replace("╝", Colors.SILVER + "╝" + Colors.RESET)
                .replace("═", Colors.SILVER + "═" + Colors.RESET)
                .replace("║", Colors.SILVER + "║" + Colors.RESET);
    }

    public static String colorizeMoon(String line) {
        return line
                .replace("@", Colors.MOON_WHITE + "@" + Colors.RESET)
                .replace("#", Colors.MOON_SILVER + "#" + Colors.RESET)
                .replace("%", Colors.MOON_SILVER + "%" + Colors.RESET)
                .replace("*", Colors.MOON_SILVER + "*" + Colors.RESET)
                .replace("+", Colors.MOON_WHITE + "+" + Colors.RESET)
                .replace("=", Colors.MOON_WHITE + "=" + Colors.RESET)
                .replace(":", Colors.MOON_GREY + ":" + Colors.RESET)
                .replace(".", Colors.MOON_GREY + "." + Colors.RESET)
                .replace("-", Colors.MOON_GREY + "-" + Colors.RESET)
                .replace("║", Colors.SILVER + "║" + Colors.RESET);
    }
}