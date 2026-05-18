package characters;

import java.util.Random;

public abstract class Character {
    protected String name;
    protected int hp;
    protected String type;
    protected int maxHp;

    protected int attack;
    public boolean isAlive = true;
    protected Random random = new Random();

    private StaminaSystem stamina;

    // attack/skills
    protected String skill1;
    protected String skill2;
    protected String skill3;

    // skill costs
    protected int basicAttackStaminaCost = 0;
    protected int specialSkillStaminaCost = 20;
    protected int ultimateSkillStaminaCost = 50;

    public Character(String name,String type,int maxHp, int attack, int maxStamina,
                     String skill1, String skill2, String skill3) {
        this.name = name;
        this.type = type;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.stamina = new StaminaSystem(maxStamina, 15, 25); // Default regen between 15-25
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
    public StaminaSystem getStamina() {
        return stamina;
    }

    public int getBasicAttackStaminaCost() { return basicAttackStaminaCost; }
    public int getSpecialSkillStaminaCost() { return specialSkillStaminaCost; }
    public int getUltimateSkillStaminaCost() { return ultimateSkillStaminaCost; }

    public boolean isAlive() {return isAlive; }
}
