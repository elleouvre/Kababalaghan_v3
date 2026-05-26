package gamemodes.campaign;

import characters.Character;
import gamemodes.BattleSystem;
import util.Colors;
import util.Utils;
import java.util.Scanner;

public class CampaignManager {
    private Scanner scanner;
    private BattleSystem battleSystem;
    private Character savedPlayer = null;


    public CampaignManager(Scanner scanner) {
        this.scanner = scanner;
        this.battleSystem = new BattleSystem(scanner);
    }

    public void startFullCampaign() {
        displayCampaignBanner();
        System.out.print(Colors.MOON_GREY+"                                                Press [ENTER] to begin your journey...");
        scanner.nextLine();

        // ========== ACT 1 ==========
        boolean act1Success = runAct1WithRetry();
        if (!act1Success) {
            System.out.println("\n                                           Returning to the main menu...");
            return;
        }

        // ========== ACT 2 ==========
        if (!askToContinue("Act 2")) {
            return;
        }
        boolean act2Success = runAct2WithRetry();
        if (!act2Success) {
            System.out.println("\n                                           Returning to the main menu...");
            return;
        }

        // ========== ACT 3 ==========
        if (!askToContinue("Act 3")) {
            return;
        }
        boolean act3Success = runAct3WithRetry();
        if (!act3Success) {
            System.out.println("\n                                           Returning to the main menu...");
            return;
        }

        // ========== FULL COMPLETION ==========
        displayCompletion();
    }

    private boolean runAct1WithRetry() {
        while (true) {
            boolean success = runAct1();
            if (success) return true;
            if (!askForRetry("Act 1")) return false;
            // If retry, the loop continues
        }
    }

    private boolean runAct2WithRetry() {
        while (true) {
            boolean success = runAct2();
            if (success) return true;
            if (!askForRetry("Act 2")) return false;
            // If retry, the loop continues
        }
    }

    private boolean runAct3WithRetry() {
        while (true) {
            boolean success = runAct3();
            if (success) return true;
            if (!askForRetry("Act 3")) return false;
            // If retry, the loop continues
        }
    }

    private boolean runAct1() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(Colors.GOLD + "                                           ╔═══════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Utils.colorizeActWan("                                           ║                   "+Colors.BOLD+Colors.ITALIC+"ACT 1                   "+Colors.RESET+Colors.BOLD+"║"));
        System.out.println(Utils.colorizeActWan("                                           ║           Pagkukulam sa Korapsyon         ║"));
        System.out.println(Colors.GOLD + "                                           ╚═══════════════════════════════════════════╝" + Colors.RESET);

        // Create a new player character for Act 1
        boolean[] isHero = new boolean[1];
        savedPlayer = Character.chooseCharacter(scanner, isHero);

        Act1 act1 = new Act1(scanner);
        return act1.start(savedPlayer);
    }

    private boolean runAct2() {
        System.out.println(Colors.GOLD + "\n                                           ╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Utils.colorizeActToo("                                           ║                   "+Colors.BOLD+Colors.ITALIC+"ACT 2                   "+Colors.RESET+Colors.BOLD+"║"));
        System.out.println(Utils.colorizeActToo("                                           ║           Ang Pagbabalik ng Buwan                  ║"));
        System.out.println(Colors.GOLD + "                                           ╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        if (savedPlayer == null) {
            System.out.println(Colors.RED + "\n                                           [ERROR] Walang nakitang karakter! Nagre-restart ng Act 1." + Colors.RESET);
            return runAct1(); // Should not happen, but as a fallback
        }

        savedPlayer.resetAll(); // Reset for the new act

        Act2 act2 = new Act2(scanner);
        return act2.start(savedPlayer);
    }

    private boolean runAct3() {
        System.out.println(Colors.PURPLE + "\n                                           ╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Utils.colorizeActTree("                                           ║                   "+Colors.BOLD+Colors.ITALIC+"ACT 3                   "+Colors.RESET+Colors.BOLD+"║"));
        System.out.println(Utils.colorizeActTree("                                           ║           Ang Huling Pagliligtas                   ║"));
        System.out.println(Colors.PURPLE + "                                           ╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        if (savedPlayer == null) {
            System.out.println(Colors.RED + "\n                                           [ERROR] Walang nakitang karakter! Nagre-restart ng Act 1." + Colors.RESET);
            return runAct1(); // Should not happen, but as a fallback
        }

        savedPlayer.resetAll(); // Reset for the new act

        Act3 act3 = new Act3(scanner);
        return act3.start(savedPlayer);
    }

    private boolean askToContinue(String actName) {
        System.out.println(Colors.GREEN + "\n                                           ✓ NAKUMPLETO MO ANG NAKARAANG ACT!" + Colors.RESET);
        System.out.print(Colors.YELLOW + "                                           Gusto mo bang magpatuloy sa " + actName + "? (Y/N): " + Colors.RESET);
        String choice = scanner.nextLine().trim().toUpperCase();
        if (!choice.equals("Y")) {
            System.out.println(Colors.CYAN + "\n                                           [SAVED] Ang iyong paglalakbay ay pansamantalang tumigil." + Colors.RESET);
            System.out.println("                                           Balik ka muli upang ipagpatuloy ang laban!");
            System.out.print("\n                                           Press Enter to return to menu...");
            scanner.nextLine();
            return false;
        }
        return true;
    }

    private boolean askForRetry(String actName) {
        System.out.println(Colors.YELLOW + "\n                                           ╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.RED + "                                           ║                   GAME OVER!                          ║" + Colors.RESET);
        System.out.println(Colors.YELLOW + "                                           ║           Natalo ka sa " + actName + "!                    ║" + Colors.RESET);
        System.out.println(Colors.YELLOW + "                                           ╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        System.out.println("\n" + Colors.CYAN + "                                           Gusto mo bang subukan ulit?" + Colors.RESET);
        System.out.print("                                            (Y/N): ");

        String choice = scanner.nextLine().trim().toUpperCase();
        return choice.equals("Y");
    }

    private void displayCampaignBanner() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(Colors.MOON_GREY+("                                           ╔════════════════════════════════════════════╗" + Colors.RESET));
        System.out.println(Colors.MOON_GREY+("                                           ║"+Colors.MOON_WHITE+Colors.BOLD+"     KABABALAGHAN: THE COMPLETE CAMPAIGN    "+Colors.RESET+Colors.MOON_GREY+"║"+Colors.RESET));
        System.out.println(Colors.MOON_GREY+("                                           ║"+Colors.MOON_WHITE+Colors.ITALIC+"             The Full Story                 "+Colors.RESET+Colors.MOON_GREY+"║"+Colors.RESET));
        System.out.println(Colors.MOON_GREY+("                                           ║"+Colors.YELLOW+Colors.ITALIC+"      ACT 1: Pagkukulam sa Korapsyon        "+Colors.RESET+Colors.MOON_GREY+"║"+Colors.RESET));
        System.out.println(Colors.MOON_GREY+("                                           ║"+Colors.BRONZE+Colors.ITALIC+"      ACT 2: Ang Pagbabalik ng Buwan        "+Colors.RESET+Colors.MOON_GREY+"║"+Colors.RESET));
        System.out.println(Colors.MOON_GREY+("                                           ║"+Colors.RED+Colors.ITALIC+"      ACT 3: Ang Huling Pagliligtas         "+Colors.RESET+Colors.MOON_GREY+"║"+Colors.RESET));
        System.out.println(Colors.MOON_GREY+("                                           ╚════════════════════════════════════════════╝"+Colors.RESET));
    }

    private void displayCompletion() {
        battleSystem.displayVictory(savedPlayer);

        System.out.println(Colors.GREEN + "\n                            ╔═══════════════════════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Utils.colorizeCompletion("                            ║                    CAMPAIGN COMPLETED!                             ║"));
        System.out.println(Colors.CYAN + "                            ║                                                                       ║" + Colors.RESET);
        System.out.println(Utils.colorizeCompletion("                            ║     Congratulations! Nakumpleto mo ang buong campaign!               ║"));
        System.out.println(Colors.CYAN + "                            ║                                                                       ║" + Colors.RESET);
        System.out.println(Utils.colorizeCompletion("                            ║     Naibalik mo ang liwanag sa mundo ng KABABALAGHAN.               ║"));
        System.out.println(Utils.colorizeCompletion("                            ║     Ang mga buwan ay muling nagniningning sa kalangitan.           ║"));
        System.out.println(Colors.CYAN + "                            ║                                                                       ║" + Colors.RESET);
        System.out.println(Utils.colorizeCompletion("                            ║              Salamat sa paglalaro ng KABABALAGHAN!                   ║"));
        System.out.println(Colors.GREEN + "                            ╚═══════════════════════════════════════════════════════════════════╝" + Colors.RESET);
    }
}