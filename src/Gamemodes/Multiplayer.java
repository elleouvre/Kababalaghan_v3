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

            //coin flip to decide who goes first
            boolean player1GoesFirst = coinFlip();

            Character winner = battleSystem.startMultiplayer(player1, player2, player1GoesFirst);
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

    //determines which player goes first
    private boolean coinFlip() {
        System.out.println("\n===========================================");
        System.out.println("             COIN FLIP!");
        System.out.println("===========================================");
        System.out.println("Flipping a coin to decide who goes first...");
        System.out.println("\nPlayer 1, call it!");
        System.out.println(" 1. Heads");
        System.out.println(" 2. Tails");
        System.out.print("Choice: ");

        int call;
        try {
            call = scanner.nextInt();
            scanner.nextLine();
            if (call < 1 || call > 2) call = 1;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            call = 1;
        }

        String playerCall = (call == 1) ? "Heads" : "Tails";

        //flip the coin
        boolean landedHeads = new Random().nextBoolean();
        String result = landedHeads ? "Heads" : "Tails";

        System.out.println("\nThe coin is in the air...");
        System.out.println("It landed on... " + result + "!");

        boolean player1Wins = (call == 1 && landedHeads) || (call == 2 && !landedHeads);

        if(player1Wins) {
            System.out.println("Player 1 called " + playerCall + " — CORRECT! Player 1 goes first!");
        } else {
            System.out.println("Player 1 called " + playerCall + " — WRONG! Player 2 goes first!");
        }

        return player1Wins;
    }

    private Character chooseCharacter(String playerName) {
        System.out.println("\n" + playerName + ", choose your side:");
        System.out.println("1. Hero");
        System.out.println("2. Villain");
        System.out.print("Choice: ");

        int side = scanner.nextInt();
        scanner.nextLine();

        if(side == 1) {
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

    private void displayScore() {
        System.out.println("\n======================================");
        System.out.printf("SCORE:  Player 1 [%d]  -  [%d] Player 2%n", player1Wins, player2Wins);
        System.out.println("======================================");
    }

    private boolean playAgain() {
        System.out.println("\nPlay another match?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        return choice == 1;
    }

    private void displayFinalScore() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\n======================================");
        System.out.println("           FINAL RESULTS");
        System.out.println("======================================");
        System.out.println("Player 1 Wins: " + player1Wins);
        System.out.println("Player 2 Wins: " + player2Wins);

        if (player1Wins > player2Wins) {
            System.out.println("\nPLAYER 1 IS THE CHAMPION!");
        } else if (player2Wins > player1Wins) {
            System.out.println("\nPLAYER 2 IS THE CHAMPION!");
        } else {
            System.out.println("\nIT'S A TIE!");
        }

        System.out.println("======================================");
    }
}
