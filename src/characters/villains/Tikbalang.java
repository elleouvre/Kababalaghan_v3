package characters.villains;
import characters.Character;
import java.util.Random;

public class Tikbalang extends Character {
    private Random random = new Random();

    public Tikbalang() {
        super("Tikbalang", "Villain", 120, 30, 100,
                0.95,0.80,0.65,
                "Hoof Stomp",
                "Labyrinth of the Forest",
                "Gingagayuma (Bewitched)");


        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 25;
        this.ultimateSkillStaminaCost = 50;
        getStamina().setRegenRange(10, 20);
    }

    @Override
    public void basicAttack(Character target) {
        if (getStamina().spend(basicAttackStaminaCost)) {
            int damage = attack + random.nextInt(15);
            System.out.println(name + " uses " + skill1 + "!");
            System.out.println("Isang malakas na sipa mula sa mga bakal!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {
        if (getStamina().spend(specialSkillStaminaCost)) {
            int damage = attack + 10 + random.nextInt(10);
            System.out.println(name + " uses " + skill2 + "!");
            System.out.println("Nagsisimulang umikot ang mga landas sa kagubatan. Ang kalaban ay disoriented!");
            target.takeDamage(damage);

            // In a real battle manager, you'd add a "Confused" status here
            System.out.println(target.getName() + "'s accuracy has dropped!");
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (getStamina().spend(ultimateSkillStaminaCost)) {
            int damage = (attack * 4) + random.nextInt(20);
            System.out.println(name + " uses " + skill3 + "!");
            System.out.println("Isang makapal na usok ng tabako ang pumupuno sa hangin! Ang Tikbalang ay tumatama mula sa mga anino!");
            target.takeDamage(damage);

            // Speed buff: Restore some stamina to represent supernatural speed
            getStamina().add(20);  //
            System.out.println(name + " gumagalaw nang may supernatural na bilis, na nagbabalik ng 20 stamina!");
        }
    }

    @Override
    public void takeDamage(int damage) {
        // Passive: "Trickster's Haze"
        // 20% chance to take 0 damage by "vanishing" into the forest
        if (random.nextInt(100) < 20) {
            System.out.println(name + " nawala sa mga puno! Hindi nakuha ang pag-atake!");
            return;
        }
        super.takeDamage(damage);
    }
}