import java.util.Scanner;
import java.util.ArrayList;
import characters.Character;
import characters.CharacterFactory;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Skeleton Version of Kababalaghan ");
        System.out.println("------------------------------");
        System.out.println("Main Menu");
        System.out.println();
        System.out.println("1. Start Game ");
        System.out.println("2. View Characters");
        System.out.println("3. Quit Game");
        int choice = sc.nextInt();

        if (choice == 1) {
            runBattleTest();
        } else if (choice == 2) {
            CharacterFactory.showAllCharacters();
        } else {
            System.out.println("Exiting...");
        }
    }


    public static void runBattleTest() {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

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
        Character enemy = villains.get(random.nextInt(villains.size()));
        
        System.out.println("\n--- BATTLE START ---");
        System.out.println(player.getName() + " vs " + enemy.getName());


        while (player.getHp() > 0 && enemy.getHp() > 0) {
            System.out.println("\n=================================");
            System.out.println(player.getName() + " HP: " + player.getHp() + "/" + player.getMaxHp() + " | Stamina: " + player.getStamina().getCurrent() + "/" + player.getStamina().getMax());
            System.out.println(enemy.getName() + " HP: " + enemy.getHp() + "/" + enemy.getMaxHp() + " | Stamina: " + enemy.getStamina().getCurrent() + "/" + enemy.getStamina().getMax());
            System.out.println("=================================");

            //Player Turn
            System.out.println("\nChoose your move:");
            System.out.println("1. " + player.getBasic() + " (Cost: " + player.getBasicAttackStaminaCost() + ")");
            System.out.println("2. " + player.getSpecial() + " (Cost: " + player.getSpecialSkillStaminaCost() + ")");
            System.out.println("3. " + player.getUltimate() + " (Cost: " + player.getUltimateSkillStaminaCost() + ")");
            System.out.println();

            int skillChoice = sc.nextInt();
            System.out.println();
            if (skillChoice == 1) player.basicAttack(enemy);
            else if (skillChoice == 2) player.specialSkill(enemy);
            else if (skillChoice == 3) player.ultimateSkill(enemy);
            else {
                System.out.println("Invalid move! " + player.getName() + " missed their turn.");
            }

            //Check if enemy is defeated
            if (enemy.getHp() <= 0) {
                System.out.println("\n*** " + enemy.getName() + " has been defeated! ***");
                break;
            }

            //Enemy Turn (random move logic)
            System.out.println();
            System.out.println("\n[ " + enemy.getName() + "'s turn! ]");
            
            // Simple enemy AI: try ultimate if enough stamina, then special, then basic
            if (enemy.getStamina().getCurrent() >= enemy.getUltimateSkillStaminaCost()) {
                enemy.ultimateSkill(player);
            } else if (enemy.getStamina().getCurrent() >= enemy.getSpecialSkillStaminaCost() && random.nextBoolean()) {
                enemy.specialSkill(player);
            } else {
                enemy.basicAttack(player);
            }
            System.out.println();

            if (player.getHp() <= 0) {
                System.out.println("\n*** You have been defeated by " + enemy.getName() + "! ***");
                break;
            }
            
            // Regenerate stamina at the end of the round
            System.out.println("\n--- End of Round ---");
            System.out.print(player.getName() + " ");
            player.getStamina().regen();
            System.out.print(enemy.getName() + " ");
            enemy.getStamina().regen();
        }
        System.out.println("\n--- BATTLE END ---");
    }

}
