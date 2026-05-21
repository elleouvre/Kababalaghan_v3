package Gamemodes.Campaign;

import characters.Character;
import characters.heroes.*;
import characters.villains.*;
import characters.CharacterFactory;
import Gamemodes.BattleSystem;
import java.util.*;


public class Act1 {
    private Scanner scanner;
    private BattleSystem battleSystem;

    public Act1(Scanner scanner) {
        this.scanner = scanner;
        this.battleSystem = new BattleSystem(scanner);
    }
    public void start() {
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
                return;
            }

        displayWin1(player, randomOpponent);

        Character corruptedApolaki = new Apolaki();

        displayFight2();
        //startfight2
        battleSystem.startSingleplayer(player, corruptedApolaki);
            //if lose then ends
            if (!player.isAlive()) {
                System.out.println("\n[GAME OVER] Nilamon ka ng bagsik ni Apolaki.");
                return;
            }
        displayWin2();
        displayOutro();
    }

    public void displayIntro() {
        System.out.println();
        System.out.println("         ARCADE CAMPAIGN: ACT 1            ");
        System.out.println("         PAGKUKULAM SA KORAPSYON           ");
        System.out.println("Ang mga Hari ng Lupa at Langit ay nawalan ng kontrol");
        System.out.println("GOAL: TULONGIN MO ANG MGA HEROES AT ILIGTAS MO SILA!");
        System.out.println();
        System.out.print("Press Enter to start and choose your Character..");
        scanner.nextLine();
    }
    public void displayFight1(Character stage1Enemy) {
     System.out.println("STAGE 1: ANG PAG SISIMULA NG DILIM ");
        System.out.println("");
        System.out.println("'Ang liwanag... unti-unting nawawala'");
        System.out.println("Isang nagwawalang tagapangalaga ang humaharang sa iyo!");
        System.out.println("Si " + stage1Enemy.getName() + " ay nilamon ng pulang hamog!");
        System.out.print("Press Enter to defend and fight!");
        scanner.nextLine();
     }

     public void displayWin1(Character player, Character stage1Enemy){
            System.out.println("✨ " + stage1Enemy.getName() + " collapses... Naalis ang korapsyon sa kanyang katawan!");
            System.out.println(stage1Enemy.getName() + ": 'Salamat... nabasag ang sumpa sa aking isipan.'");
            System.out.println(stage1Enemy.getName() + ": 'Ngunit magmadali ka! Ang Bathalang Mandirigma ay nasa dulo, tuluyan nang nawalan ng bait!'");
            System.out.print("Press Enter to continue and face the final threat...");
            scanner.nextLine();
            //clean stats
            player.resetAll();
     }
     public void displayFight2() {
        System.out.println("");
        System.out.println("STAGE 2: Nilamon ng Natuyong Sumpa ang Araw");
        System.out.println("Nagliliyab ang paligid sa galit at init ni Apolaki!");
        System.out.println("Apolaki: 'HINDI NIYO MAPIPIGILAN ANG KADILIMAN! UMALIS KAYO DITO!'");
        System.out.print("Press Enter to engage to save Apolaki!");
        System.out.println("");
        scanner.nextLine();
     }
    public void displayWin2() {
        System.out.println();
        System.out.println("Apolaki drops to his knees. The crimson eyes fade back to regular light.");
        System.out.println("Apolaki: 'Aking isipan... bumalik na sa normal. Teka... ang kapatid ko!'");
        System.out.println("Apolaki: 'Ang korapsyon sa amin ay isang patibong lamang...'");
        System.out.println("Apolaki: 'Si Mayari! Ang aking kapatid ay inaatake ngayon! Kailangan niya ng tulong!'");
        System.out.print("Press Enter to look up at the corrupted heavenly sky...");
        scanner.nextLine();
    }
    public void displayOutro(){
        System.out.println("");
        System.out.println("          !!! WARNING !!!                  ");
        System.out.println("Tumingala ka sa kalangitan kasama si Apolaki.");
        System.out.println("Ang gabi ay unti-unting binabalot ng kawalan.");
        System.out.println("Habang nakikipaglaban kayo, ang mga buwan ay unti-unting ninanakaw!");
        System.out.println("Ang mga pitong(7) nga mga buwan ni Mayari ay nawawala!");
        System.out.println("");
        System.out.println("===========================================");
        System.out.println("            ACT 1 COMPLETED!               ");
        System.out.println("===========================================");
    }
}
