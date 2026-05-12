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

    public Character(String name, String type, int maxHp, int attack, int stamina, int maxStamina,
                     String basicAttackName, String specialSkillName, String ultimateSkillName) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.stamina = maxStamina;
        this.maxStamina = maxStamina;
        this.attack = attack;
    }
}
