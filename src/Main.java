import java.util.Scanner;
import java.util.ArrayList;
import characters.Character;
import characters.CharacterFactory;
import Gamemodes.BattleSystem;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while(choice != 3) {
            showDisplayMenu();
            System.out.print("Choose Option: ");
            
            try {
                choice = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the invalid input
                continue; // Skip the rest of the loop
            } finally {
                sc.nextLine(); // Always consume the newline character
            }

            if (choice == 1) {
                //Transition to BattleSystem
                BattleSystem battleSystem = new BattleSystem(sc);

                ArrayList<Character> heroes = CharacterFactory.getAllHeroes();
                System.out.println("\nChoose your hero:");
                for (int i = 0; i < heroes.size(); i++) {
                    System.out.println((i + 1) + ". " + heroes.get(i).getName());
                }
                
                int heroChoice = 0;
                try {
                    heroChoice = sc.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // Clear the invalid input
                    continue; // Skip to next menu iteration
                } finally {
                    sc.nextLine(); // Always consume the newline
                }


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
                System.out.println("\nPress Enter to return to the main menu...");
                sc.nextLine();
            } else if (choice == 3){
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        
        sc.close(); // Close the scanner once, right before the program exits.
    }
    
    public static void showDisplayMenu(){
        System.out.println("\n====================================");
        System.out.println("      KABABALAGHAN: The Game");
        System.out.println("====================================");
        System.out.println("1. Start Game (Single Player)");
        System.out.println("2. View Characters");
        System.out.println("3. Quit Game");
        System.out.println("====================================");
    }
}
