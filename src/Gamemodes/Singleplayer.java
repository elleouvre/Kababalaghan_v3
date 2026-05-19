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
        Character player = chooseCharacter(isHero);

        //Generates random enemy
        Character enemy = generateRandomEnemy(isHero[0]);

        battleSystem.startSingleplayer(player, enemy);

        player.resetAll();
        enemy.resetAll();

        System.out.println("\nPress Enter to return to menu...");
        scanner.nextLine();
    }

    private Character chooseCharacter(boolean[] isHero) {
        System.out.println("\nChoose your side:");
        System.out.println("1. Hero");
        System.out.println("2. Villain");
        System.out.print("Choice: ");

        int side = scanner.nextInt();
        scanner.nextLine();

        if(side == 1) {
            isHero[0] = true;
            ArrayList<Character> heroes = CharacterFactory.getAllHeroes();
            return selectCharacter(heroes, "Hero");
        } else if(side == 2) {
            isHero[0] = false;
            ArrayList<Character> villains = CharacterFactory.getAllVillains();
            return selectCharacter(villains, "Villain");
        } else {
            System.out.println("Invalid choice! Defaulting to Hero.");
            isHero[0] = true;
            return CharacterFactory.getAllHeroes().get(0);
        }
    }

    private Character selectCharacter(ArrayList<Character> characters, String type) {
        System.out.println("\nChoose your " + type + ":");
        System.out.println("--------------------------------------");

        for(int i = 0; i < characters.size(); i++) {
            Character c = characters.get(i);
            System.out.printf("%d. %-15s HP: %3d | ATK: %3d | STA: %3d%n",
                    (i + 1),
                    c.getName(),
                    c.getMaxHp(),
                    c.getAttack(),
                    c.getStaminaMax());
            System.out.printf("   Skills: %s, %s, %s%n",
                    c.getBasic(),
                    c.getSpecial(),
                    c.getUltimate());
        }

        System.out.print("\nChoice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if(choice < 1 || choice > characters.size()) {
            System.out.println("Invalid choice! Defaulting to first character.");
            return characters.get(0);
        }

        Character selected = characters.get(choice - 1);
        System.out.println("\n[You chose: " + selected.getName() + "]");
        return selected;
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