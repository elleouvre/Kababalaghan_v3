import java.util.Scanner;
import java.util.ArrayList;
import characters.Character;
import characters.CharacterFactory;
import Gamemodes.BattleSystem;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        showDisplayMenu();
        int choice = sc.nextInt();
        sc.nextLine(); // Clear scanner buffer

        while(choice != 3) {
            if (choice == 1) {
                //Transition to BattleSystem
                BattleSystem battleSystem = new BattleSystem(sc);

                ArrayList<Character> heroes = CharacterFactory.getAllHeroes();
                System.out.println("\nChoose your hero:");
                for (int i = 0; i < heroes.size(); i++) {
                    System.out.println((i + 1) + ". " + heroes.get(i).getName());
                }
                int heroChoice = sc.nextInt();

                // Input validation
                if (heroChoice < 1 || heroChoice > heroes.size()) {
                    System.out.println("Invalid choice. Defaulting to first hero.");
                    heroChoice = 1;
                }

                Character player = heroes.get(heroChoice - 1);

                ArrayList<Character> villains = CharacterFactory.getAllVillains();
                Character enemy = villains.get(new Random().nextInt(villains.size()));

                battleSystem.startSingleplayer(player, enemy);

            } else if (choice == 2) {
                CharacterFactory.showAllCharacters();
                sc.nextLine();
            } else if (choice==3){
                System.out.println("Exiting...");
                sc.close();
            }
            System.out.println();
            showDisplayMenu();
            System.out.print("Choose Option: ");
            choice = sc.nextInt();
            sc.nextLine();
        }
    }
    public static void showDisplayMenu(){
        System.out.println("Skeleton Version of Kababalaghan ");
        System.out.println("------------------------------");
        System.out.println("Main Menu");
        System.out.println();
        System.out.println("1. Start Game ");
        System.out.println("2. View Characters");
        System.out.println("3. Quit Game");
    }
}
