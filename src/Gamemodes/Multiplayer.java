package Gamemodes;

import characters.Character;
import characters.CharacterFactory;
import util.Colors;

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
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.CYAN + "║               MULTIPLAYER MODE                    ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        boolean playing = true;
        while(playing){
            displayScore();

            System.out.println("\n[PLAYER 1'S TURN]");
            boolean[] player1IsHero = new boolean[1];
            Character player1 = Character.chooseCharacter(scanner, player1IsHero);

            System.out.println("\n[PLAYER 2'S TURN]");
            boolean[] player2IsHero = new boolean[1];
            Character player2 = Character.chooseCharacter(scanner, player2IsHero);

            //coin flip to decide who goes first
            boolean player1GoesFirst = coinFlip(player1.getName(), player2.getName());

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
    private boolean coinFlip(String player1Name, String player2Name) {
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.CYAN + "║               COIN FLIP                           ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);
        System.out.println("Flipping a coin to decide who goes first...");
        System.out.println("\nPlayer 1, call it!");
        System.out.println(" [1] Heads");
        System.out.println(" [2] Tails");
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
        boolean result = new Random().nextBoolean();
        String winner = result ? player1Name : player2Name;

        System.out.println("\nThe coin is in the air...");
        System.out.println("It landed on... " + winner + "!");

        if(result) {
            System.out.println("Player 1 called " + playerCall + " — CORRECT! " + player1Name + " goes first!");
        } else {
            System.out.println("Player 1 called " + playerCall + " — WRONG! " + player2Name + " goes first!");
        }

        return result;
    }


    private void displayScore() {
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.printf("║     SCORE:  Player 1 [%d]  -  [%d] Player 2                ║", player1Wins, player2Wins);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);
        //System.out.println("\n======================================");
        //System.out.printf("SCORE:  Player 1 [%d]  -  [%d] Player 2%n", player1Wins, player2Wins);
        //System.out.println("======================================");
    }

    private boolean playAgain() {
        System.out.println("\nPlay another match?");
        System.out.println("[1] Yes");
        System.out.println("[2] No");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        return choice == 1;
    }

    private void displayFinalScore() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.CYAN + "║               FINAL RESULTS:                      ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);
        System.out.println("Player 1 Wins: " + player1Wins);
        System.out.println("Player 2 Wins: " + player2Wins);

        if (player1Wins > player2Wins) {
            System.out.println("\nPLAYER 1 IS THE CHAMPION!");
        } else if (player2Wins > player1Wins) {
            System.out.println("\nPLAYER 2 IS THE CHAMPION!");
        } else {
            System.out.println("\nIT'S A TIE!");
        }

        System.out.println(Colors.GREEN + "═══════════════════════════════════════════════════" + Colors.RESET);
    }
}
