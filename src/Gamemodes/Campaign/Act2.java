package Gamemodes.Campaign;

import characters.Character;
import characters.heroes.*;
import characters.villains.*;
import characters.CharacterFactory;
import Gamemodes.BattleSystem;
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

    public void start(Character player) {
        displayIntro();

        // Reset moon count
        moonsCollected = 0;

        // STAGE 1: First Moon - Aswang
        displayFight1();
        Character aswang = new Aswang();
        battleSystem.startSingleplayer(player, aswang);

        if (!player.isAlive()) {
            System.out.println("\n[GAME OVER] Ang unang buwan ay nanatiling nakatago sa dilim...");
            return;
        }
        displayWin1(player, aswang);
        player.resetAll();

        // STAGE 2: Second Moon - Manananggal
        displayFight2();
        Character manananggal = new Mananananggal();
        battleSystem.startSingleplayer(player, manananggal);

        if (!player.isAlive()) {
            System.out.println("\n[GAME OVER] Ang pangalawang buwan ay nalunod sa dugo...");
            return;
        }
        displayWin2(player, manananggal);
        player.resetAll();

        // STAGE 3: Final Moon - Tikbalang (Guardian)
        displayFight3();
        Character tikbalang = new Tikbalang();
        battleSystem.startSingleplayer(player, tikbalang);

        if (!player.isAlive()) {
            System.out.println("\n[GAME OVER] Hindi mo nabawi ang huling buwan...");
            return;
        }

        displayWin3();
        displayOutro();
    }

    public void displayIntro() {
        System.out.println("\n         ARCADE CAMPAIGN: ACT 2            ");
        System.out.println("         ANG PAGBABALIK NG BUWAN           ");
        System.out.println("Matapos iligtas si Apolaki, tumulong ka ngayon kay Mayari.");
        System.out.println("GOAL: BAWIIN ANG MGA NINAKAW NA BUWAN NI MAYARI!");
        System.out.println();
        System.out.print("Press Enter upang hanapin ang mga buwan...");
        scanner.nextLine();
    }

    public void displayFight1() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("        UNANG BUWAN: PUGAD NG ASWANG");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("Ang unang buwan ay nakikita sa pugad ng Aswang!");
        System.out.println("Aswang: 'ANG BUWAN NA ITO AY SA AKIN! WALANG MAKAHAHABLA NITO!'");
        System.out.print("Press Enter to retrieve the first moon...");
        scanner.nextLine();
    }

    public void displayWin1(Character player, Character enemy) {
        moonsCollected++;
        System.out.println("\n✨ " + enemy.getName() + " ay tumakas sa liwanag!");
        System.out.println("Nabawi mo ang unang buwan ni Mayari! (" + moonsCollected + "/" + TOTAL_MOONS + ")");
        System.out.println("Aswang: 'SUSUNOD KA... MAGIGING DILIM ANG IYONG KAPALARAN!'");
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
        player.resetAll();
    }

    public void displayFight2() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("        PANGALAWANG BUWAN: KALANGITAN");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("Ang pangalawang buwan ay nakasabit sa himpapawid!");
        System.out.println("Manananggal: 'HINDI KO IBIBIGAY ANG BUWAN NA ITO!'");
        System.out.print("Press Enter to retrieve the second moon...");
        scanner.nextLine();
    }

    public void displayWin2(Character player, Character enemy) {
        moonsCollected++;
        System.out.println("\n✨ " + enemy.getName() + " ay bumagsak sa lupa!");
        System.out.println("Nabawi mo ang pangalawang buwan! (" + moonsCollected + "/" + TOTAL_MOONS + ")");
        System.out.println("Manananggal: 'MAY HIGIT PANG MAKAPANGYARIHAN SA AKIN... MAG-INGAT KA!'");
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
        player.resetAll();
    }

    public void displayFight3() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("        HULING BUWAN: GUBAT NG TIKBALANG");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("Ang huling buwan ay nasa pinakagitna ng gubat!");
        System.out.println("Tikbalang: 'ANG HULING BUWAN NA ITO AY SA AKING MGA KAMAY!'");
        System.out.println("Tikbalang: 'KAILANGAN MONG PATUNAYAN ANG IYONG LAKAS!'");
        System.out.print("Press Enter to face the final guardian...");
        scanner.nextLine();
    }

    public void displayWin3() {
        moonsCollected++;
        System.out.println("\n✨ Tikbalang ay lumuhod sa harap mo!");
        System.out.println("Nabawi mo ang huling buwan! (" + moonsCollected + "/" + TOTAL_MOONS + ")");
        System.out.println("Tikbalang: 'MARUNONG KANG LUMABAN... ITO NA ANG HULING BUWAN.'");
        System.out.print("Press Enter to complete Act 2...");
        scanner.nextLine();
    }

    public void displayOutro() {
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.CYAN + "║            ACT 2 COMPLETED!                 ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════════╝" + Colors.RESET);
        System.out.println("Nagawa mong mabawi ang lahat ng " + moonsCollected + " ninakaw na buwan!");
        System.out.println("Si Mayari ay nagpapasalamat sa iyong katapangan.");
        System.out.println("");
        System.out.println("Mayari: 'SALAMAT SA PAGLILIGTAS SA AKING MGA BUWAN...'");
        System.out.println("Mayari: 'NGUNIT HINDI PA TAPOS ANG LABAN. MAS MALAKING KADILIMAN ANG PAPARATING.'");
        System.out.println("");
        System.out.println("Ang kalangitan ay nagsisimula nang gumuho...");
        System.out.println("Isang malaking anino ang unti-unting lumalapit...");
        System.out.println("");
        System.out.print("Press Enter to wait for Act 3...");
        scanner.nextLine();
    }
}