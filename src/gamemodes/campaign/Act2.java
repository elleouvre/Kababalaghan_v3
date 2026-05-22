package gamemodes.campaign;

import characters.Character;
import characters.villains.*;
import gamemodes.BattleSystem;
import util.Utils;
import util.Colors;

import java.util.*;

public class Act2 {
    private Scanner scanner;
    private BattleSystem battleSystem;
    private Random rand = new Random();

    // Track moons collected
    private int moonsCollected = 0;
    private final int TOTAL_MOONS = 3;  // 3 boss fights = 3 moons

    public Act2(Scanner scanner) {
        this.scanner = scanner;
        this.battleSystem = new BattleSystem(scanner);
    }

    public boolean start(Character player) {
        displayIntro();

        // Reset moon count
        moonsCollected = 0;

        // STAGE 1: First Moon - Aswang
        displayFight1();
        Character aswang = new Aswang();
        boolean battle1Won = battleSystem.startSingleplayer(player, aswang);
        if (!battle1Won) { return false; } // Player exited
        if (!player.isAlive()) {
            System.out.println("\n[GAME OVER] Ang unang buwan ay nanatiling nakatago sa dilim...");
            return false; // Player died
        }
        displayWin1(player, aswang);
        player.resetAll();

        // STAGE 2: Second Moon - Manananggal
        displayFight2();
        Character manananggal = new Mananananggal();
        boolean battle2Won = battleSystem.startSingleplayer(player, manananggal);
        if (!battle2Won) { return false; } // Player exited
        if (!player.isAlive()) {
            System.out.println("\n[GAME OVER] Ang pangalawang buwan ay nalunod sa dugo...");
            return false; // Player died
        }
        displayWin2(player, manananggal);
        player.resetAll();

        // STAGE 3: Final Moon - Tikbalang (Guardian)
        displayFight3();
        Character tikbalang = new Tikbalang();
        boolean battle3Won = battleSystem.startSingleplayer(player, tikbalang);
        if (!battle3Won) { return false; } // Player exited
        if (!player.isAlive()) {
            System.out.println("\n[GAME OVER] Hindi mo nabawi ang huling buwan...");
            return false; // Player died
        }

        displayWin3();
        displayOutro();
        return true; // Act completed successfully
    }

    public void displayIntro() {
        System.out.println("\n         ARCADE CAMPAIGN: ACT 2            ");
        System.out.println("         ANG PAGBABALIK NG BUWAN           ");
        Utils.delay(900);
        System.out.println("Matapos iligtas si Apolaki, tumulong ka ngayon kay Mayari.");
        Utils.delay(1000);
        System.out.println("GOAL: BAWIIN ANG MGA NINAKAW NA BUWAN NI MAYARI!");
        Utils.delay(1000);
        System.out.println();

        System.out.print("Press Enter upang hanapin ang mga buwan...");
        Utils.delay(1500);
        scanner.nextLine();
    }

    public void displayFight1() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("        UNANG BUWAN: PUGAD NG ASWANG");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("Ang unang buwan ay nakikita sa pugad ng Aswang!");
        Utils.delay(900);
        System.out.println("Aswang: 'ANG BUWAN NA ITO AY SA AKIN! WALANG MAKAHAHABLA NITO!'");
        Utils.delay(1000);
        System.out.print("Press Enter to retrieve the first moon...");
        Utils.delay(1500);
        scanner.nextLine();
    }

    public void displayWin1(Character player, Character enemy) {
        moonsCollected++;
        System.out.println("\n✨ " + enemy.getName() + " ay tumakas sa liwanag!");
        Utils.delay(900);
        System.out.println("Nabawi mo ang unang buwan ni Mayari! (" + moonsCollected + "/" + TOTAL_MOONS + ")");
        Utils.delay(1000);
        System.out.println("Aswang: 'SUSUNOD KA... MAGIGING DILIM ANG IYONG KAPALARAN!'");
        Utils.delay(1000);
        System.out.print("Press Enter to continue...");
        Utils.delay(1500);
        scanner.nextLine();
        player.resetAll();
    }

    public void displayFight2() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("        PANGALAWANG BUWAN: KALANGITAN");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("Ang pangalawang buwan ay nakasabit sa himpapawid!");
        Utils.delay(900);
        System.out.println("Manananggal: 'HINDI KO IBIBIGAY ANG BUWAN NA ITO!'");
        Utils.delay(1000);
        System.out.print("Press Enter to retrieve the second moon...");
        Utils.delay(1500);
        scanner.nextLine();
    }

    public void displayWin2(Character player, Character enemy) {
        moonsCollected++;
        System.out.println("\n✨ " + enemy.getName() + " ay bumagsak sa lupa!");
        Utils.delay(900);
        System.out.println("Nabawi mo ang pangalawang buwan! (" + moonsCollected + "/" + TOTAL_MOONS + ")");
        Utils.delay(1000);
        System.out.println("Manananggal: 'MAY HIGIT PANG MAKAPANGYARIHAN SA AKIN... MAG-INGAT KA!'");
        Utils.delay(1000);
        System.out.print("Press Enter to continue...");
        Utils.delay(1500);
        scanner.nextLine();
        player.resetAll();
    }

    public void displayFight3() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("        HULING BUWAN: GUBAT NG TIKBALANG");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("Ang huling buwan ay nasa pinakagitna ng gubat!");
        Utils.delay(900);
        System.out.println("Tikbalang: 'ANG HULING BUWAN NA ITO AY SA AKING MGA KAMAY!'");
        Utils.delay(1000);
        System.out.println("Tikbalang: 'KAILANGAN MONG PATUNAYAN ANG IYONG LAKAS!'");
        Utils.delay(1000);
        System.out.print("Press Enter to face the final guardian...");
        Utils.delay(1500);
        scanner.nextLine();
    }

    public void displayWin3() {
        moonsCollected++;
        System.out.println("\n✨ Tikbalang ay lumuhod sa harap mo!");
        Utils.delay(900);
        System.out.println("Nabawi mo ang huling buwan! (" + moonsCollected + "/" + TOTAL_MOONS + ")");
        Utils.delay(1000);
        System.out.println("Tikbalang: 'MARUNONG KANG LUMABAN... ITO NA ANG HULING BUWAN.'");
        Utils.delay(1000);
        System.out.print("Press Enter to complete Act 2...");
        Utils.delay(1500);
        scanner.nextLine();
    }

    public void displayOutro() {
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.CYAN + "║            ACT 2 COMPLETED!                 ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════════╝" + Colors.RESET);
        System.out.println("Nagawa mong mabawi ang lahat ng " + moonsCollected + " ninakaw na buwan!");
        Utils.delay(900);
        System.out.println("Si Mayari ay nagpapasalamat sa iyong katapangan.");
        Utils.delay(1000);
        System.out.println("");
        System.out.println("Mayari: 'SALAMAT SA PAGLILIGTAS SA AKING MGA BUWAN...'");
        Utils.delay(1000);
        System.out.println("Mayari: 'NGUNIT HINDI PA TAPOS ANG LABAN. MAS MALAKING KADILIMAN ANG PAPARATING.'");
        Utils.delay(1000);
        System.out.println("");
        System.out.println("Ang kalangitan ay nagsisimula nang gumuho...");
        Utils.delay(1000);
        System.out.println("Isang malaking anino ang unti-unting lumalapit...");
        Utils.delay(1000);
        System.out.println("");

        System.out.print("Press Enter to wait for Act 3...");
        Utils.delay(1500);
        scanner.nextLine();
    }
}
