package util;

public class Utils {
    public static void delay(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    //displayMenu
    public static String colorizeBlocks(String line) {
        return line
                .replace("✠", Colors.BLACK + "✠" + Colors.RESET)
                .replace("—", Colors.BLACK + "—" + Colors.RESET)

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

    public static String colorizeMenu(String line){
        return line
                .replace("║", Colors.CYAN + "║" + Colors.RESET)
                .replace("══", Colors.CYAN + "══" + Colors.RESET)
                .replace("╔", Colors.CYAN + "╔" + Colors.RESET)
                .replace("╗", Colors.CYAN + "╗" + Colors.RESET)
                .replace("╚", Colors.CYAN + "╚" + Colors.RESET)
                .replace("╝", Colors.CYAN + "╝" + Colors.RESET)
                .replace("GAME MODES:", Colors.BLUE + "GAME MODES:" + Colors.RESET)
                .replace("[1] Arcade Campaign", Colors.PURPLE + "[1] Arcade Campaign" + Colors.RESET)
                .replace("[2] Singleplayer (VS Computer) ", Colors.PURPLE + "[2] Singleplayer (VS Computer) " + Colors.RESET)
                .replace("[3] Multiplayer (PVP)", Colors.PURPLE + "[3] Multiplayer (PVP)" + Colors.RESET)
                .replace("[X] Back to Main Menu", Colors.RED + "[X] Back to Main Menu" + Colors.RESET)
                .replace("Choose mode:", Colors.WHITE+ "Choose mode:" + Colors.RESET);
    }

    public static String colorizeSingp(String line) {
        return line
                .replace("║", Colors.CYAN + "║" + Colors.RESET)
                .replace("══", Colors.CYAN + "══" + Colors.RESET)
                .replace("╔", Colors.CYAN + "╔" + Colors.RESET)
                .replace("╗", Colors.CYAN + "╗" + Colors.RESET)
                .replace("╚", Colors.CYAN + "╚" + Colors.RESET)
                .replace("╝", Colors.CYAN + "╝" + Colors.RESET)
                .replace("SINGLEPLAYER MODE", Colors.CYAN + "SINGLEPLAYER MODE" + Colors.RESET)
                .replace("Press Enter to return to menu...", Colors.CYAN + "Press Enter to return to menu..." + Colors.RESET);
    }

    public static String colorizeMulti(String line) {
        return line
                .replace("║", Colors.CYAN + "║" + Colors.RESET)
                .replace("══", Colors.CYAN + "══" + Colors.RESET)
                .replace("╔", Colors.CYAN + "╔" + Colors.RESET)
                .replace("╗", Colors.CYAN + "╗" + Colors.RESET)
                .replace("╚", Colors.CYAN + "╚" + Colors.RESET)
                .replace("╝", Colors.CYAN + "╝" + Colors.RESET);
    }

    public static String colorizeBorders(String line){
        return line
                .replace("║", Colors.BLUE + "║" + Colors.RESET)
                .replace("ACT 1: Pagkukulam sa Korapsyon", Colors.YELLOW + "ACT 1: Pagkukulam sa Korapsyon" + Colors.RESET)
                .replace("ACT 2: Ang Pagbabalik ng Buwan", Colors.BRONZE + "ACT 2: Ang Pagbabalik ng Buwan" + Colors.RESET)
                .replace("ACT 3: Ang Huling Pagliligtas", Colors.MAROON + "ACT 3: Ang Huling Pagliligtas" + Colors.RESET);
    }

    public static String colorizeActWan(String line){
        return line
                .replace("║", Colors.GOLD + "║" + Colors.RESET)
                .replace("ACT 1", Colors.YELLOW + "ACT 1" + Colors.RESET)
                .replace("Pagkukulam sa Korapsyon", Colors.YELLOW + "Pagkukulam sa Korapsyon" + Colors.RESET)
                .replace("ARCADE CAMPAIGN:", Colors.TEAL + "ARCADE CAMPAIGN:" + Colors.RESET)
                .replace("PAGKUKULAM SA KORAPSYON", Colors.TEAL + "PAGKUKULAM SA KORAPSYON" + Colors.RESET)
                .replace("Ang mga Hari ng Lupa at Langit ay nawalan ng kontrol", Colors.TEAL + "Ang mga Hari ng Lupa at Langit ay nawalan ng kontrol" + Colors.RESET)
                .replace("GOAL: TULONGIN MO ANG MGA HEROES AT ILIGTAS MO SILA!", Colors.YELLOW + "GOAL: TULONGIN MO ANG MGA HEROES AT ILIGTAS MO SILA!" + Colors.RESET);
    }

    public static String colorizeActToo(String line){
        return line
                .replace("║", Colors.GOLD + "║" + Colors.RESET)
                .replace("ACT 2", Colors.YELLOW + "ACT 2" + Colors.RESET)
                .replace("Ang Pagbabalik ng Buwan", Colors.YELLOW + "Ang Pagbabalik ng Buwan" + Colors.RESET)
                .replace("ARCADE CAMPAIGN:", Colors.YELLOW + "ARCADE CAMPAIGN:" + Colors.RESET)
                .replace("ANG PAGBABALIK NG BUWAN", Colors.YELLOW + "ANG PAGBABALIK NG BUWAN" + Colors.RESET)
                .replace("UNANG BUWAN: PUGAD NG ASWANG", Colors.YELLOW + "UNANG BUWAN: PUGAD NG ASWANG" + Colors.RESET)
                .replace("PANGALAWANG BUWAN: KALANGITAN", Colors.YELLOW + "PANGALAWANG BUWAN: KALANGITAN" + Colors.RESET)
                .replace("HULING BUWAN: GUBAT NG TIKBALANG", Colors.YELLOW + "HULING BUWAN: GUBAT NG TIKBALANG" + Colors.RESET);
    }

    public static String colorizeActTree(String line){
        return line
                .replace("ACT 3 ",Colors.YELLOW + "ACT 3" + Colors.RESET)
                .replace("ANG HULING PAGLILIGTAS",Colors.YELLOW + "ANG HULING PAGLILIGTAS" + Colors.RESET)
                .replace("STAGE 1: ANG PINTUAN NG KAWALAN",Colors.YELLOW + "STAGE 1: ANG PINTUAN NG KAWALAN" + Colors.RESET)
                .replace("Isang anino ang humaharang sa iyong landas.",Colors.YELLOW + "Isang anino ang humaharang sa iyong landas." + Colors.RESET)
                .replace("STAGE 2: Ang Higanteng Kumakain ng Buwan",Colors.YELLOW + "STAGE 2: Ang Higanteng Kumakain ng Buwan" + Colors.RESET)
                .replace("Ang higanteng si Bakunawa ay nakabukas ang bibig sa huling buwan!",Colors.YELLOW + "Ang higanteng si Bakunawa ay nakabukas ang bibig sa huling buwan!" + Colors.RESET)
                .replace("COMPLETED!",Colors.YELLOW + "COMPLETED!" + Colors.RESET);
    }
    public static String colorizeCompletion(String line){
        return line
                .replace("CAMPAIGN COMPLETED!",Colors.YELLOW + "COMPLETED!" + Colors.RESET)
                .replace("Congratulations! Nakumpleto mo ang buong campaign!",Colors.YELLOW + "Congratulations! Nakumpleto mo ang buong campaign!" + Colors.RESET)
                .replace("Naibalik mo ang liwanag sa mundo ng KABABALAGHAN.",Colors.YELLOW + "Naibalik mo ang liwanag sa mundo ng KABABALAGHAN." + Colors.RESET)
                .replace("Ang mga buwan ay muling nagniningning sa kalangitan.",Colors.YELLOW + "Ang mga buwan ay muling nagniningning sa kalangitan." + Colors.RESET)
                .replace("Salamat sa paglalaro ng KABABALAGHAN!",Colors.YELLOW + "Salamat sa paglalaro ng KABABALAGHAN!" + Colors.RESET);
    }
}