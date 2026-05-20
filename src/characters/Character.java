package characters;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

import static characters.CharacterFactory.getAllHeroes;
import static characters.CharacterFactory.getAllVillains;

public abstract class Character {
    protected String name;
    protected int hp;
    protected String type;
    protected int maxHp;

    protected int attack;
    private boolean isAlive = true;
    protected Random random = new Random();

    private StaminaSystem stamina;
    private AccuracySystem accuracy;
    // attack/skills
    protected String skill1;
    protected String skill2;
    protected String skill3;

    // skill costs
    protected int basicAttackStaminaCost = 0;
    protected int specialSkillStaminaCost = 20;
    protected int ultimateSkillStaminaCost = 50;

    public Character(String name,String type,int maxHp, int attack, int maxStamina,//basic stats
                     double basicAcc, double specialAcc, double ultAcc,
                     String skill1, String skill2, String skill3) {
        this.name = name;
        this.type = type;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.stamina = new StaminaSystem(maxStamina, 15, 25); // Default regen between 15-25
        this.accuracy = new AccuracySystem(basicAcc, specialAcc, ultAcc);
        this.attack = attack;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
    }

    public abstract void basicAttack(Character target);
    public abstract void specialSkill(Character target);
    public abstract void ultimateSkill(Character target);

    public void takeDamage(int damage){
        this.hp -= damage;
        if (hp < 0) hp = 0;// constraint
        System.out.println(name + " has taken " + damage + " damage! HP: " + hp + "/" + maxHp);
        if (this.hp <= 0){ // check if dead
            isAlive = false;
            System.out.println(name + " has died!");
        }
    }
    //Reset Methods for new battles
    public void resetHp() {
        this.hp = this.maxHp;
        this.isAlive = true;
    }

    public void resetStamina() {
        this.stamina.reset();
    }

    public void resetAll() {
        resetHp();
        resetStamina();
    }

    public static Character chooseCharacter(Scanner scanner, boolean[] isHero) {
        System.out.println("\nChoose your side:");
        System.out.println("1. Hero");
        System.out.println("2. Villain");
        System.out.print("Choice: ");
        int side = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Character> characters;
        String type;

        switch(side) {
            case 1:
                characters = getAllHeroes();
                type = "Hero";
                isHero[0] = true;
                break;
            case 2:
                characters = getAllVillains();
                type = "Villain";
                isHero[0] = false;
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Hero.");
                isHero[0] = true;
                return getAllHeroes().get(0);
        }

        System.out.println("\nChoose your " + type + ":");
        System.out.println("--------------------------------------");

        for(int i = 0; i < characters.size(); i++) {
            Character c = characters.get(i);
            System.out.printf("%d. %-15s HP: %3d | ATK: %3d | STA: %3d%n",
                    (i + 1), c.getName(), c.getMaxHp(), c.getAttack(), c.getStaminaMax());
            System.out.printf("   Skills: %s, %s, %s%n",
                    c.getBasic(), c.getSpecial(), c.getUltimate());
        }

        System.out.print("\nChoice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Character selected;

        if (choice >= 1 && choice <= characters.size()) {
            selected = characters.get(choice - 1);
        } else {
            selected = characters.get(0);
        }

        if(choice < 1 || choice > characters.size()) {
            System.out.println("Invalid choice! Defaulting to first character.");
        }

        System.out.println("\n[You chose: " + selected.getName() + "]");
        return selected;
    }

    //Getters
    public String getName() {
        return name;
    }
    public int getHp() {
        return hp;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public int getAttack() {
        return attack;
    }

    public String getBasic() { return skill1; }
    public String getSpecial() { return skill2; }
    public String getUltimate() { return skill3; }
    public StaminaSystem getStamina() { return stamina; }
    public AccuracySystem getAccuracy(){return accuracy;}

    public int getStaminaMax() { return stamina.getMax();}
    public int getStaminaRegenMin() { return stamina.getRegenMin(); }
    public int getStaminaRegenMax() { return stamina.getRegenMax(); }

    public int getBasicAttackStaminaCost() { return basicAttackStaminaCost; }
    public int getSpecialSkillStaminaCost() { return specialSkillStaminaCost; }
    public int getUltimateSkillStaminaCost() { return ultimateSkillStaminaCost; }

    public boolean isAlive() { return isAlive; }
}
