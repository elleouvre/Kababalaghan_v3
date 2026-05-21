package characters.villains;
import characters.Character;
import java.util.Random;

public class Tikbalang extends Character {
    private Random random = new Random();

    public Tikbalang() {
        super("Tikbalang", "Villain", 2640, 275, 110,
                0.90,0.85,0.82,
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
            int damage = attack + random.nextInt(75);
            System.out.println(name + " uses " + skill1 + "!");
            System.out.println("Isang malakas na sipa mula sa mga bakal!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {
        if (getStamina().spend(specialSkillStaminaCost)) {
            int damage = attack + 10 + random.nextInt(50);
            System.out.println(name + " uses " + skill2 + "!");
            System.out.println("Nagsisimulang umikot ang mga landas sa kagubatan. Ang kalaban ay disoriented!");
            target.takeDamage(damage);

            // In a real battle manager, you'd add a "Confused" status here
            System.out.println("bumaba ang accuracy ni" + target.getName() + " !");
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (getStamina().spend(ultimateSkillStaminaCost)) {
            int damage = (attack * 4) + random.nextInt(114);
            System.out.println(name + " uses " + skill3 + "!");
            System.out.println("Isang makapal na usok ng tabako ang pumupuno sa hangin! Ang Tikbalang ay tumatama mula sa mga anino!");
            target.takeDamage(damage);

            // Speed buff: Restore some stamina to represent supernatural speed
            getStamina().add(20);  //
            System.out.println(name + " gumagalaw nang may supernatural na bilis, na nagbabalik ng 20 stamina!");
        }
    }
}