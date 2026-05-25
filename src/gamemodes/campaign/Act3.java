package gamemodes.campaign;

import characters.Character;
import characters.CharacterFactory;
import characters.bosses.Bakunawa;
import gamemodes.BattleSystem;
import util.Utils;
import util.Colors;

import java.util.*;

public class Act3 {
    private Scanner sc;
    private BattleSystem battleSystem;

    public Act3(Scanner sc){
        this.sc = sc;
        this.battleSystem = new BattleSystem(sc);
    }

    public boolean start(Character player){
        displayIntro();

        Character randomOpponent = CharacterFactory.generateRandomEnemy(false);

        displayFight1(randomOpponent);
        boolean battle1Won = battleSystem.startSingleplayer(player, randomOpponent);
        if (!battle1Won) { return false; } // Player exited

        if (!player.isAlive()) {
            System.out.println("\n[GAME OVER] Nilamon ng dilim ang huling pag-asa...");
            return false; // Player died
        }

        displayWin1(player, randomOpponent);

        // FINAL BOSS: MOON DEVOURER Bakunawa
        Character finalBoss = new Bakunawa();

        displayFight2();
        boolean battle2Won = battleSystem.startSingleplayer(player, finalBoss);
        if (!battle2Won) { return false; } // Player exited

        if (!player.isAlive()) {
            System.out.println("\n[GAME OVER] Naglaho ang mga buwan nang tuluyan.");
            return false; // Player died
        }

        displayWin2();
        displayOutro();
        return true; // Act completed successfully
    }

    public void displayIntro() {
        System.out.println();
        System.out.println("         ARCADE CAMPAIGN: ACT 3            ");
        System.out.println("         ANG HULING PAGLILIGTAS           ");
        Utils.delay(1000);

        System.out.println("Ang kalangitan ay tuluyan nang naging itim.");
        Utils.delay(1000);
        System.out.println("GOAL: Bawiin ang buwan at tapusin ang sumpa!");
        Utils.delay(1000);
        System.out.println();

        System.out.print("Press [ENTER] to start...");
        Utils.delay(1500);
        sc.nextLine();
    }

    public void displayFight1(Character stage1Enemy) {
        System.out.println("STAGE 1: ANG PINTUAN NG KAWALAN ");
        System.out.println("");
        System.out.println("Isang anino ang humaharang sa iyong landas.");
        Utils.delay(1000);
        System.out.println("Si " + stage1Enemy.getName() + " ay tila wala na sa sarili!");
        Utils.delay(1000);

        System.out.print("Press [ENTER] to engage!");
        Utils.delay(1500);
        sc.nextLine();
    }

    public void displayWin1(Character player, Character stage1Enemy){
        System.out.println(stage1Enemy.getName() + " ay nagising mula sa masamang panaginip.");
        Utils.delay(900);
        System.out.println(stage1Enemy.getName() + ": 'Ang Bakunawa... nasa likod siya ng lahat ng ito...'");
        Utils.delay(1000);
        System.out.print("Press [ENTER] to face the devourer of moons...");
        Utils.delay(1500);
        sc.nextLine();
        player.resetAll();
    }

    public void displayFight2() {
        System.out.println("");
        System.out.println("STAGE 2: Ang Higanteng Kumakain ng Buwan");
        System.out.println("Ang higanteng si Bakunawa ay nakabukas ang bibig sa huling buwan!");
        Utils.delay(900);
        System.out.println("Bakunawa: 'AKIN ANG LIWANAG!'");
        Utils.delay(1000);
        System.out.print("Press [ENTER] to save the world!");
        Utils.delay(1500);
        System.out.println("");
        sc.nextLine();
    }

    public void displayWin2() {
        System.out.println();
        System.out.println("Suka ng liwanag ang iniluwal ni Bakunawa habang siya ay natatalo.");
        Utils.delay(900);
        System.out.println("Ang mga pitong buwan ay muling nagliwanag sa langit.");
        Utils.delay(1000);
        System.out.println("Mayari: 'Salamat, mortal... ang balanse ay naibalik na.'");
        Utils.delay(1000);
        System.out.print("Press [ENTER] to see the dawn...");
        Utils.delay(1500);
        sc.nextLine();

        System.out.println("\nAng unang sinag ng araw ay dahan-dahang sumisilip sa kapuluan.");
        Utils.delay(900);
        System.out.println("Ang pulang hamog ay naglaho, at ang awit ng mga ibon ay muling narinig.");
        Utils.delay(1000);
        System.out.println("Isang bagong umaga ang sumapit sa mundo ng KABALAGHAN.");
        Utils.delay(1500);

    }

    public void displayOutro(){
        System.out.println("");
        System.out.println(Colors.CYAN + "\n                                           ╔════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.CYAN + Colors.BOLD + Colors.ITALIC + "                                           ║            ACT 3 COMPLETED!                ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "                                           ╚════════════════════════════════════════════╝" + Colors.RESET);
        System.out.println("Nagtagumpay ka sa pagpuksa ng korapsyon.");
        Utils.delay(900);
        System.out.println("Ang KABABALAGHAN ay muling nanahimik...");
        Utils.delay(1000);
        System.out.println("===========================================");
    }
}