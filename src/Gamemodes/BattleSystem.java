package Gamemodes;

import characters.Character;
import characters.StaminaSystem;
import java.util.*;


public class BattleSystem {
    private Scanner scanner;
    private boolean forceExit = false;

    public BattleSystem(Scanner scanner){
        this.scanner = scanner;
    }


    //For Singleplayer (Player vs AI) - Lou
    public void startSingleplayer(Character player, Character ai){
        displayBattleIntro();
        System.out.println("[ PLAYER VS AI BATTLE ]");
        System.out.println(player.getName() + " VS " + ai.getName());
        System.out.println("Press Enter to start!");
        scanner.nextLine();
        
        forceExit = false;

        while(player.isAlive() && ai.isAlive() && !forceExit){
            System.out.println();
            System.out.println("\n======================================");
            System.out.println("YOUR TURN (" + player.getName() + ")");
            System.out.println("======================================");
            playerTurn(player, ai,1);

            if(!ai.isAlive() || forceExit){
                break;
            }

            System.out.println("\nPress [ENTER] for the enemy's turn...\n");
            scanner.nextLine();

            System.out.println("\n======================================");
            System.out.println("ENEMY TURN (" + ai.getName() + ")");
            System.out.println("======================================");
            aiTurn(ai, player);

            if(player.isAlive()){
                System.out.println("\n[Stamina Regeneration]");
                System.out.print(player.getName() + " (You) ");
                player.getStamina().regen();
            }
            if(ai.isAlive()){
                System.out.print(ai.getName() + " ");
                ai.getStamina().regen();
            }

            if (!forceExit && player.isAlive() && ai.isAlive()) {
                System.out.println("\nPress [ENTER] to continue...\n");
                scanner.nextLine();
            }
        }
        if (!forceExit) {
            displayBattleResult();
        } else {
            System.out.println("\n[ BATTLE ABORTED ]");
        }
    }

    //For multiplayer (Player vs Player) - Bea
    public Character startMultiplayer(Character player1, Character player2, boolean player1GoesFirst){
        displayBattleIntro();
        System.out.println("\n[ PLAYER VS PLAYER BATTLE ]");
        System.out.println(player1.getName() + " VS " + player2.getName());
        System.out.println("Press [ENTER] to start!");
        scanner.nextLine();

        boolean player1Turn = player1GoesFirst;
        forceExit = false;

        while(player1.isAlive() && player2.isAlive() && !forceExit){
            if(player1Turn){
                System.out.println("\n======================================");
                System.out.println("PLAYER 1'S TURN (" + player1.getName() + ")");
                System.out.println("======================================");
                playerTurn(player1, player2, 2);
            }else{
                System.out.println("\n======================================");
                System.out.println("PLAYER 2'S TURN (" + player2.getName() + ")");
                System.out.println("======================================");
                playerTurn(player2, player1, 2);
            }

            if (!player1.isAlive() || !player2.isAlive() || forceExit) { break; }

            player1Turn = !player1Turn;
            System.out.println("\n[Stamina Regeneration]");
            if(player1.isAlive()){
                System.out.print(player1.getName() + " (P1) ");
                player1.getStamina().regen();
            }
            if(player2.isAlive()){
                System.out.print(player2.getName() + " (P2) ");
                player2.getStamina().regen();
            }
            System.out.println("\nPress [ENTER] to continue to next round...\n");
            scanner.nextLine();
        }

        if (!forceExit) {
            displayBattleResult();
        } else {
             System.out.println("\n[ BATTLE ABORTED ]");
        }
        return player1.isAlive() ? player1 : player2;
    }

    //Player turn logic for both singleplayer and multiplayer - Bea
    private void playerTurn(Character attacker, Character defender, int mode){
        displayStats(attacker, defender, mode);
        displaySkills(attacker);
        System.out.print("\nChoose action: ");
        int action = getValidAction();
        
        if (action == 0) {
            forceExit = true;
            return;
        }

        switch (action) {
            case 1:
                if (attacker.getStamina().getCurrent() >= attacker.getBasicAttackStaminaCost()) {
                    attacker.getStamina().spend(attacker.getBasicAttackStaminaCost());
                    if(checkHit(attacker.getAccuracy().getBasicAccuracy())) {
                        attacker.basicAttack(defender);
                    }
                    else{
                        System.out.println(attacker.getName()+" misses their basic attack!");
                    }
                } else {
                    System.out.println("Not enough stamina!");
                }
                break;
            case 2:
                if (attacker.getStamina().getCurrent() >= attacker.getSpecialSkillStaminaCost()) {
                    attacker.getStamina().spend(attacker.getSpecialSkillStaminaCost());
                    if(checkHit(attacker.getAccuracy().getSpecialAccuracy())) {
                        attacker.specialSkill(defender);
                    }
                    else{
                        System.out.println(attacker.getName()+" misses their special skill!");
                    }
                }else{
                    System.out.println("Not enough stamina!");
                }
                break;
            case 3:
                if(attacker.getStamina().getCurrent() >= attacker.getUltimateSkillStaminaCost()){
                    attacker.getStamina().spend(attacker.getUltimateSkillStaminaCost());

                    if(checkHit(attacker.getAccuracy().getUltimateAccuracy())) {
                        attacker.ultimateSkill(defender);
                    }
                    else {
                            System.out.println(attacker.getName() + " misses their ultimate skill!");
                    }
                }else{
                    System.out.println("\n[WARNING] Not enough stamina! Need " + attacker.getUltimateSkillStaminaCost() + " stamina.");
                    System.out.println("Performing basic attack instead!");

                    if (attacker.getStamina().getCurrent() >= attacker.getBasicAttackStaminaCost()) {
                        attacker.getStamina().spend(attacker.getBasicAttackStaminaCost());
                        if(checkHit(attacker.getAccuracy().getBasicAccuracy())) {
                            attacker.basicAttack(defender);
                        }else{
                            System.out.println("Basic attack also misses... Unlucky.. ");
                        }
                    } else {
                        System.out.println("Not enough stamina for basic attack either! Turn wasted!");
                    }
                }
                break;
        }
    }

    //Validate player action input - Bea
    private int getValidAction() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            
            if (input.equals("X")) {
                return 0; // 0 signals an exit
            }
            
            try {
                int action = Integer.parseInt(input);
                if (action >= 1 && action <= 3) {
                    return action;
                } else {
                    System.out.print("Invalid choice! Please enter [1], [2], [3], or [X]: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number [1], [2], [3], or [X] to exit: ");
            }
        }
    }

    //AI turn logic for singleplayer - Bea
    private void aiTurn(Character ai, Character player){
        int action = decideAIAction(ai);

        switch (action){
            case 1:
                if(ai.getStamina().getCurrent() >= ai.getBasicAttackStaminaCost()){
                    ai.getStamina().spend(ai.getBasicAttackStaminaCost());

                    if(checkHit(ai.getAccuracy().getBasicAccuracy())) {
                        ai.basicAttack(player);
                    }
                    else{
                        System.out.println(ai.getName()+" misses their basic attack!");
                    }
                }else{
                    System.out.println("Not enough stamina!");
                }
                break;
            case 2:
                if(ai.getStamina().getCurrent() >= ai.getSpecialSkillStaminaCost()){
                    ai.getStamina().spend(ai.getSpecialSkillStaminaCost());

                    if(checkHit(ai.getAccuracy().getSpecialAccuracy())) {
                        ai.specialSkill(player);
                    }
                    else{
                        System.out.println(ai.getName()+" misses their special skill!");
                    }
                }else{
                    System.out.println("Not enough stamina!");
                }
                break;
            case 3:
                if(ai.getStamina().getCurrent() >= ai.getUltimateSkillStaminaCost()){
                    ai.getStamina().spend(ai.getUltimateSkillStaminaCost());

                    if(checkHit(ai.getAccuracy().getUltimateAccuracy())){
                        ai.ultimateSkill(player);
                    }
                    else{
                        System.out.println(ai.getName() + " misses their ultimate skill!");
                    }
                }else{
                    System.out.println("\n[WARNING] Not enough stamina! Need " + ai.getUltimateSkillStaminaCost() + " stamina.");
                    System.out.println("Performing basic attack instead!");

                    if(ai.getStamina().getCurrent() >= ai.getBasicAttackStaminaCost()){
                        ai.getStamina().spend(ai.getBasicAttackStaminaCost());
                        if(checkHit(ai.getAccuracy().getBasicAccuracy())) {
                            ai.basicAttack(player);
                        }
                        else{
                            System.out.println("Basic attack also misses... Unlucky.. ");
                        }
                    } else {
                        System.out.println("Not enough stamina for basic attack either!");
                    }
                }
                break;
        }
    }

    //AI decision-making logic - Bea
    private int decideAIAction(Character ai){
        int aiHPPercentage = (ai.getHp() * 100) / ai.getMaxHp();
        StaminaSystem stamina = ai.getStamina();

        int ultimateCost = ai.getUltimateSkillStaminaCost();
        int specialCost = ai.getSpecialSkillStaminaCost();

        // If low HP and enough stamina for ultimate
        if (aiHPPercentage < 30 && stamina.getCurrent() >= ultimateCost && Math.random() < 0.5) { return 3; }

        // If enough stamina for ultimate
        if (stamina.getCurrent() >= ultimateCost && Math.random() < 0.3) { return 3; }

        // If enough stamina for special
        if (stamina.getCurrent() >= specialCost && Math.random() < 0.4) {return 2;}
        return 1;
    }

    //Display HP and Stamina bars for both characters - Lou
    private void displayStats(Character c1, Character c2, int mode) {
        // mode: 1 = singleplayer, 2 = multiplayer
        System.out.println("\n======================================");

        if (mode == 1) {
            System.out.printf("%-15s VS %-15s%n", c1.getName() + " (You)", c2.getName() + " (Enemy)");
        } else {
            System.out.printf("%-15s VS %-15s%n", c1.getName() + " (P1)", c2.getName() + " (P2)");
        }
        System.out.println("--------------------------------------");

        System.out.printf("HP: %3d/%-3d      |      HP: %3d/%-3d%n",
                c1.getHp(), c1.getMaxHp(),
                c2.getHp(), c2.getMaxHp());

        System.out.printf("STA: %3d/%-3d     |      STA: %3d/%-3d%n",
                c1.getStamina().getCurrent(), c1.getStaminaMax(),
                c2.getStamina().getCurrent(), c2.getStaminaMax());

        System.out.println("======================================");
    }

    //Display battle intro - Lou
    private void displayBattleIntro(){
        System.out.println("\n======================================");
        System.out.println("          BATTLE START");
        System.out.println("======================================");
    }

    //Display battle result - Lou
    private void displayBattleResult(){
        System.out.println("\n======================================");
        System.out.println("         BATTLE FINISHED");
        System.out.println("======================================");
    }

    //Display Skills or Action - Lou
    private void displaySkills(Character character){
        System.out.println("\nAvailable Skills:");
        System.out.println("[1] (S1) " + character.getBasic() + " (Cost: " + character.getBasicAttackStaminaCost() + ")");
        System.out.println("[2] (S2) " + character.getSpecial() + " (Cost: " + character.getSpecialSkillStaminaCost() + ")");
        System.out.println("[3] (S3) " + character.getUltimate() + " (Cost: " + character.getUltimateSkillStaminaCost() + ")");
        System.out.println("[X] Exit to Menu");
    }
    private boolean checkHit(double accuracyRate) {
        return Math.random() <= accuracyRate;
    }
}