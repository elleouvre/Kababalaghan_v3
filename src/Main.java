import java.util.InputMismatchException;
import java.util.Scanner;
import Gamemodes.Singleplayer;
import Gamemodes.Multiplayer;
import characters.CharacterFactory;
import util.Colors;
import util.Utils;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String choice = "";

        while(!choice.equalsIgnoreCase("X")) {
            showDisplayMenu();
            System.out.print(Colors.RED + "                                                      Choose Option: " + Colors.RESET);
            choice = sc.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "1":
                    startGameMode(sc);
                    break;
                case "2":
                    CharacterFactory.showAllCharacters();
                    System.out.println("\nPress [ENTER] to return to the main menu...");
                    sc.nextLine();
                    break;
                case "X":
                    System.out.println("Thank you for playing!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        
        sc.close(); // Close the scanner once, right before the program exits.
    }

    private static void startGameMode(Scanner sc) {
        String choice = "";

        while(!choice.equalsIgnoreCase("X")) {
            System.out.println("\n===========================================");
            System.out.println("              GAME MODES");
            System.out.println("===========================================");
            System.out.println(" [1] Arcade Campaign");
            System.out.println(" [2] Singleplayer (VS Computer)");
            System.out.println(" [3] Multiplayer (PVP)");
            System.out.println(" [X] Back to Main Menu");
            System.out.println("===========================================");
            System.out.print("Choose mode: ");
            choice = sc.nextLine().trim();

            switch(choice.toUpperCase()) {
                case "1":
                    new Gamemodes.Campaign.Act1(sc).start();
                    break;
                case "2":
                    new Singleplayer(sc).start();
                    break;
                case "3":
                    new Multiplayer(sc).start();
                    break;
                case "X":
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    public static void showDisplayMenu(){

        System.out.println(Utils.colorizeBlocks("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗"));
        System.out.println(Utils.colorizeBlocks("║                                                                                                                                      ║"));
        System.out.println(Utils.colorizeMoon("║                                                           =@@@@@@@@@@@=                                                              ║"));
        System.out.println(Utils.colorizeMoon("║                                                    .:+@@@@#::::::::::-#@@@@+:.                                                       ║"));
        System.out.println(Utils.colorizeMoon("║                                                 =+*%%*:-::-=-=---::--:::::-*%%*+                                                     ║"));
        System.out.println(Utils.colorizeMoon("║                                                *++===++=====++-::::..:.   ...--+*#                                                   ║"));
        System.out.println(Utils.colorizeMoon("║                                              #%:=**+*++++++--::--::---::..   ..:-:#%                                                 ║"));
        System.out.println(Utils.colorizeMoon("║                                            =@=:*******+=---:--***=-:::--::.      .:-@=                                               ║"));
        System.out.println(Utils.colorizeMoon("║                                          -@+:=+***++*+-++++-=+++*=-::-::.     .... :@@@=                                             ║"));
        System.out.println(Utils.colorizeBlocks("║               ██ ▄█▀▄▄▄       ▄▄▄▄    ▄▄▄       ▄▄▄▄    ▄▄▄       ██▓    ▄▄▄        ▄████  ██░ ██  ▄▄▄       ███▄    █               ║"));
        System.out.println(Utils.colorizeBlocks("║               ██▄█▒▒████▄    ▓█████▄ ▒████▄    ▓█████▄ ▒████▄    ▓██▒   ▒████▄     ██▒ ▀█▒▓██░ ██▒▒████▄     ██ ▀█   █               ║"));
        System.out.println(Utils.colorizeBlocks("║               ▓███▄░▒██  ▀█▄  ▒██▒ ▄██▒██  ▀█▄  ▒██▒ ▄██▒██  ▀█▄  ▒██░   ▒██  ▀█▄  ▒██░▄▄▄░▒██▀▀██░▒██  ▀█▄  ▓██  ▀█ ██▒             ║"));
        System.out.println(Utils.colorizeBlocks("║               ▓██ █▄░██▄▄▄▄██ ▒██░█▀  ░██▄▄▄▄██ ▒██░█▀  ░██▄▄▄▄██ ▒██░   ░██▄▄▄▄██ ░▓█  ██▓░▓█ ░██ ░██▄▄▄▄██ ▓██▒  ▐▌██▒             ║"));
        System.out.println(Utils.colorizeBlocks("║               ▒██▒ █▄▓█   ▓██▒░▓█  ▀█▓ ▓█   ▓██▒░▓█  ▀█▓ ▓█   ▓██▒░██████▒▓█   ▓██▒░▒▓███▀▒░▓█▒░██▓ ▓█   ▓██▒▒██░   ▓██░             ║"));
        System.out.println(Utils.colorizeBlocks("║               ▒ ▒▒ ▓▒▒▒   ▓▒█░░▒▓███▀▒ ▒▒   ▓▒█░░▒▓███▀▒ ▒▒   ▓▒█░░ ▒░▓  ░▒▒   ▓▒█░ ░▒   ▒  ▒ ░░▒░▒ ▒▒   ▓▒█░░ ▒░   ▒ ▒              ║"));
        System.out.println(Utils.colorizeBlocks("║               ░ ░▒ ▒░ ▒   ▒▒ ░▒░▒   ░   ▒   ▒▒ ░▒░▒   ░   ▒   ▒▒ ░░ ░ ▒  ░ ▒   ▒▒ ░  ░   ░  ▒ ░▒░ ░  ▒   ▒▒ ░░ ░░   ░ ▒░             ║"));
        System.out.println(Utils.colorizeBlocks("║               ░ ░░ ░  ░   ▒    ░    ░   ░   ▒    ░    ░   ░   ▒     ░ ░    ░   ▒   ░ ░   ░  ░  ░░ ░  ░   ▒      ░   ░ ░              ║"));
        System.out.println(Utils.colorizeBlocks("║               ░  ░        ░  ░ ░            ░  ░ ░            ░  ░    ░  ░     ░  ░      ░  ░  ░  ░      ░  ░         ░              ║"));
        System.out.println(Utils.colorizeBlocks("║               ░                 ░                                                                                                    ║"));
        System.out.println(Utils.colorizeBlocks("║                       ✠ ——— ✠ ——— ✠ ——— ✠ DESCEND INTO LEGEND.  FIGHT FOR YOUR FATE. ✠ ——— ✠ ——— ✠ ——— ✠                          ║"));
        System.out.println(Utils.colorizeBlocks("║                                                                                                                                      ║"));
        System.out.println(Utils.colorizeBlocks("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n"));

        System.out.println(Colors.PURPLE + "                                                      [1] Start");
        System.out.println(Colors.PURPLE + "                                                      [2] View Characters");
        System.out.println(Colors.LIGHT_BLUE + "                                                      [X] Quit Game" + Colors.RESET);

    }
}