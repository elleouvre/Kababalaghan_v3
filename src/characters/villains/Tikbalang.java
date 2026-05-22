package characters.villains;

import characters.Character;
import util.Colors;

public class Tikbalang extends Character {

    public Tikbalang() {
        super("Tikbalang", "Villain", 3400, 245, 110,
                0.90, 0.85, 0.82,
                "Hoof Stomp",
                "Labyrinth of the Forest",
                "Gingagayuma (Bewitched)");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 25;
        this.ultimateSkillStaminaCost = 50;
        getStamina().setRegenRange(15, 25);
    }

    @Override
    public void basicAttack(Character target) {
        System.out.println(Colors.YELLOW + name + " uses " + skill1 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Isang malakas na sipa mula sa mga nagbabagang bakal!" + Colors.RESET);

        int damage = attack + random.nextInt(75);
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        System.out.println(Colors.YELLOW + name + " uses " + skill2 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Nagsisimulang umikot ang mga landas sa kagubatan. Ang kalaban ay disoriented!" + Colors.RESET);

        int damage = (attack * 2) + random.nextInt(85);
        target.takeDamage(damage);
        System.out.println(Colors.RED + "Bumaba ang accuracy ni " + target.getName() + "!" + Colors.RESET);
    }

    @Override
    public void ultimateSkill(Character target) {
        System.out.println(Colors.RED + name + " uses " + skill3 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Isang makapal na usok ng tabako ang pumupuno sa hangin! Ang Tikbalang ay tumatama mula sa mga anino!" + Colors.RESET);

        int damage = (attack * 3) + random.nextInt(140);
        target.takeDamage(damage);

        // Speed battery recharge mechanic
        getStamina().add(30);
        System.out.println(Colors.GREEN + name + " moves with supernatural speed, recovering 30 stamina instantly!" + Colors.RESET);
    }
}