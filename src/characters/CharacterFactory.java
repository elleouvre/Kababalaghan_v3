package characters;

import characters.heroes.Magwayen;
import characters.heroes.Kaptan;
import characters.villains.Kapre;
import characters.villains.Santelmo;

import java.util.ArrayList;
import java.util.Collections;

public class CharacterFactory {

    // heroes
    public static ArrayList<Character> getAllHeroes() {
        ArrayList<Character> heroes = new ArrayList<>();
        heroes.add(new Magwayen());
        heroes.add(new Kaptan());
        //to add more

        Collections.shuffle(heroes);
        return heroes;
    }

    // villains
    public static ArrayList<Character> getAllVillains() {
        ArrayList<Character> villains = new ArrayList<>();
        villains.add(new Kapre());
        villains.add(new Santelmo());
        //to add more

        Collections.shuffle(villains);
        return villains;
    }
}