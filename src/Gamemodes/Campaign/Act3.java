package Gamemodes.Campaign;

import characters.Character;
import characters.CharacterFactory;
import characters.bosses.Bakunawa;
import Gamemodes.BattleSystem;
import java.util.*;

public class Act3 {
    private Scanner sc;
    private BattleSystem battleSystem;
    
    public Act3(Scanner sc){
        this.sc = sc;
        this.battleSystem = new BattleSystem(sc);
    }

    public void start(){
        displayIntro();

        boolean[] getChara = new boolean[1];
        Character player = Character.chooseCharacter(sc, getChara);
        Character randomOpponent = CharacterFactory.generateRandomEnemy(getChara[0]);

        displayFight1(randomOpponent);
        battleSystem.startSingleplayer(player, randomOpponent);
        if (!player.isAlive()) {
            System.out.println("\n[GAME OVER] Nilamon ng dilim ang huling pag-asa...");
            return;
        }

        displayWin1(player, randomOpponent);

        // FINAL BOSS: MOON DEVOURER Bakunawa
        Character finalBoss = new Bakunawa();

        displayFight2();
        battleSystem.startSingleplayer(player, finalBoss);
        if (!player.isAlive()) {
            System.out.println("\n[GAME OVER] Naglaho ang mga buwan nang tuluyan.");
            return;
        }

        displayWin2();
        displayOutro();
    }

    public void displayIntro() {
        System.out.println();
        System.out.println("         ARCADE CAMPAIGN: ACT 3            ");
        System.out.println("         ANG HULING PAGLILIGTAS           ");
        System.out.println("Ang kalangitan ay tuluyan nang naging itim.");
        System.out.println("GOAL: Bawiin ang buwan at tapusin ang sumpa!");
        System.out.println();
        System.out.print("Press [ENTER] to start and choose your Character..");
        sc.nextLine();
    }

    public void displayFight1(Character stage1Enemy) {
        System.out.println("STAGE 1: ANG PINTUAN NG KAWALAN ");
        System.out.println("");
        System.out.println("Isang anino ang humaharang sa iyong landas.");
        System.out.println("Si " + stage1Enemy.getName() + " ay tila wala na sa sarili!");
        System.out.print("Press [ENTER] to engage!");
        sc.nextLine();
    }

    public void displayWin1(Character player, Character stage1Enemy){
        System.out.println(stage1Enemy.getName() + " ay nagising mula sa masamang panaginip.");
        System.out.println(stage1Enemy.getName() + ": 'Ang Bakunawa... nasa likod siya ng lahat ng ito...'");
        System.out.print("Press [ENTER] to face the devourer of moons...");
        sc.nextLine();
        player.resetAll(); // reset player stats
    }

    public void displayFight2() {
        System.out.println("");
        System.out.println("STAGE 2: Ang Higanteng Kumakain ng Buwan");
        System.out.println("Ang higanteng si Bakunawa ay nakabukas ang bibig sa huling buwan!");
        System.out.println("Bakunawa: 'AKIN ANG LIWANAG!'");
        System.out.print("Press [ENTER] to save the world!");
        System.out.println("");
        sc.nextLine();
    }

    public void displayWin2() {
        System.out.println();
        System.out.println("Suka ng liwanag ang iniluwal ni Bakunawa habang siya ay natatalo.");
        System.out.println("Ang mga pitong buwan ay muling nagliwanag sa langit.");
        System.out.println("Mayari: 'Salamat, mortal... ang balanse ay naibalik na.'");
        System.out.print("Press [ENTER] to see the dawn...");
        sc.nextLine();

        System.out.println("\nAng unang sinag ng araw ay dahan-dahang sumisilip sa kapuluan.");
        System.out.println("Ang pulang hamog ay naglaho, at ang awit ng mga ibon ay muling narinig.");
        System.out.println("Isang bagong umaga ang sumapit sa mundo ng KABALAGHAN.");
    }

    public void displayOutro(){
        System.out.println("");
        System.out.println("===========================================");
        System.out.println("              ACT 3 COMPLETED!             ");
        System.out.println("===========================================");
        System.out.println("Nagtagumpay ka sa pagpuksa ng korapsyon.");
        System.out.println("Ang KABABALAGHAN ay muling nanahimik...");
        System.out.println("===========================================");
    }
}
