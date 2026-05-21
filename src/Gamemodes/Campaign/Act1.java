package Gamemodes.Campaign;

import characters.Character;
import characters.heroes.*;
import characters.villains.*;
import characters.CharacterFactory;
import Gamemodes.BattleSystem;
import util.Utils;
import util.Colors;

import java.util.*;


public class Act1 {
    private Scanner scanner;
    private BattleSystem battleSystem;

    public Act1(Scanner scanner) {
        this.scanner = scanner;
        this.battleSystem = new BattleSystem(scanner);
    }
    public Character start() {
        displayIntro();


        //Get User and Randomized Enemy
        boolean[] getChara = new boolean[1];
        Character player = Character.chooseCharacter(scanner, getChara);
        Character randomOpponent = CharacterFactory.generateRandomEnemy(getChara[0]);

        displayFight1(randomOpponent);

        //startfight1
        battleSystem.startSingleplayer(player, randomOpponent);
            //if lose then ends
            if (!player.isAlive()) {
                System.out.println("\n[GAME OVER] Yung mundo ay naging ka diliman...");
                return null;
            }

        displayWin1(player, randomOpponent);

        Character corruptedApolaki = new Apolaki();

        displayFight2();
        //startfight2
        battleSystem.startSingleplayer(player, corruptedApolaki);
            //if lose then ends
            if (!player.isAlive()) {
                System.out.println("\n[GAME OVER] Nilamon ka ng bagsik ni Apolaki.");
                return null;
            }
        displayWin2();
        displayOutro();
        return player;
    }
    public void displayIntro() {
        System.out.println();
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println("║          ARCADE CAMPAIGN: ACT 1            ║");
        System.out.println("║          PAGKUKULAM SA KORAPSYON           ║");
        System.out.println("║        Ang mga Hari ng Lupa at Langit ay nawalan ng kontrol║");
        System.out.println("║               GOAL: TULONGIN MO ANG MGA HEROES AT ILIGTAS MO SILA!║");
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════════╝" + Colors.RESET);
        System.out.println();
        System.out.print("Press Enter to start and choose your Character..");
        scanner.nextLine();
    }
    public void displayFight1(Character stage1Enemy) {
        System.out.println("STAGE 1: ANG PAG SISIMULA NG DILIM");
        System.out.println("");
        System.out.println("'Ang liwanag... unti-unting nawawala'");
        Utils.delay(900);
        System.out.println("Isang nagwawalang tagapangalaga ang humaharang sa iyo!");
        Utils.delay(1000);

        System.out.println("Si " + stage1Enemy.getName() + " ay nilamon ng pulang hamog!");
        Utils.delay(1500);

        System.out.println("Press Enter to defend and fight!");
        Utils.delay(1500);
        scanner.nextLine();
    }
    public void displayWin1(Character player, Character stage1Enemy) {
        System.out.println("✨ " + stage1Enemy.getName() + " collapses... Naalis ang korapsyon sa kanyang katawan!");
        Utils.delay(900);

        System.out.println(stage1Enemy.getName() + ": 'Salamat... nabasag ang sumpa sa aking isipan.'");
        Utils.delay(1000);
        System.out.println(stage1Enemy.getName() + ": 'Ngunit magmadali ka! Ang Bathalang Mandirigma ay nasa dulo, tuluyan nang nawalan ng bait!'");
        Utils.delay(1000);

        System.out.print("Press Enter to continue and face the final threat...");
        Utils.delay(1500);
        scanner.nextLine();
        //clean stats
        player.resetAll();
    }
    public void displayFight2() {
        System.out.println("");
        System.out.println("STAGE 2: Nilamon ng Natuyong Sumpa ang Araw");
        System.out.println("Nagliliyab ang paligid sa galit at init ni Apolaki!");
        Utils.delay(900);
        System.out.println("Apolaki: 'HINDI NIYO MAPIPIGILAN ANG KADILIMAN! UMALIS KAYO DITO!'");
        Utils.delay(1000);

        System.out.print("Press Enter to engage to save Apolaki!");
        Utils.delay(1500);
        System.out.println("");
        scanner.nextLine();
    }
    public void displayWin2() {
        System.out.println();
        System.out.println("Apolaki drops to his knees. The crimson eyes fade back to regular light.");
        Utils.delay(900);
        System.out.println("Apolaki: 'Aking isipan... bumalik na sa normal. Teka... ang kapatid ko!'");
        Utils.delay(1500);
        System.out.println("Apolaki: 'Ang korapsyon sa amin ay isang patibong lamang...'");
        Utils.delay(1500);
        System.out.println("Apolaki: 'Si Mayari! Ang aking kapatid ay inaatake ngayon! Kailangan niya ng tulong!'");
        Utils.delay(1500);

        System.out.print("Press Enter to look up at the corrupted heavenly sky...");
        Utils.delay(1500);
        scanner.nextLine();
    }
    public void displayOutro(){
        System.out.println("");
        System.out.println("          !!! WARNING !!!                  ");
        System.out.println("Tumingala ka sa kalangitan kasama si Apolaki.");
        Utils.delay(1000);
        System.out.println("Ang gabi ay unti-unting binabalot ng kawalan.");
        Utils.delay(1200);
        System.out.println("Habang nakikipaglaban kayo, ang mga buwan ay unti-unting ninanakaw!");
        Utils.delay(1200);
        System.out.println("Ang mga pitong(7) nga mga buwan ni Mayari ay nawawala!");
        Utils.delay(1500);
        System.out.println("");
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.CYAN + "║            ACT 1 COMPLETED!                 ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════════╝" + Colors.RESET);
    }
}
