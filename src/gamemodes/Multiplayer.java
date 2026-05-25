package gamemodes;

import characters.Character;
import util.*;


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
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(Utils.colorizeMulti("\n                                           ╔════════════════════════════════════════════╗"));
        System.out.println(Utils.colorizeMulti("                                           ║ "+Colors.BOLD+Colors.ITALIC+"             MULTIPLAYER MODE              ║"));
        System.out.println(Utils.colorizeMulti("                                           ╚════════════════════════════════════════════╝"));

        boolean playing = true;
        while(playing){
            displayScore();

            System.out.println(Colors.LIGHT_GREY + "\n                                           [PLAYER 1'S TURN]" + Colors.RESET);
            boolean[] player1IsHero = new boolean[1];
            Character player1 = Character.chooseCharacter(scanner, player1IsHero);

            System.out.println(Colors.LIGHT_GREY + "\n                                           [PLAYER 2'S TURN]" + Colors.RESET);
            boolean[] player2IsHero = new boolean[1];
            Character player2 = Character.chooseCharacter(scanner, player2IsHero);

            boolean player1GoesFirst = coinFlip(player1.getName(), player2.getName());

            Character winner = battleSystem.startMultiplayer(player1, player2, player1GoesFirst);

            if(winner == player1){
                player1Wins++;
                System.out.println(Colors.BOLD + Colors.LIGHT_GREY + "\n                                           [PLAYER 1 WINS THE MATCH!]" + Colors.RESET);
            } else {
                player2Wins++;
                System.out.println(Colors.BOLD + Colors.LIGHT_GREY + "\n                                           [PLAYER 2 WINS THE MATCH!]" + Colors.RESET);
            }

            player1.resetAll();
            player2.resetAll();

            playing = playAgain();
        }

        displayFinalScore();
    }

    private boolean coinFlip(String player1Name, String player2Name) {

        System.out.println(Colors.GREEN + "\n                                           ╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.CYAN + "                                           ║               COIN FLIP                           ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "                                           ╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        System.out.println(Colors.LIGHT_GREY + "                                           Flipping a coin to decide who goes first..." + Colors.RESET);
        System.out.println(Colors.LIGHT_GREY + "\n                                           Player 1, call it!" + Colors.RESET);

        System.out.println(Colors.MOON_SILVER + "                                            [1] Heads" + Colors.RESET);
        System.out.println(Colors.MOON_SILVER + "                                            [2] Tails" + Colors.RESET);

        System.out.print(Colors.LIGHT_GREY + "                                           Choice: " + Colors.RESET);

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

        boolean result = new Random().nextBoolean();
        String winner = result ? player1Name : player2Name;

        System.out.println(Colors.LIGHT_GREY + "\n                                           The coin is in the air..." + Colors.RESET);
        System.out.println(Colors.LIGHT_GREY + "                                           It landed on... " + Colors.RESET + winner + "!");

        if(result) {
            System.out.println(Colors.LIGHT_GREY + "                                           Player 1 called " + playerCall + " — CORRECT! " + Colors.RESET + player1Name + Colors.LIGHT_GREY + " goes first!" + Colors.RESET);
        } else {
            System.out.println(Colors.LIGHT_GREY + "                                           Player 1 called " + playerCall + " — WRONG! " + Colors.RESET + player2Name + Colors.LIGHT_GREY + " goes first!" + Colors.RESET);
        }

        return result;
    }

    private void displayScore() {
        System.out.println(Utils.colorizeMulti("\n                                           ╔════════════════════════════════════════════╗"));
        System.out.printf(
                Colors.CYAN + "                                           ║" +
                        Colors.BOLD + Colors.ITALIC + Colors.LIGHT_GREY +
                        "  SCORE:  Player 1 [%d]  -  [%d] Player 2     " +
                        Colors.RESET + Colors.CYAN +
                        "║\n",
                player1Wins, player2Wins
        );
        System.out.println(Utils.colorizeMulti("                                           ╚════════════════════════════════════════════╝"));
    }

    private boolean playAgain() {

        System.out.println(Colors.LIGHT_GREY + "\n                                           Play another match?" + Colors.RESET);

        System.out.println(Colors.MOON_SILVER + "                                           [1] Yes" + Colors.RESET);
        System.out.println(Colors.MOON_SILVER + "                                           [2] No" + Colors.RESET);

        System.out.print(Colors.LIGHT_GREY + "                                           Choice: " + Colors.RESET);

        int choice = scanner.nextInt();
        scanner.nextLine();

        return choice == 1;
    }

    private void displayFinalScore() {

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println(Colors.GREEN + "\n                                           ╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.CYAN + "                                           ║               FINAL RESULTS:                      ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "                                           ╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        System.out.println(Colors.LIGHT_GREY + "                                           Player 1 Wins: " + Colors.RESET + player1Wins);
        System.out.println(Colors.LIGHT_GREY + "                                           Player 2 Wins: " + Colors.RESET + player2Wins);

        if (player1Wins > player2Wins) {
            System.out.println(Colors.BOLD + Colors.LIGHT_GREY + "\n                                           PLAYER 1 IS THE CHAMPION!" + Colors.RESET);
        } else if (player2Wins > player1Wins) {
            System.out.println(Colors.BOLD + Colors.LIGHT_GREY + "\n                                           PLAYER 2 IS THE CHAMPION!" + Colors.RESET);
        } else {
            System.out.println(Colors.BOLD + Colors.MOON_SILVER + "\n                                           IT'S A TIE!" + Colors.RESET);
        }

        System.out.println(Colors.GREEN + "                                           ═══════════════════════════════════════════════════" + Colors.RESET);
    }
}