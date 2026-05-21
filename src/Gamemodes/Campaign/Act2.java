package Gamemodes.Campaign;

import characters.Character;
import characters.CharacterFactory;
import Gamemodes.BattleSystem;
import java.util.*;

public class Act2 {
    private Scanner scanner;
    private BattleSystem battleSystem;
    private Random rand = new Random();


    private int hoursLeft = 24;
    private int moonsRetrieved = 0;
    private boolean[] domainsExplored = new boolean[6];

    public Act2(Scanner scanner) {
        this.scanner = scanner;
        this.battleSystem = new BattleSystem(scanner);
    }


    public boolean start(Character player) {
        displayIntro();

        while (hoursLeft > 0 && player.isAlive() && moonsRetrieved < 6 && !allDomainExplored()) {
            displayMenu();

            int domainIndex = getDomain();
            if (domainIndex == -1) continue;


            processExplorationTime();
            displayExplore(domainIndex);

            // Ambush Checker Domain 2 & 5
            if (!ifAmbush(domainIndex, player)) {
                return false;
            }
            //getMoon
            executeScavengeLogic(domainIndex);
        }
        return displayOutro(player);
    }


    private int getDomain() {
        System.out.print("Piliin ang kweba (1-6): ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            int targetIndex = choice - 1;
            if (choice >= 1 && choice <= 6 && !domainsExplored[targetIndex]) {
                return targetIndex;
            }
            System.out.println("Invalid choice o nagamit na ang kwebang iyan!");
        } catch (Exception e) {
            System.out.println("Pumili lamang ng tamang numero.");
            scanner.nextLine();
        }
        return -1;
    }

    private void processExplorationTime() {
        int cost = rand.nextInt(3) + 1; // 1-3 Hours
        hoursLeft -= cost;
        System.out.println("\nNaglakad ka ng malalim. Lumipas ang " + cost + " oras.");
    }

    private boolean ifAmbush(int caveIndex, Character player) {
        if (caveIndex == 1 || caveIndex == 4) {
            System.out.println("\nWARNING: Mapanganib ang domain na ito! Sinusuri ang paligid...");

            if (rand.nextBoolean()) {
                System.out.println("AMBUSH! May Villain patungo sayo!");
                Character ambushEnemy = CharacterFactory.generateRandomEnemy(false);
                System.out.print("Press Enter to start the Battle. ");
                scanner.nextLine();

                battleSystem.startSingleplayer(player, ambushEnemy);
                player.resetAll();
            } else {
                System.out.println("Ligtas! Walang kalaban na nakaabang.");
            }
            if (!player.isAlive()) return false;
        }
        return true;
    }

    private void executeScavengeLogic(int index) {
        domainsExplored[index] = true;

        System.out.println("\nMay nakita kang piraso ng Buwan ni Mayari!");
        System.out.print("Press Enter to get to the moon ");
        scanner.nextLine();

        moonsRetrieved++;
        System.out.println("[SUCCESS] Nakuha mo ang 1 garantisadong buwan!");

        if (index != 1 && index != 3) {
            if (rand.nextBoolean()) {
                moonsRetrieved++;
                System.out.println("💎 BONUS! May nakatago pang extra moon piece dito! (+1 Moon)");
            }
        } else {
            System.out.println("[INFO] Tuyo ang kwebang ito. Walang nakatagong extra pools dito.");
        }
    }

    private boolean allDomainExplored() {
        for (boolean e : domainsExplored) if (!e) return false;
        return true;
    }

    public void displayIntro() {
        System.out.println("\n               ARCADE CAMPAIGN: ACT 2                    ");
        System.out.println("             --- ANG PAGNANAKAW NG BUWAN ---            ");
        System.out.println("\nMayroon kang 24 na oras bago maglaho ang gabi.");
        System.out.print("Press Enter upang simulan ang pag-explore... ");
        scanner.nextLine();
    }

    public void displayMenu() {
        System.out.println();
        System.out.println("ORAS: " + hoursLeft + " Oras na Natitira | BUWAN: " + moonsRetrieved + "/6");
        System.out.println();
        System.out.println(" 1. Pugad sa Kalaliman (Aswang's Lair) " + (domainsExplored[0] ? "             [✔ DONE]" : "            [OPEN]"));
        System.out.println(" 2. Pinagsumpaang Balete (Kapre's Canopy) " + (domainsExplored[1] ? "          [✔ DONE]" : "         [OPEN]"));
        System.out.println(" 3. Putol na Kalangitan (Manananggal's Roost) " + (domainsExplored[2] ? "      [✔ DONE]" : "     [OPEN]"));
        System.out.println(" 4. Lawa ng Nagbabagang Kaluluwa (Santelmo's Pit) " + (domainsExplored[3] ? "  [✔ DONE]" : " [OPEN]"));
        System.out.println(" 5. Gubat ng Naligaw na Landas (Tikbalang's Maze) " + (domainsExplored[4] ? "  [✔ DONE]" : " [OPEN]"));
        System.out.println(" 6. Sinaunang Libingan ng mga Sinaunang Datu " + (domainsExplored[5] ? "       [✔ DONE]" : "      [OPEN]"));
    }

    public void displayExplore(int index) {
        System.out.print("Exploring Domain " + (index + 1));
        for (int i = 0; i < 3; i++) {
            //add delay text
            System.out.print(".");
        }
        System.out.println();
    }

    public boolean displayOutro(Character player) {
        if (player.isAlive()) {
            System.out.println("\n                  ACT 2 COMPLETED!                       ");
            System.out.println("Nakalabas ka nang buhay kasama ang " + moonsRetrieved + " na buwan.");
            System.out.println("Ngunit isang malaking anino ang lumulubog sa dagat...");
            System.out.print("Press Enter para magpatuloy sa Act 3 Finale... ");
            scanner.nextLine();
            return true;
        }
        System.out.println("\n[GAME OVER] Dumidilim ang pananaw mo.");
        return false;
    }
}