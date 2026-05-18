package characters;

import characters.heroes.*;
import characters.villains.*;

import java.util.ArrayList;
import java.util.Collections;

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
        Collections.shuffle(heroes);
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

        Collections.shuffle(villains);
        return villains;
    }

    public static void showAllCharacters() {
        StaminaSystem stamina = new StaminaSystem();
        System.out.println("\n===========================================");
        System.out.println("              CHARACTERS");
        System.out.println("===========================================");

        System.out.println("\n[ HEROES ]");
        System.out.println("-------------------------------------------");
        ArrayList<Character> heroes = getAllHeroes();
        for (int i = 0; i < heroes.size(); i++) {
            Character hero = heroes.get(i);
            System.out.printf("%d. %-15s HP: %3d | ATK: %3d | STA: %3d%n",
                    (i+1), hero.getName(), hero.getMaxHp(), hero.getAttack(), stamina.getMax());
            System.out.printf("   Skills: %s, %s, %s%n",
                    hero.getBasic(),
                    hero.getSpecial(),
                    hero.getUltimate());
            System.out.printf("   Stamina Regen: %d-%d per round%n",
                    stamina.getRegenMin(),
                    stamina.getRegenMax());
        }

        System.out.println("\n[ VILLAINS ]");
        System.out.println("-------------------------------------------");
        ArrayList<Character> villains = getAllVillains();
        for (int i = 0; i < villains.size(); i++) {
            Character villain = villains.get(i);
            System.out.printf("%d. %-15s HP: %3d | ATK: %3d | STA: %3d%n",
                    (i+1), villain.getName(), villain.getMaxHp(), villain.getAttack(), stamina.getMax());
            System.out.printf("   Skills: %s, %s, %s%n",
                    villain.getBasic(),
                    villain.getSpecial(),
                    villain.getUltimate());
            System.out.printf("   Stamina Regen: %d-%d per round%n",
                    stamina.getRegenMin(),
                    stamina.getRegenMax());
        }
        System.out.println("===========================================");
    }
}