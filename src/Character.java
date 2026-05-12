import java.util.Random;

public abstract class Character {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int stamina;
    protected int maxStamina;
    protected int attack;
    protected boolean isAlive = true;
    protected Random random = new Random();

    protected String skill1;
    protected String skill2;
    protected String skill3;

    public Character(String name, int maxHp, int attack, int stamina, int maxStamina,
                     String skill1, String skill2, String skill3) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.stamina = maxStamina;
        this.maxStamina = maxStamina;
        this.attack = attack;

        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
    }

    public abstract void useSkill1(Character target);
    public abstract void useSkill2(Character target);
    public abstract void useSkill3(Character target);

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
}
