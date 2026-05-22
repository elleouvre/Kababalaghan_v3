package gamemodes.campaign;

import characters.Character;
import characters.heroes.*;
import characters.CharacterFactory;
import gamemodes.BattleSystem;
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
    public boolean start(Character player) {
        displayIntro();

        Character randomOpponent = CharacterFactory.generateRandomEnemy(true); // Assuming player is a hero

        displayFight1(randomOpponent);

        boolean battle1Won = battleSystem.startSingleplayer(player, randomOpponent);
        if (!battle1Won) {
            return false; // Player exited
        }
        
        if (!player.isAlive()) {
            return false; // Player died
        }

        displayWin1(player, randomOpponent);

        Character corruptedApolaki = new Apolaki();

        displayFight2();
        boolean battle2Won = battleSystem.startSingleplayer(player, corruptedApolaki);
        if (!battle2Won) {
            return false; // Player exited
        }
        
        if (!player.isAlive()) {
            return false; // Player died
        }
        
        displayWin2();
        displayOutro();
        return true; // Act completed successfully
    }
    public void displayIntro() {
        System.out.println();
        System.out.println(Colors.GOLD + "\n╔═════════════════════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Utils.colorizeActWan("║                  ARCADE CAMPAIGN: ACT 1                         ║"));
        System.out.println(Utils.colorizeActWan("║                  PAGKUKULAM SA KORAPSYON                        ║"));
        System.out.println(Utils.colorizeActWan("║     Ang mga Hari ng Lupa at Langit ay nawalan ng kontrol        ║"));
        System.out.println(Utils.colorizeActWan("║     GOAL: TULONGIN MO ANG MGA HEROES AT ILIGTAS MO SILA!        ║"));
        System.out.println(Colors.GOLD + "╚═════════════════════════════════════════════════════════════════╝" + Colors.RESET);
        System.out.println();
        System.out.print("Press Enter to start...");
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
        player.resetAll(); // Stats reset
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
