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
            return false;
        }

        if (!player.isAlive()) {
            return false;
        }

        displayWin1(player, randomOpponent);

        Character corruptedApolaki = new Apolaki();

        displayFight2();
        boolean battle2Won = battleSystem.startSingleplayer(player, corruptedApolaki);
        if (!battle2Won) {
            return false;
        }

        if (!player.isAlive()) {
            return false;
        }

        displayWin2();
        displayOutro();
        return true;
    }

    public void displayIntro() {
        System.out.println();
        System.out.println(Colors.GOLD + Colors.BOLD + Colors.ITALIC + "\n                                           ╔═════════════════════════════════════════════════════════════════╗");
        System.out.println(Utils.colorizeActWan("                                           ║                  ARCADE CAMPAIGN: ACT 1                         ║"));
        System.out.println(Utils.colorizeActWan("                                           ║                  PAGKUKULAM SA KORAPSYON                        ║"));
        System.out.println(Colors.RED + "                                           ║     Ang mga Hari ng Lupa at Langit ay nawalan ng kontrol        ║");
        System.out.println(Colors.YELLOW + "                                           ║     GOAL: TULONGIN MO ANG MGA HEROES AT ILIGTAS MO SILA!        ║");
        System.out.println(Colors.GOLD + "                                           ╚═════════════════════════════════════════════════════════════════╝" + Colors.RESET);
        System.out.println();
        System.out.print(Colors.MOON_GREY + "                                           Press Enter to start..." + Colors.RESET);
        scanner.nextLine();
    }

    public void displayFight1(Character stage1Enemy) {
        System.out.println(Colors.CYAN + Colors.BOLD + Colors.ITALIC + "                                           STAGE 1: ANG PAG SISIMULA NG DILIM" + Colors.RESET);
        System.out.println(Colors.LIGHT_GREY + "                                           ");
        System.out.println(Colors.LIGHT_GREY + "                                           'Ang liwanag... unti-unting nawawala'");
        Utils.delay(900);
        System.out.println(Colors.RED + "                                           Isang nagwawalang tagapangalaga ang humaharang sa iyo!");
        Utils.delay(1000);
        System.out.println(Colors.RED + "                                           Si " + Colors.WHITE + Colors.BOLD + Colors.ITALIC + stage1Enemy.getName() + Colors.RESET + Colors.RED + " ay nilamon ng pulang hamog!");
        Utils.delay(1500);
        System.out.println(Colors.LIGHT_GREY + "                                           Press Enter to defend and fight!");
        Utils.delay(1500);
        scanner.nextLine();
    }

    public void displayWin1(Character player, Character stage1Enemy) {
        System.out.println(Colors.GREEN + "                                           " + stage1Enemy.getName() + " collapses... Naalis ang korapsyon sa kanyang katawan!" + Colors.RESET);

        Utils.delay(900);

        System.out.println(Colors.MOON_WHITE + "                                           " + stage1Enemy.getName() + ": 'Salamat... nabasag ang sumpa sa aking isipan.'" + Colors.RESET);
        Utils.delay(1000);
        System.out.println(Colors.YELLOW + "                                           " + stage1Enemy.getName() + ": 'Ngunit magmadali ka! Ang Bathalang Mandirigma ay nasa dulo, tuluyan nang nawalan ng bait!'" + Colors.RESET);
        Utils.delay(1000);

        System.out.print(Colors.LIGHT_GREY + "                                           Press Enter to continue and face the final threat..." + Colors.RESET);
        Utils.delay(1500);
        scanner.nextLine();
        player.resetAll();
    }

    public void displayFight2() {
        System.out.println();
        System.out.println(Colors.CYAN + Colors.BOLD + Colors.ITALIC + "                                           STAGE 2: Nilamon ng Natuyong Sumpa ang Araw" + Colors.RESET);
        System.out.println(Colors.RED + "                                           Nagliliyab ang paligid sa galit at init ni Apolaki!" + Colors.RESET);
        Utils.delay(900);
        System.out.println(Colors.RED + "                                           Apolaki: 'HINDI NIYO MAPIPIGILAN ANG KADILIMAN! UMALIS KAYO DITO!'" + Colors.RESET);
        Utils.delay(1000);

        System.out.print(Colors.LIGHT_GREY + "                                           Press Enter to engage to save Apolaki!" + Colors.RESET);
        Utils.delay(1500);
        System.out.println("");
        scanner.nextLine();
    }

    public void displayWin2() {
        System.out.println();
        System.out.println(Colors.GREEN + "                                           Apolaki drops to his knees. The crimson eyes fade back to regular light." + Colors.RESET);
        Utils.delay(900);
        System.out.println(Colors.MOON_WHITE + "                                           Apolaki: 'Aking isipan... bumalik na sa normal. Teka... ang kapatid ko!'" + Colors.RESET);
        Utils.delay(1500);
        System.out.println(Colors.YELLOW + "                                           Apolaki: 'Ang korapsyon sa amin ay isang patibong lamang...'" + Colors.RESET);
        Utils.delay(1500);
        System.out.println(Colors.RED + "                                           Apolaki: 'Si Mayari! Ang aking kapatid ay inaatake ngayon! Kailangan niya ng tulong!'" + Colors.RESET);Utils.delay(1500);
        System.out.print(Colors.LIGHT_GREY + "                                           Press Enter to look up at the corrupted heavenly sky..." + Colors.RESET);
        Utils.delay(1500);
        scanner.nextLine();}

    public void displayOutro(){
        System.out.println("");
        System.out.println(Colors.RED + Colors.BOLD + "                                                     !!! WARNING !!!                  " + Colors.RESET);
        System.out.println(Colors.LIGHT_GREY + "                                           Tumingala ka sa kalangitan kasama si Apolaki.");
        Utils.delay(1000);
        System.out.println(Colors.RED + "                                           Ang gabi ay unti-unting binabalot ng kawalan.");
        Utils.delay(1200);
        System.out.println(Colors.RED + "                                           Habang nakikipaglaban kayo, ang mga buwan ay unti-unting ninanakaw!");
        Utils.delay(1200);
        System.out.println(Colors.YELLOW + "                                           Ang mga apat(4) nga mga buwan ni Mayari ay nawawala!");
        Utils.delay(1500);
        System.out.println("");
        System.out.println(Colors.CYAN + "\n                                           ╔════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.CYAN + Colors.BOLD + Colors.ITALIC + "                                           ║            ACT 1 COMPLETED!                ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "                                           ╚════════════════════════════════════════════╝" + Colors.RESET);
    }
}