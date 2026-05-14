import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Skeleton Version of Kababalaghan ");
        System.out.println("------------------------------");
        System.out.println("Main Menu");
        System.out.println();
        System.out.println("1. Start Game ");
        System.out.println("2. Quit Game");
        int choice = sc.nextInt();

        if(choice == 1){
            runBattleTest();
        }else{
            System.out.println("Exiting...");
        }
    }


    public static void runBattleTest() {
        Scanner sc = new Scanner(System.in);

        TestDummy player = new TestDummy();
        EnemyDummy enemy = new EnemyDummy();

        while (player.getHp() > 0 && enemy.getHp() > 0) {
            System.out.println();
            System.out.println(player.getName() + " HP: " + player.getHp());
            System.out.println(enemy.getName() + " HP: " + enemy.getHp());

            //Player Turn
            System.out.println();
            System.out.println("Choose your move:");
            System.out.println("1. " + player.getBasic());
            System.out.println("2. " + player.getSpecial());
            System.out.println("3. " + player.getUltimate());
            System.out.println();

            int skillChoice = sc.nextInt();
            System.out.println();
            if (skillChoice == 1) player.basicAttack(enemy);
            else if (skillChoice == 2) player.specialSkill(enemy);
            else if (skillChoice == 3) player.ultimateSkill(enemy);

            //Check if enemy is defeated
            if (enemy.getHp() <= 0) {
                System.out.println("\n*** " + enemy.getName() + " has been defeated! ***");
                break;
            }

            //Enemy Turn (basic attack for now)
            System.out.println();
            System.out.println("\n[ " + enemy.getName() + "'s turn! ]");
            enemy.basicAttack(player);
            System.out.println();

            if (player.getHp() <= 0) {
                System.out.println("\n*** You have been defeated! ***");
                break;
            }
        }
        System.out.println("\n--- BATTLE END ---");
    }


}