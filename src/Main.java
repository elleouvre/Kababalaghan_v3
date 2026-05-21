import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import Gamemodes.Singleplayer;
import Gamemodes.Multiplayer;
import characters.CharacterFactory;
import characters.Character;
import util.Colors;
import util.Utils;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String choice = "";

        while(!choice.equalsIgnoreCase("X")) {
            showDisplayMenu();
            System.out.print(Colors.WHITE + "                                                      Choose Option: " + Colors.RESET);
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
            System.out.println(Colors.GREEN + "\n╔════════════════════════════════════════════╗" + Colors.RESET);
            System.out.println(Colors.CYAN + "║               GAME MODES:                  ║" + Colors.RESET);
            System.out.println(Colors.CYAN + "║ [1] Arcade Campaign                        ║" + Colors.RESET);
            System.out.println(Colors.CYAN + "║ [2] Singleplayer (VS Computer)             ║" + Colors.RESET);
            System.out.println(Colors.CYAN + "║ [3] Multiplayer (PVP)                      ║" + Colors.RESET);
            System.out.println(Colors.CYAN + "║ [X] Back to Main Menu                      ║" + Colors.RESET);
            System.out.println(Colors.GREEN + "╚════════════════════════════════════════════╝" + Colors.RESET);
            System.out.print("Choose mode: ");
            choice = sc.nextLine().trim();

            switch(choice.toUpperCase()) {
                case "1":
                    Gamemodes.Campaign.CampaignManager campaign = new Gamemodes.Campaign.CampaignManager(sc);
                    campaign.startFullCampaign();
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

    private static void CampaignManager(Scanner sc) {
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║            KABABALAGHAN: FULL CAMPAIGN            ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║               The Complete Story                  ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);
        System.out.print("\nPress Enter to begin your journey...");
        sc.nextLine();

        // ========== ACT 1 ==========
        System.out.println(Colors.PURPLE + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║                    ACT 1                          ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║           Pagkukulam sa Korapsyon                 ║" + Colors.RESET);
        System.out.println(Colors.PURPLE + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        Gamemodes.Campaign.Act1 act1 = new Gamemodes.Campaign.Act1(sc);
        characters.Character player = act1.start();  // ← Use full name or add import

        // Check if player survived Act 1
        if (player == null) {
            System.out.println(Colors.RED + "\n[GAME OVER] Hindi mo nakumpleto ang Act 1..." + Colors.RESET);
            System.out.println("Ang iyong kwento ay nagtapos dito.");
            System.out.print("\nPress Enter to return to menu...");
            sc.nextLine();
            return;
        }

        // Ask if player wants to continue
        System.out.print(Colors.GREEN + "\n✓ NAKUMULETO MO ANG ACT 1!" + Colors.RESET);
        System.out.print("\n" + Colors.YELLOW + "Gusto mo bang magpatuloy sa Act 2? (1=Oo / 2=Hindi): " + Colors.RESET);
        String continueChoice = sc.nextLine().trim();

        if (!continueChoice.equals("1")) {
            System.out.println(Colors.CYAN + "\n[SAVED] Ang iyong paglalakbay ay pansamantalang tumigil." + Colors.RESET);
            System.out.println("Balik ka muli upang ipagpatuloy ang laban!");
            System.out.print("\nPress Enter to return to menu...");
            sc.nextLine();
            return;
        }

        // ========== ACT 2 ==========
        System.out.println(Colors.PURPLE + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║                    ACT 2                          ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║           Ang Pagbabalik ng Buwan                 ║" + Colors.RESET);
        System.out.println(Colors.PURPLE + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        Gamemodes.Campaign.Act2 act2 = new Gamemodes.Campaign.Act2(sc);
        act2.start(player);

        // ========== ACT 3 ==========
        System.out.println(Colors.PURPLE + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║                    ACT 3                          ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║           Ang Pagbabalik ng Buwan                 ║" + Colors.RESET);
        System.out.println(Colors.PURPLE + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        Gamemodes.Campaign.Act3 act3 = new Gamemodes.Campaign.Act3(sc);
        act3.start(player);

        // ========== COMPLETION ==========
        System.out.println(Colors.GREEN + "\n╔═══════════════════════════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║         CONGRATULATIONS!                          ║" + Colors.RESET);
        System.out.println(Colors.CYAN + "║     Nakumpleto mo ang Acts 1 - 3!                 ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "║                                                   ║" + Colors.RESET);
        System.out.println(Colors.PURPLE + "║                                                   ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚═══════════════════════════════════════════════════╝" + Colors.RESET);

        System.out.print("\nPress Enter to return to menu...");
        sc.nextLine();
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