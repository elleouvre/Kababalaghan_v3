package gamemodes;

import characters.Character;
import util.Colors;

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
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.CYAN + "║               SINGLEPLAYER MODE                   ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        //Choose your character (true = Hero, false = Villain)
        boolean[] isHero = new boolean[1];
        Character player = Character.chooseCharacter(scanner, isHero);

        //Generates random enemy
        Character enemy = characters.CharacterFactory.generateRandomEnemy(isHero[0]);

        battleSystem.startSingleplayer(player, enemy);

        player.resetAll();
        enemy.resetAll();

        System.out.println("\nPress Enter to return to menu...");
        scanner.nextLine();
    }


}