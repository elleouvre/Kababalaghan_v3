import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import Gamemodes.Singleplayer;
import Gamemodes.Multiplayer;
import characters.CharacterFactory;
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
                startGameMode(sc);
            } else if (choice == 2) {
                CharacterFactory.showAllCharacters();
                System.out.println("\nPress Enter to return to the main menu...");
                sc.nextLine();
            } else if (choice == 3) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        
        sc.close(); // Close the scanner once, right before the program exits.
    }

    private static void startGameMode(Scanner sc) {
        boolean inGameModeMenu = true;

        while(inGameModeMenu) {
            System.out.println("\n===========================================");
            System.out.println("              GAME MODES");
            System.out.println("===========================================");
            System.out.println(" 1. Arcade Campaign");
            System.out.println(" 2. Singleplayer (VS Computer)");
            System.out.println(" 3. Multiplayer (PVP)");
            System.out.println(" 0. Back to Menu");
            System.out.println("===========================================");
            System.out.print("Choose mode: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                sc.nextLine();
                continue;
            }

            switch(choice) {
                case 1:
                    System.out.println("\n===========================================");
                    System.out.println("     ARCADE CAMPAIGN MODE -- Coming Soon");
                    System.out.println("===========================================");
                    System.out.println("\n Enter to go back...");
                    sc.nextLine();
                    break;
                case 2:
                    new Singleplayer(sc).start();
                    break;
                case 3:
                    new Multiplayer(sc).start();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    inGameModeMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    public static void showDisplayMenu(){
        System.out.println("\n====================================");
        System.out.println("      KABABALAGHAN: The Game");
        System.out.println("====================================");
        System.out.println(" 1. Start Game");
        System.out.println(" 2. View Characters");
        System.out.println(" 3. Quit Game");
        System.out.println("====================================");
    }
}