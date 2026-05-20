package Gamemodes;

import characters.Character;
import characters.CharacterFactory;
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
        System.out.println("===========================================");
        System.out.println("            SINGLEPLAYER MODE");
        System.out.println("===========================================");

        //Choose your character (true = Hero, false = Villain)
        boolean[] isHero = new boolean[1];
        Character player = Character.chooseCharacter(scanner, isHero);

        //Generates random enemy
        Character enemy = generateRandomEnemy(isHero[0]);

        battleSystem.startSingleplayer(player, enemy);

        player.resetAll();
        enemy.resetAll();

        System.out.println("\nPress Enter to return to menu...");
        scanner.nextLine();
    }

    private Character generateRandomEnemy(boolean isHero) {
        ArrayList<Character> enemies;

        if(isHero) {
            enemies = CharacterFactory.getAllVillains();
            System.out.println("\n[As a Hero, you will fight a Villain!]");
        } else {
            enemies = CharacterFactory.getAllHeroes();
            System.out.println("\n[As a Villain, you will fight a Hero!]");
        }

        Random rand = new Random();
        Character enemy = enemies.get(rand.nextInt(enemies.size()));
        System.out.println("[Your enemy is: " + enemy.getName() + "]");

        return enemy;
    }
}