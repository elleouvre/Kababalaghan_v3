/*
package z_broken_characters;
import characters.Character;

public class Mayari extends Character {
    private boolean isReadyToCounter = false;

    public Mayari() {
        super("Mayari", "Hero", 140, 28, 100,
                "Piercing Moonlight",
                "Shroud of the One-Eyed Queen",
                "Decree of the Celestial Divide");

        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 45;
    }

    @Override
    public void basicAttack(Character target) {
        // Simple 50/50 chance for bonus effects instead of turn tracking
        int damage = attack + random.nextInt(15);
        System.out.println("Mayari thrusts her spear! 'The shadows do not hide you.'");

        if (random.nextBoolean()) {
            System.out.println("Critical Hit! The Full Moon empowers the strike!");
            damage += 10;
        }
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        if (spendStamina(specialSkillStaminaCost)) {
            System.out.println(name + " enters a Counter Stance! 'Focus on what you can see...'");
            this.counterReady = true; // Sets the flag for the next hit
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (spendStamina(ultimateSkillStaminaCost)) {
            int damage = attack * 3 + 20;
            System.out.println("The sky splits! 'I am the sky's true master!'");
            target.takeDamage(damage);

            // Simple shield logic
            this.hp = Math.min(maxHp, this.hp + 30);
            System.out.println("A Silver Shield protects Mayari!");
        }
    }

    @Override
    public void takeDamage(int damage) {
        // If counter is on, take 0 and heal
        if (counterReady) {
            System.out.println("Mayari parries! 0 damage taken and she recovers 20 HP!");
            this.hp = Math.min(maxHp, this.hp + 20);
            counterReady = false;
            return;
        }

        // Permanent 20% reduction to keep it simple instead of toggling
        int finalDamage = (int)(damage * 0.80);
        super.takeDamage(finalDamage);
    }
}
*/
