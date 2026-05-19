package characters.heroes;
import characters.Character;
import java.util.Random;

public class Mayari extends Character {
    private boolean isReadyToCounter = false;
    private Random random = new Random();

    public Mayari() {
        super("Mayari", "Hero", 140, 28, 100,
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
            System.out.println(name + " uses " + skill2 + "!");
            System.out.println("Pumasok si Mayari sa isang Counter Stance! 'Focus sa kung ano ang makikita mo...'");
            this.isReadyToCounter = true;

            // Counter stance doesn't deal damage, just prepares for next hit
            System.out.println("Handa nang kontrahin ang susunod na pag-atake!");
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

    @Override
    public void takeDamage(int damage) {
        // Counter stance logic
        if (isReadyToCounter) {
            System.out.println(name + " pinipigilan ang pag-atake! 0 pinsala ang nakuha at nabawi ang 20 HP!");
            int healAmount = 20;
            this.hp = Math.min(maxHp, this.hp + healAmount);
            isReadyToCounter = false;
            return;
        }

        // Permanent 20% damage reduction
        int finalDamage = (int)(damage * 0.80);
        super.takeDamage(finalDamage);
        System.out.println(name + " binabawasan ang pinsala ng 20% dahil sa pagpapala ng buwan!");
    }
}
