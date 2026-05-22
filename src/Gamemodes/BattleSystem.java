package Gamemodes;

import characters.Character;
import characters.StaminaSystem;
import java.util.*;
import util.*;


public class BattleSystem {
    private Scanner scanner;
    private boolean forceExit = false;

    public BattleSystem(Scanner scanner){
        this.scanner = scanner;
    }


    //For Singleplayer (Player vs AI) - Lou
    public void startSingleplayer(Character player, Character ai){
        displayBattleIntro();
        System.out.println(Colors.YELLOW + "[ PLAYER VS AI BATTLE ]" + Colors.RESET);
        System.out.println(Colors.CYAN + player.getName() + " VS " + ai.getName() + Colors.RESET);
        System.out.println(Colors.CYAN + "Press Enter to start!" + Colors.RESET);
        scanner.nextLine();

        forceExit = false;

        while(player.isAlive() && ai.isAlive() && !forceExit){
            System.out.println();
            System.out.println(Colors.GREEN + "✠ ——— ✠ ——— ✠ ——— ✠" + Colors.RESET);
            System.out.println(Colors.YELLOW + "YOUR TURN (" + player.getName() + ")" + Colors.RESET);
            System.out.println(Colors.GREEN + "✠ ——— ✠ ——— ✠ ——— ✠" + Colors.RESET);
            playerTurn(player, ai,1);

            if(!ai.isAlive() || forceExit){
                break;
            }

            System.out.println(Colors.GREEN+"\nPress [ENTER] for the enemy's turn...\n"+Colors.RESET);
            scanner.nextLine();

            System.out.println(Colors.GREEN + "✠ ——— ✠ ——— ✠ ——— ✠" + Colors.RESET);
            System.out.println(Colors.YELLOW + "ENEMY TURN (" + ai.getName() + ")" + Colors.RESET);
            System.out.println(Colors.GREEN + "✠ ——— ✠ ——— ✠ ——— ✠" + Colors.RESET);
            aiTurn(ai, player);

            if(player.isAlive()){
                System.out.println(Colors.YELLOW+"\n[Stamina Regeneration]"+Colors.RESET);
                System.out.print(Colors.CYAN +player.getName() + " (You) "+Colors.RESET);
                player.getStamina().regen();
            }
            if(ai.isAlive()){
                System.out.print(Colors.CYAN +ai.getName() + " "+Colors.RESET);
                ai.getStamina().regen();
            }

            if (!forceExit && player.isAlive() && ai.isAlive()) {
                System.out.println(Colors.PURPLE+"\nPress [ENTER] to continue...\n"+ Colors.RESET);
                scanner.nextLine();
            }
        }
        if (!forceExit) {
            displayBattleResult();
        } else {
            System.out.println(Colors.RED+"\n[ BATTLE ABORTED ]"+ Colors.RESET);
        }
    }

    //For multiplayer (Player vs Player) - Bea
    public Character startMultiplayer(Character player1, Character player2, boolean player1GoesFirst){
        displayBattleIntro();
        System.out.println(Colors.YELLOW + "\n[ PLAYER VS PVP BATTLE ]" + Colors.RESET);
        System.out.println(Colors.CYAN + player1.getName() + " VS " + player2.getName() + Colors.RESET);
        System.out.println(Colors.CYAN + "Press [ENTER] to start!" + Colors.RESET);
        scanner.nextLine();

        boolean player1Turn = player1GoesFirst;
        forceExit = false;

        while(player1.isAlive() && player2.isAlive() && !forceExit){
            if(player1Turn){
                System.out.println(Colors.GREEN + "✠ ——— ✠ ——— ✠ ——— ✠" + Colors.RESET);
                System.out.println(Colors.YELLOW + "PLAYER 1'S TURN (" + player1.getName() + ")" + Colors.RESET);
                System.out.println(Colors.GREEN + "✠ ——— ✠ ——— ✠ ——— ✠" + Colors.RESET);
                playerTurn(player1, player2, 2);
            }else{
                System.out.println(Colors.GREEN + "✠ ——— ✠ ——— ✠ ——— ✠" + Colors.RESET);
                System.out.println(Colors.YELLOW + "PLAYER 2'S TURN (" + player2.getName() + ")" + Colors.RESET);
                System.out.println(Colors.GREEN + "✠ ——— ✠ ——— ✠ ——— ✠" + Colors.RESET);
                playerTurn(player2, player1, 2);
            }

            if (!player1.isAlive() || !player2.isAlive() || forceExit) { break; }

            player1Turn = !player1Turn;
            System.out.println(Colors.YELLOW + "\n[Stamina Regeneration]" + Colors.RESET);
            if(player1.isAlive()){
                System.out.print(Colors.CYAN + player1.getName() + " (P1) " + Colors.RESET);
                player1.getStamina().regen();
            }
            if(player2.isAlive()){
                System.out.print(Colors.CYAN + player2.getName() + " (P2) " + Colors.RESET);
                player2.getStamina().regen();
            }
            System.out.println(Colors.GREEN + "\nPress [ENTER] to continue to next round...\n" + Colors.RESET);
            scanner.nextLine();
        }

        if (!forceExit) {
            displayBattleResult();
        } else {
             System.out.println(Colors.RED+"\n[ BATTLE ABORTED ]"+Colors.RESET);
        }
        return player1.isAlive() ? player1 : player2;
    }

    //Player turn logic for both singleplayer and multiplayer - Bea
    private void playerTurn(Character attacker, Character defender, int mode){
        displayStats(attacker, defender, mode);
        displaySkills(attacker);
        System.out.print(Colors.CYAN + "\nChoose action: " + Colors.RESET);
        int action = getValidAction();

        if (action == 0) {
            forceExit = true;
            return;
        }

        switch (action) {
            case 1:
                int basicCost = attacker.getBasicAttackStaminaCost();
                if (attacker.getStamina().getCurrent() >= basicCost) {
                    attacker.getStamina().spend(basicCost);
                    System.out.println(Colors.CYAN + "Used " + basicCost + " stamina for " + attacker.getBasic() + Colors.RESET);

                    if(checkHit(attacker.getAccuracy().getBasicAccuracy())) {
                        attacker.basicAttack(defender);
                    } else {
                        System.out.println(Colors.RED + attacker.getName() + " misses their basic attack! (Stamina still consumed: -" + basicCost + ")" + Colors.RESET);
                    }
                } else {
                    System.out.println(Colors.RED + "Not enough stamina! Need " + basicCost + ", have " + attacker.getStamina().getCurrent() + Colors.RESET);
                }
                break;

            case 2:
                int specialCost = attacker.getSpecialSkillStaminaCost();
                if (attacker.getStamina().getCurrent() >= specialCost) {
                    attacker.getStamina().spend(specialCost);
                    System.out.println(Colors.CYAN + "Used " + specialCost + " stamina for " + attacker.getSpecial() + Colors.RESET);

                    if(checkHit(attacker.getAccuracy().getSpecialAccuracy())) {
                        attacker.specialSkill(defender);
                    } else {
                        System.out.println(Colors.RED + attacker.getName() + " misses their special skill! (Stamina still consumed: -" + specialCost + ")" + Colors.RESET);                    }
                } else {
                    System.out.println(Colors.RED + "Not enough stamina! Need " + specialCost + ", have " + attacker.getStamina().getCurrent() + Colors.RESET);                }
                break;

            case 3:
                int ultimateCost = attacker.getUltimateSkillStaminaCost();
                if (attacker.getStamina().getCurrent() >= ultimateCost) {
                    attacker.getStamina().spend(ultimateCost);
                    System.out.println(Colors.CYAN + "Used " + ultimateCost + " stamina for " + attacker.getUltimate() + Colors.RESET);
                    if(checkHit(attacker.getAccuracy().getUltimateAccuracy())) {
                        attacker.ultimateSkill(defender);
                    } else {
                        System.out.println(Colors.RED + attacker.getName() + " misses their ultimate skill! (Stamina still consumed: -" + ultimateCost + ")" + Colors.RESET);                    }
                } else {
                    System.out.println(Colors.RED + "\n[WARNING] Not enough stamina! Need " + ultimateCost + " stamina." + Colors.RESET);
                    System.out.println(Colors.CYAN + "Performing basic attack instead!" + Colors.RESET);

                    int basicFallbackCost = attacker.getBasicAttackStaminaCost();
                    if (attacker.getStamina().getCurrent() >= basicFallbackCost) {
                        attacker.getStamina().spend(basicFallbackCost);
                        System.out.println(Colors.CYAN + "Used " + basicFallbackCost + " stamina for fallback " + attacker.getBasic() + Colors.RESET);

                        if(checkHit(attacker.getAccuracy().getBasicAccuracy())) {
                            attacker.basicAttack(defender);
                        } else {
                            System.out.println(Colors.RED + "Basic attack also misses... (Stamina: -" + basicFallbackCost + ")" + Colors.RESET);
                        }
                    } else {
                        System.out.println(Colors.RED + "Not enough stamina for basic attack either! Turn wasted!" + Colors.RESET);                    }
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
                    System.out.print(Colors.RED + "Invalid choice! Please enter [1], [2], [3], or [X]: " + Colors.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.print(Colors.RED + "Please enter a valid number [1], [2], [3], or [X] to exit: " + Colors.RESET);
            }
        }
    }

    //AI turn logic for singleplayer - Bea
    private void aiTurn(Character ai, Character player){
        int action = decideAIAction(ai);

        switch (action){
            case 1:
                int basicCost = ai.getBasicAttackStaminaCost();
                if(ai.getStamina().getCurrent() >= basicCost){
                    ai.getStamina().spend(basicCost);
                    System.out.println(Colors.CYAN + ai.getName() + " used " + basicCost + " stamina for " + ai.getBasic() + Colors.RESET);

                    if(checkHit(ai.getAccuracy().getBasicAccuracy())) {
                        ai.basicAttack(player);
                    }
                    else{
                        System.out.println(Colors.RED + ai.getName() + " misses their basic attack! (Stamina still consumed: -" + basicCost + ")" + Colors.RESET);
                    }
                }else{
                    System.out.println(Colors.RED + "Not enough stamina! Need " + basicCost + ", have " + ai.getStamina().getCurrent() + Colors.RESET);
                }
                break;

            case 2:
                int specialCost = ai.getSpecialSkillStaminaCost();
                if(ai.getStamina().getCurrent() >= specialCost){
                    ai.getStamina().spend(specialCost);
                    System.out.println(Colors.CYAN + ai.getName() + " used " + specialCost + " stamina for " + ai.getSpecial() + Colors.RESET);

                    if(checkHit(ai.getAccuracy().getSpecialAccuracy())) {
                        ai.specialSkill(player);
                    }
                    else{
                        System.out.println(Colors.RED + ai.getName() + " misses their special skill! (Stamina still consumed: -" + specialCost + ")" + Colors.RESET);
                    }
                }else{
                    System.out.println(Colors.RED + "Not enough stamina! Need " + specialCost + ", have " + ai.getStamina().getCurrent() + Colors.RESET);
                }
                break;

            case 3:
                int ultimateCost = ai.getUltimateSkillStaminaCost();
                if(ai.getStamina().getCurrent() >= ultimateCost){
                    ai.getStamina().spend(ultimateCost);
                    System.out.println(Colors.CYAN + ai.getName() + " used " + ultimateCost + " stamina for " + ai.getUltimate() + Colors.RESET);

                    if(checkHit(ai.getAccuracy().getUltimateAccuracy())){
                        ai.ultimateSkill(player);
                    }
                    else{
                        System.out.println(Colors.RED + ai.getName() + " misses their ultimate skill! (Stamina still consumed: -" + ultimateCost + ")" + Colors.RESET);
                    }
                }else{
                    System.out.println(Colors.RED + "\n[WARNING] Not enough stamina! Need " + ultimateCost + " stamina." + Colors.RESET);
                    System.out.println(Colors.CYAN + "Performing basic attack instead!" + Colors.RESET);

                    int basicFallbackCost = ai.getBasicAttackStaminaCost();
                    if(ai.getStamina().getCurrent() >= basicFallbackCost){
                        ai.getStamina().spend(basicFallbackCost);
                        System.out.println(Colors.CYAN + ai.getName() + " used " + basicFallbackCost + " stamina for fallback " + ai.getBasic() + Colors.RESET);

                        if(checkHit(ai.getAccuracy().getBasicAccuracy())) {
                            ai.basicAttack(player);
                        }
                        else{
                            System.out.println(Colors.RED + "Basic attack also misses... (Stamina still consumed: -" + basicFallbackCost + ")" + Colors.RESET);
                        }
                    } else {
                        System.out.println(Colors.RED + "Not enough stamina for basic attack either! Need " + basicFallbackCost + ", have " + ai.getStamina().getCurrent() + Colors.RESET);
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
        System.out.println(Colors.GREEN + "\n======================================" + Colors.RESET);

        if (mode == 1) {
            System.out.printf(Colors.CYAN + "%-15s" + Colors.RESET + " VS " + Colors.RED + "%-15s%n" + Colors.RESET, c1.getName() + " (You)", c2.getName() + " (Enemy)");
        } else {
            System.out.printf(Colors.CYAN + "%-15s" + Colors.RESET + " VS " + Colors.CYAN + "%-15s%n" + Colors.RESET, c1.getName() + " (P1)", c2.getName() + " (P2)");
        }
        System.out.println(Colors.GREEN + "--------------------------------------" + Colors.RESET);
        System.out.print(Colors.RED);
        System.out.printf("HP: %3d/%-3d      |      HP: %3d/%-3d%n",
                c1.getHp(), c1.getMaxHp(),
                c2.getHp(), c2.getMaxHp());

        System.out.printf("STA: %3d/%-3d     |      STA: %3d/%-3d%n",
                c1.getStamina().getCurrent(), c1.getStaminaMax(),
                c2.getStamina().getCurrent(), c2.getStaminaMax());
        System.out.print(Colors.RESET);
        System.out.println(Colors.GREEN + "======================================" + Colors.RESET);
    }

    //Display battle intro - Lou
    private void displayBattleIntro(){
        System.out.println(Colors.GREEN + "\n======================================" + Colors.RESET);
        System.out.println(Colors.YELLOW + "          BATTLE START" + Colors.RESET);
        System.out.println(Colors.GREEN + "======================================" + Colors.RESET);
    }

    //Display battle result - Lou
    private void displayBattleResult(){
        System.out.println(Colors.GREEN + "\n======================================" + Colors.RESET);
        System.out.println(Colors.YELLOW + "         BATTLE FINISHED" + Colors.RESET);
        System.out.println(Colors.GREEN + "======================================" + Colors.RESET);
    }

    //Display Skills or Action - Lou
    private void displaySkills(Character character){
        System.out.println(Colors.YELLOW + "\nAvailable Skills:" + Colors.RESET);
        System.out.print(Colors.RED);
        System.out.println("Current Stamina: " + character.getStamina().getCurrent() + "/" + character.getStaminaMax());
        System.out.print(Colors.RESET);
        System.out.println(Colors.CYAN + "[1] " + character.getBasic() + " (Cost: " + character.getBasicAttackStaminaCost() + ")");
        System.out.println("[2] " + character.getSpecial() + " (Cost: " + character.getSpecialSkillStaminaCost() + ")");
        System.out.println("[3] " + character.getUltimate() + " (Cost: " + character.getUltimateSkillStaminaCost() + ")" + Colors.RESET);
        System.out.println(Colors.PURPLE + "[X] Exit to Menu" + Colors.RESET);
    }
    private boolean checkHit(double accuracyRate) {
        return Math.random() <= accuracyRate;
    }
}