package characters;

import characters.heroes.*;
import characters.villains.*;
import util.Colors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CharacterFactory {

    // heroes
    public static ArrayList<Character> getAllHeroes() {
        ArrayList<Character> heroes = new ArrayList<>();
        heroes.add(new Apolaki());
        heroes.add(new Kaptan());
        heroes.add(new Magwayen());
        heroes.add(new MariaMakiling());
        heroes.add(new Mayari());
        //to add more
        //Collections.shuffle(heroes);
        return heroes;
    }

    // villains
    public static ArrayList<Character> getAllVillains() {
        ArrayList<Character> villains = new ArrayList<>();
        villains.add(new Aswang());
        villains.add(new Kapre());
        villains.add(new Mananananggal());
        villains.add(new Santelmo());
        villains.add(new Tikbalang());
        //to add more

        //Collections.shuffle(villains);
        return villains;
    }

    public static void showAllCharacters() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(Colors.MOON_GREY + "                                           ╔════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.MOON_GREY+ "                                           ║"+Colors.MOON_WHITE+Colors.ITALIC+Colors.BOLD+"                 CHARACTERS:                "+Colors.MOON_GREY+"║" + Colors.RESET);
        System.out.println(Colors.MOON_GREY + "                                           ╚════════════════════════════════════════════╝" + Colors.RESET);

            System.out.println(Colors.BLUE + "                                                           \n                                           ╠  HEROES  ╣" + Colors.RESET);
            System.out.println(Colors.BLUE + "                                           ════════════════════════════════════════════════════════════" + Colors.RESET);
            ArrayList<Character> heroes = getAllHeroes();
            for (int i = 0; i < heroes.size(); i++) {
                Character hero = heroes.get(i);
                System.out.printf(Colors.ITALIC+Colors.MOON_WHITE+"                                           %d. %-15s HP: %3d | ATK: %3d | STA: %3d%n",
                        (i+1), hero.getName(), hero.getMaxHp(), hero.getAttack(), hero.getStaminaMax());
                System.out.printf(Colors.MOON_GREY+"                                              Skills: %s, %s, %s%n",
                        hero.getBasic(),
                        hero.getSpecial(),
                        hero.getUltimate());
                System.out.printf(Colors.BLUE+"                                              Stamina Regen: %d-%d per round%n",
                        hero.getStaminaRegenMin(),
                        hero.getStaminaRegenMax());
            }

        System.out.println(Colors.RED + "                                                           \n                                           ╠  VILLAINS  ╣" + Colors.RESET);
        System.out.println(Colors.RED + "                                           ════════════════════════════════════════════════════════════" + Colors.RESET);
            ArrayList<Character> villains = getAllVillains();
            for (int i = 0; i < villains.size(); i++) {
                Character villain = villains.get(i);
                System.out.printf(Colors.ITALIC+Colors.MOON_WHITE+"                                           %d. %-15s HP: %3d | ATK: %3d | STA: %3d%n",
                        (i+1), villain.getName(), villain.getMaxHp(), villain.getAttack(), villain.getStaminaMax());
                System.out.printf(Colors.MOON_GREY+"                                              Skills: %s, %s, %s%n",
                        villain.getBasic(),
                        villain.getSpecial(),
                        villain.getUltimate());
                System.out.printf(Colors.BLUE+"                                              Stamina Regen: %d-%d per round%n",
                        villain.getStaminaRegenMin(),
                        villain.getStaminaRegenMax());
            }
        System.out.println(Colors.PURPLE + "                                           ════════════════════════════════════════════════════════════" + Colors.RESET);
    }

    public static Character generateRandomEnemy(boolean isHero) {
        ArrayList<Character> enemies;

        if(isHero) {
            enemies = CharacterFactory.getAllVillains();
            System.out.println("\n                                           [As a Hero, you will fight a Corrupted Villain!]");
        } else {
            enemies = CharacterFactory.getAllHeroes();
            System.out.println("\n                                           [As a Villain, you will fight a Corrupted Hero!]");
        }

        Random rand = new Random();
        Character enemy = enemies.get(rand.nextInt(enemies.size()));
        System.out.println("                                           [Your enemy is: " + enemy.getName() + "]");

        return enemy;
    }
}