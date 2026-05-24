import java.util.Scanner;
import gamemodes.Singleplayer;
import gamemodes.Multiplayer;
import characters.CharacterFactory;
import util.Colors;
import util.Utils;

public class GameMenu {
    private Scanner scanner;

    public GameMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        String choice = "";

        while (!choice.equalsIgnoreCase("X")) {
            showDisplayMenu();
            System.out.print(Colors.WHITE + "                                                         Choose Option: " + Colors.RESET);
            choice = scanner.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "1":
                    startGameMode();
                    break;
                case "2":
                    CharacterFactory.showAllCharacters();
                    System.out.println("\nPress [ENTER] to return to the main menu...");
                    scanner.nextLine();
                    break;
                case "X":
                    System.out.println();
                    System.out.println();
                    System.out.println("                                                The darkness awaits your return...");
                    Utils.delay(900);
                    System.out.println("                                                You escaped the nightmare... this time.");
                    Utils.delay(900);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void startGameMode() {
        String choice = "";

        while (!choice.equalsIgnoreCase("X")) {
            System.out.println();
            System.out.println(Colors.BLACK + "                                           ╔════════════════════════════════════════════╗" + Colors.RESET);
            System.out.println(Colors.CYAN + "                                           ║               GAME MODES:                  ║" + Colors.RESET);
            System.out.println(Colors.CYAN + "                                           ║ [1] Arcade Campaign                        ║" + Colors.RESET);
            System.out.println(Colors.CYAN + "                                           ║ [2] Singleplayer (VS Computer)             ║" + Colors.RESET);
            System.out.println(Colors.CYAN + "                                           ║ [3] Multiplayer (PVP)                      ║" + Colors.RESET);
            System.out.println(Colors.CYAN + "                                           ║ [X] Back to Main Menu                      ║" + Colors.RESET);
            System.out.println(Colors.BLACK + "                                           ╚════════════════════════════════════════════╝" + Colors.RESET);
            System.out.print("                                             Choose mode: ");
            choice = scanner.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "1":
                    new gamemodes.campaign.CampaignManager(scanner).startFullCampaign();
                    break;
                case "2":
                    new Singleplayer(scanner).start();
                    break;
                case "3":
                    new Multiplayer(scanner).start();
                    break;
                case "X":
                    System.out.println("                                             Returning to main menu...");
                    break;
                default:
                    System.out.println("                                             Invalid choice! Please try again.");
            }
        }
    }

    private void showDisplayMenu() {
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

        System.out.println(Colors.PURPLE + "                                                         [1] Start");
        System.out.println(Colors.BLUE + "                                                         [2] View Characters");
        System.out.println(Colors.LIGHT_BLUE + "                                                         [X] Quit Game" + Colors.RESET);
    }
}