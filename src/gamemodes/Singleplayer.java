package gamemodes;

import characters.Character;
import util.*;

import java.util.*;

public class Singleplayer {
    private Scanner scanner;
    private BattleSystem battleSystem;

    public Singleplayer(Scanner scanner) {
        this.scanner = scanner;
        this.battleSystem = new BattleSystem(scanner);
    }

    public void start() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(Utils.colorizeSingp("\n                                           ╔════════════════════════════════════════════╗"));
        System.out.println(Utils.colorizeSingp("                                           ║ " + Colors.CYAN + Colors.BOLD + Colors.ITALIC + "             SINGLEPLAYER MODE             " + Colors.RESET + "║"));
        System.out.println(Utils.colorizeSingp("                                           ╚════════════════════════════════════════════╝"));

        System.out.println(Colors.LIGHT_GREY + "                                           Choose your character..." + Colors.RESET);
        boolean[] isHero = new boolean[1];
        Character player = Character.chooseCharacter(scanner, isHero);

        System.out.println(Colors.LIGHT_GREY + "                                           Generating enemy..." + Colors.RESET);
        Character enemy = characters.CharacterFactory.generateRandomEnemy(isHero[0]);

        battleSystem.startSingleplayer(player, enemy);

        player.resetAll();
        enemy.resetAll();

        System.out.println(Utils.colorizeSingp(Colors.LIGHT_GREY + "\n                                           Press Enter to return to menu..." + Colors.RESET));
        scanner.nextLine();
    }
}