package characters.heroes;
import characters.Character;
import java.util.Random;

public class Mayari extends Character {
    private Random random = new Random();

    public Mayari() {
        super("Mayari", "Hero", 140, 28, 100,
                0.95, 0.80, 0.65,
                "Tumatagos ang Liwanag ng Buwan",
                "Saplot ng One-Eyed Queen",
                "Dekreto ng Celestial Divide");

        this.basicAttackStaminaCost = 5;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 45;
        getStamina().setRegenRange(20, 35);
    }

    @Override
    public void basicAttack(Character target) {
        if (getStamina().spend(basicAttackStaminaCost)) {
            int damage = attack + random.nextInt(15);
            System.out.println(name + " uses " + skill1 + "!");
            System.out.println("Itinutok ni Mayari ang kanyang sibat! 'Hindi ka itinatago ng mga anino.'");

            if (random.nextBoolean()) {
                System.out.println("Kritikal na Hit! Ang Full Moon ay nagbibigay kapangyarihan sa strike!");
                damage += 10;
            }
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {
        if (getStamina().spend(specialSkillStaminaCost)) {
            int damage = attack * 2 + random.nextInt(15);
            System.out.println(name + " uses " + skill2 + "!");
            System.out.println("Ang talim ng gasuklay ni Mayari ay tumatawid sa larangan ng digmaan!");
            target.takeDamage(damage);

            // Simple buff
            int healAmount = 15;
            this.hp = Math.min(maxHp, this.hp + healAmount);
            System.out.println("Nagbabalik ang liwanag ng buwan " + healAmount + " HP kay " + name + "!");
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (getStamina().spend(ultimateSkillStaminaCost)) {
            int damage = attack * 3 + 20 + random.nextInt(15);
            System.out.println(name + " uses " + skill3 + "!");
            System.out.println("Nahati ang langit! 'Ako ang tunay na panginoon ng langit!'");

            target.takeDamage(damage);

            // Heal/shield effect
            int healAmount = 30;
            this.hp = Math.min(maxHp, this.hp + healAmount);
            System.out.println("Nag-restore ang isang Silver Shield " + healAmount + " HP to " + name + "!");
        }
    }
}