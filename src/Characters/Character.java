package Characters;

import java.util.Random;

public abstract class Character {
    protected String name;
    protected int hp;
    protected String type;
    protected int maxHp;
    protected int stamina;
    protected int maxStamina;
    protected int attack;
    protected boolean isAlive = true;
    protected Random random = new Random();

    public String skill1;
    protected String skill2;
    protected String skill3;

    public Character(String name,String type,int maxHp, int attack, int stamina, int maxStamina,
                     String skill1, String skill2, String skill3) {
        this.name = name;
        this.type = type;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.stamina = maxStamina;
        this.maxStamina = maxStamina;
        this.attack = attack;

        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
    }

    public abstract void basicAttack(Character target);
    protected int basicAttackStaminaCost = 0;
    public abstract void specialSkill(Character target);
    protected int specialSkillStaminaCost = 20;
    public abstract void ultimateSkill(Character target);
    protected int ultimateSkillStaminaCost = 50;

    public void takeDamage(int damage){
        this.hp -= damage;

        // constraint
        if (hp < 0) hp = 0;

        System.out.println(name + " has taken " + damage + " damage! HP: " + hp + "/" + maxHp);

        // check if dead

        if (this.hp <= 0){
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
    public int getStamina() {
        return stamina;
    }
    public int getMaxStamina() {
        return maxStamina;
    }
    public int getAttack() {
        return attack;
    }
    public String getBasic() { return skill1; }
    public String getSpecial() { return skill2; }
    public String getUltimate() { return skill3; }
}
