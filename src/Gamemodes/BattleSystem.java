package Gamemodes;

import characters.Character;
import characters.StaminaSystem;

import java.util.*;


public class BattleSystem {
    private Scanner scanner;


    public BattleSystem(Scanner scanner){
        this.scanner = scanner;
    }

    //For Singleplayer (Player vs AI) - Lou
    public void startSingleplayer(Character player, Character ai){
        System.out.println("[ PLAYER VS AI BATTLE ]");
        System.out.println(player.getName() + " VS " + ai.getName());
        System.out.println("Press Enter to start!");
        scanner.nextLine();

        while(player.isAlive() && ai.isAlive()){
            displayStats(player, ai);

            System.out.println("\n======================================");
            System.out.println("YOUR TURN (" + player.getName() + ")");
            System.out.println("======================================");
            playerTurn(player, ai);

            if(!ai.isAlive()){
                break;
            }
            System.out.println("\n======================================");
            System.out.println("ENEMY TURN (" + ai.getName() + ")");
            System.out.println("======================================");
            aiTurn(ai, player);

            if(player.isAlive()){
                System.out.println("\n[Stamina Regeneration]");
                player.getStamina().regen();
            }
            if(ai.isAlive()){
                ai.getStamina().regen();
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
        displayBattleResult();
    }

    //For multiplayer (Player vs Player) - Bea
    public Character startMultiplayer(Character player1, Character player2){
        System.out.println("\n[ PLAYER VS PLAYER BATTLE ]");
        System.out.println(player1.getName() + " VS " + player2.getName());
        System.out.println("Press Enter to start!");
        scanner.nextLine();

        boolean player1Turn = true;

        while(player1.isAlive() && player2.isAlive()){
            displayStats(player1, player2);

            if(player1Turn){
                System.out.println("\n======================================");
                System.out.println("PLAYER 1'S TURN (" + player1.getName() + ")");
                System.out.println("======================================");
                playerTurn(player1, player2);
            }else{
                System.out.println("\n======================================");
                System.out.println("PLAYER 2'S TURN (" + player2.getName() + ")");
                System.out.println("======================================");
                playerTurn(player2, player1);
            }
            player1Turn = !player1Turn;
            if(player1.isAlive()){
                System.out.println("\n[Stamina Regeneration]");
                player1.getStamina().regen();
            }
            if(player2.isAlive()){
                player2.getStamina().regen();
            }
            System.out.println("\nPress Enter to continue to next round...");
            scanner.nextLine();

            displayBattleResult();
        }
        return player1;
    }

    private boolean checkHit(double accuracyRate) {
        return Math.random() <= accuracyRate;
    }

    //Player turn logic for both singleplayer and multiplayer - Bea
    private void playerTurn(Character attacker, Character defender){
        displayStats(attacker, defender);
        displaySkills(attacker);
        System.out.print("\nChoose action: ");
        int action = getValidAction();

        switch (action) {
            case 1:
                if (attacker.getStamina().spend(attacker.getBasicAttackStaminaCost())) {
                    if(checkHit(attacker.getAccuracy().getBasicAccuracy())){
                        attacker.basicAttack(defender);
                    }else {
                        System.out.println(attacker.getName()+" misses their basic attack! ");
                    }
                }else{
                    System.out.println("Not enough stamina!");
                    }
                break;
            case 2:
                if(attacker.getStamina().spend(attacker.getSpecialSkillStaminaCost())){
                    if(checkHit(attacker.getAccuracy().getSpecialAccuracy())) {
                        attacker.specialSkill(defender);
                    }else{
                        System.out.println(attacker.getName()+" misses their special attack! ");
                    }
                }else{
                    System.out.println("Not enough stamina!");
                 }
                break;
            case 3:
                if(attacker.getStamina().spend(attacker.getUltimateSkillStaminaCost())){
                    if(checkHit(attacker.getAccuracy().getUltimateAccuracy())) {
                        attacker.ultimateSkill(defender);
                    }
                    else{
                        System.out.println(attacker.getName()+" misses their ultimate attack! ");
                    }
                }else{
                    System.out.println("\n[WARNING] Not enough stamina! Need " + attacker.getUltimateSkillStaminaCost() + " stamina.");
                    System.out.println("Performing basic attack instead!");
                    if (checkHit(attacker.getAccuracy().getBasicAccuracy())) {
                        attacker.basicAttack(defender);
                    }else{
                        System.out.println("Basic attack also misses. Unlucky.");
                    }
                }
                break;
        }
        System.out.println("\n" + defender.getName() + " HP: " + defender.getHp() + "/" + defender.getMaxHp());

    }

    //Validate player action input - Bea
    private int getValidAction() {
        while(true){
            System.out.println("Choose (1-3): ");
            String in = scanner.nextLine();

            switch(in){
                case "1":
                case "2":
                case "3":
                    return Integer.parseInt(in);
                default:
                    System.out.println("Invalid! Enter 1-3 only.");
            }
        }
    }

    //AI turn logic for singleplayer - Bea
    private void aiTurn(Character ai, Character player){
        int action = decideAIAction(ai);

        switch (action){
            case 1:
                if(ai.getStamina().spend(ai.getBasicAttackStaminaCost())) {
                    if (checkHit(ai.getAccuracy().getBasicAccuracy())) {
                        ai.basicAttack(player);
                    } else {
                        System.out.println(ai.getName()+" misses their basic attack! ");
                    }
                }else{
                        System.out.println("Not enough stamina!");
                    }
                break;
            case 2:
                if(ai.getStamina().spend(ai.getSpecialSkillStaminaCost())){
                    if (checkHit(ai.getAccuracy().getSpecialAccuracy())) {
                        ai.specialSkill(player);
                    }
                    else{
                        System.out.println(ai.getName()+" misses their special attack! ");
                    }
                }else{
                    System.out.println("Not enough stamina!");
                }
                break;
            case 3:
                if(ai.getStamina().spend(ai.getUltimateSkillStaminaCost())){
                    if (checkHit(ai.getAccuracy().getUltimateAccuracy())) {
                        ai.ultimateSkill(player);
                    }
                    else{
                        System.out.println(ai.getName()+" misses their ultimate attack! ");
                    }
                }else{
                    System.out.println("\n[WARNING] Not enough stamina! Need " + ai.getUltimateSkillStaminaCost() + " stamina.");
                    System.out.println("Performing basic attack instead!");
                    if (checkHit(ai.getAccuracy().getBasicAccuracy())) {
                        ai.basicAttack(player);
                    }else{
                        System.out.println("Basic attack also misses. Unlucky.");
                    }
                }
                break;
        }
        System.out.println("\n" + player.getName() + " HP: " + player.getHp() + "/" + player.getMaxHp());
    }

    //AI decision-making logic - Bea
    private int decideAIAction(Character ai){
        int aiHPPercentage = (ai.getHp() * 100) / ai.getMaxHp();
        StaminaSystem stamina = ai.getStamina();

        int ultimateCost = ai.getUltimateSkillStaminaCost();
        int specialCost = ai.getSpecialSkillStaminaCost();

        // If low HP and enough stamina for ultimate
        if (aiHPPercentage < 30 &&
                stamina.getCurrent() >= ultimateCost &&
                Math.random() < 0.5) {
            return 3;
        }

        // If enough stamina for ultimate
        if (stamina.getCurrent() >= ultimateCost &&
                Math.random() < 0.3) {
            return 3;
        }

        // If enough stamina for special
        if (stamina.getCurrent() >= specialCost &&
                Math.random() < 0.4) {
            return 2;
        }

        // Default basic attack
        return 1;
    }

    //Display HP and Stamina bars for both characters - Lou
    private void displayStats(Character c1, Character c2){
        System.out.println("\n======================================");
        System.out.printf("%-15s VS %-15s%n", c1.getName(), c2.getName());
        System.out.println("--------------------------------------");

        System.out.println(c1.getName());
        System.out.println("HP: " + c1.getHp() + "/" + c1.getMaxHp());
        System.out.println("STA: " + c1.getStamina().getCurrent() + "/" + c1.getStaminaMax());
        System.out.println();

        System.out.println(c2.getName());
        System.out.println("HP: " + c2.getHp() + "/" + c2.getMaxHp());
        System.out.println("STA: " + c2.getStamina().getCurrent() + "/" + c2.getStaminaMax());
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
        System.out.println("1. " + character.getBasic() + " (Cost: " + character.getBasicAttackStaminaCost() + ")");
        System.out.println("2. " + character.getSpecial() + " (Cost: " + character.getSpecialSkillStaminaCost() + ")");
        System.out.println("3. " + character.getUltimate() + " (Cost: " + character.getUltimateSkillStaminaCost() + ")");
    }

}