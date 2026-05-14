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
    public static void runBattleTest(){
        Scanner sc = new Scanner(System.in);

        TestDummy player = new TestDummy();
        EnemyDummy enemy = new EnemyDummy();

        System.out.println("\n--- BATTLE TEST START---");
        System.out.println("Player: "+player.getName() + " vs Enemy: " + enemy.getName());
        int skillChoice = sc.nextInt();

        switch(skillChoice){
            case 1:
                player.useSkill1(enemy);
                break;
            case 2:
                player.useSkill2(enemy);
                break;
            case 3:
                player.useSkill3(enemy);
                break;
            default:
                System.out.println("Exiting...");
        }

        System.out.println("\n Final Enemy HP: " + enemy.getHp());
        System.out.println("--- Test Complete ---");
    }
}