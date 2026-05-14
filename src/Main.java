import java.util.*;

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
            System.out.println(player.getName() + " HP: " + player.getHp());
            System.out.println(enemy.getName() + " HP: " + player.getHp());

            //Player Turn
            System.out.println("Choose your move:");
            System.out.println("1. " + player.skill1);
            System.out.println("2. " + player.skill2);
            System.out.println("3. " + player.skill3);
            System.out.println();

            int skillChoice = sc.nextInt();
            if (skillChoice == 1) player.useSkill1(enemy);
            else if (skillChoice == 2) player.useSkill2(enemy);
            else if (skillChoice == 3) player.useSkill3(enemy);

            //Check if enemy is defeated
            if (enemy.getHp() <= 0) {
                System.out.println("\n*** " + enemy.getName() + " has been defeated! ***");
                break;
            }

            //Enemy Turn (basic attack for now)
            System.out.println();
            System.out.println("\n[ " + enemy.getName() + "'s turn! ]");
            enemy.useSkill1(player);

            if (player.getHp() <= 0) {
                System.out.println("\n*** You have been defeated! ***");
                break;
            }
        }
        System.out.println("\n--- BATTLE END ---");
    }
}