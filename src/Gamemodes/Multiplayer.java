package Gamemodes;

import characters.Character;
import characters.CharacterFactory;

import java.util.*;

public class Multiplayer {
    private Scanner scanner;
    private BattleSystem battleSystem;
    private int player1Wins = 0;
    private int player2Wins = 0;

    public Multiplayer(Scanner scanner){
        this.scanner = scanner;
        this.battleSystem = new BattleSystem(scanner);
    }

    public void start(){
        //display multiplayer mode display
        System.out.println();
        System.out.println();
        System.out.println("===========================================");
        System.out.println("            MULTIPLAYER MODE");
        System.out.println("===========================================");

        boolean playing = true;
        while(playing){
            displayScore();

            System.out.println("\n[PLAYER 1'S TURN]");
            Character player1 = chooseCharacter("Player 1");

            System.out.println("\n[PLAYER 2'S TURN]");
            Character player2 = chooseCharacter("Player 2");

            //display battle intro
            System.out.println("\n===========================================");
            System.out.println("              FINAL BATTLE");
            System.out.println("===========================================");
            System.out.println(player1.getName() + " VS " + player2.getName());
            System.out.println("Press Enter to start the battle!");
            scanner.nextLine();

            Character winner = battleSystem.startMultiplayer(player1, player2);
            if(winner == player1){
                player1Wins++;
                System.out.println("\n[PLAYER 1 WINS THE MATCH!]");
            } else {
                player2Wins++;
                System.out.println("\n[PLAYER 2 WINS THE MATCH!]");
            }

            player1.resetAll();
            player2.resetAll();

            playing = playAgain();
        }
        displayFinalScore();
    }


    private Character chooseCharacter(String playerName) {
        System.out.println("\n" + playerName + ", choose your side:");
        System.out.println("1. Hero");
        System.out.println("2. Villain");
        System.out.print("Choice: ");

        int side = scanner.nextInt();
        scanner.nextLine();

        if (side == 1) {
            ArrayList<Character> heroes = CharacterFactory.getAllHeroes();
            return selectCharacter(heroes, playerName);
        } else if (side == 2) {
            ArrayList<Character> villains = CharacterFactory.getAllVillains();
            return selectCharacter(villains, playerName);
        } else {
            System.out.println("Invalid choice! Defaulting to Hero.");
            return CharacterFactory.getAllHeroes().getFirst();
        }
    }

    private Character selectCharacter(ArrayList<Character> characters, String playerName) {
        System.out.println("\n" + playerName + ", choose your character:");
        System.out.println("--------------------------------------");

        for (int i = 0; i < characters.size(); i++) {
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

        if (choice < 1 || choice > characters.size()) {
            System.out.println("Invalid choice! Defaulting to first character.");
            return characters.get(0);
        }

        Character selected = characters.get(choice - 1);
        System.out.println("\n[" + playerName + " chose: " + selected.getName() + "]");
        return selected;
    }

    //Lou
    private void displayScore() {
    }

    //Lou
    private boolean playAgain() {
    }

    //Lou
    private void displayFinalScore() {
    }
}
