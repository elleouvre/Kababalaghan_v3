package Gamemodes.Campaign;

import characters.Character;
import util.Colors;
import java.util.Scanner;

public class CampaignManager {
    private Scanner scanner;
    private Character savedPlayer = null;
    private boolean act1Completed = false;
    private boolean act2Completed = false;

    public CampaignManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startFullCampaign() {
        boolean campaignRunning = true;

        while (campaignRunning) {
            displayCampaignBanner();
            System.out.print("Press Enter to begin your journey...");
            scanner.nextLine();

            // ========== ACT 1 ==========
            if (!act1Completed) {
                boolean act1Success = runAct1();

                if (!act1Success) {
                    if (!askForRetry("Act 1")) {
                        return; // Return to menu
                    }

                }
                act1Completed = true;
            }

            // Ask to continue to Act 2
            if (!act2Completed) {
                if (!askToContinue("Act 2")) {
                    System.out.println(Colors.CYAN + "\n[SAVED] Ang iyong paglalakbay ay pansamantalang tumigil." + Colors.RESET);
                    System.out.println("Balik ka muli upang ipagpatuloy ang laban!");
                    System.out.print("\nPress Enter to return to menu...");
                    scanner.nextLine();
                    return;
                }

                boolean act2Success = runAct2();

                if (!act2Success) {
                    if (!askForRetry("Act 2")) {
                        return; // Return to menu
                    }
                    continue; // Restart Act 2
                }
                act2Completed = true;
            }

            // Ask to continue to Act 3
            if (!askToContinue("Act 3")) {
                System.out.println(Colors.CYAN + "\n[SAVED] Ang iyong paglalakbay ay pansamantalang tumigil." + Colors.RESET);
                System.out.println("Balik ka muli upang ipagpatuloy ang laban!");
                System.out.print("\nPress Enter to return to menu...");
                scanner.nextLine();
                return;
            }

            // ========== ACT 3 ==========
            boolean act3Success = runAct3();

            if (!act3Success) {
                if (!askForRetry("Act 3")) {
                    return; // Return to menu
                }
                continue; // Restart Act 3
            }

            // ========== FULL COMPLETION ==========
            displayCompletion();
            campaignRunning = false;
        }
    }

    private boolean runAct1() {
        System.out.println(Colors.PURPLE + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║                    ACT 1                          ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║           Pagkukulam sa Korapsyon                 ║" + Colors.RESET);
        System.out.println(Colors.PURPLE + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        Act1 act1 = new Act1(scanner);
        Character player = act1.start();  // Now this works!

        if (player == null) {
            return false;  // Player died
        }

        savedPlayer = player;  // Save for Acts 2 & 3
        return true;
    }

    private boolean runAct2() {
        System.out.println(Colors.PURPLE + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║                    ACT 2                          ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║           Ang Pagbabalik ng Buwan                  ║" + Colors.RESET);
        System.out.println(Colors.PURPLE + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        if (savedPlayer == null) {
            System.out.println(Colors.RED + "\n[ERROR] Walang nakitang karakter!" + Colors.RESET);
            return false;
        }

        Act2 act2 = new Act2(scanner);
        act2.start(savedPlayer);  // Pass the saved player

        if (!savedPlayer.isAlive()) {
            return false;
        }

        return true;
    }

    private boolean runAct3() {
        System.out.println(Colors.PURPLE + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║                    ACT 3                          ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║           Ang Huling Pagliligtas                   ║" + Colors.RESET);
        System.out.println(Colors.PURPLE + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        if (savedPlayer == null) {
            System.out.println(Colors.RED + "\n[ERROR] Walang nakitang karakter!" + Colors.RESET);
            return false;
        }

        Act3 act3 = new Act3(scanner);
        act3.start(savedPlayer);

        if (!savedPlayer.isAlive()) {
            return false;
        }

        return true;
    }

    private boolean askToContinue(String actName) {
        System.out.println(Colors.GREEN + "\n✓ NAKUMULETO MO ANG ACT 1!" + Colors.RESET);
        System.out.print(Colors.YELLOW + "Gusto mo bang magpatuloy sa " + actName + "? (1=Oo / 2=Hindi): " + Colors.RESET);
        String choice = scanner.nextLine().trim();
        return choice.equals("1");
    }

    private boolean askForRetry(String actName) {
        System.out.println(Colors.YELLOW + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.RED + "║                   GAME OVER!                        ║" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║           Natalo ka sa " + actName + "!                ║" + Colors.RESET);
        System.out.println(Colors.YELLOW + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        System.out.println("\n" + Colors.CYAN + "Gusto mo bang subukan ulit?" + Colors.RESET);
        System.out.println(" [1] Oo - Subukan muli ang " + actName);
        System.out.println(" [2] Hindi - Bumalik sa menu");
        System.out.print("\nChoice: ");

        String choice = scanner.nextLine().trim();
        return choice.equals("1");
    }

    private void displayCampaignBanner() {
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║                    KABABALAGHAN: THE COMPLETE CAMPAIGN            ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║                         The Full Story                            ║" + Colors.RESET);
        System.out.println(Colors.PURPLE + "║                                                                   ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║   ACT 1: Pagkukulam sa Korapsyon                                  ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║   ACT 2: Ang Pagbabalik ng Buwan                                  ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║   ACT 3: Ang Huling Pagliligtas                                   ║" + Colors.RESET);
        System.out.println(Colors.PURPLE + "║                                                                   ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════════════════════╝" + Colors.RESET);
    }

    private void displayCompletion() {
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║                    CAMPAIGN COMPLETED!                             ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║                                                                       ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║     Congratulations! Nakumpleto mo ang buong campaign!               ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║                                                                       ║" + Colors.RESET);
        System.out.println(Colors.PURPLE + "║     Naibalik mo ang liwanag sa mundo ng KABABALAGHAN.               ║" + Colors.RESET);
        System.out.println(Colors.PURPLE + "║     Ang mga buwan ay muling nagniningning sa kalangitan.           ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║                                                                       ║" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║              Salamat sa paglalaro ng KABABALAGHAN!                   ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════════════════════╝" + Colors.RESET);
    }
}