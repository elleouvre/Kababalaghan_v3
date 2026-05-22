package characters.heroes;

import characters.Character;
import util.Colors;

public class Magwayen extends Character {
    public Magwayen() {
        super("Magwayen", "Hero", 3800, 210, 120,
                0.90, 0.83, 0.68,
                "Ulap ng Kalaliman",
                "Luha ng Karagatan",
                "Pagtawid sa Dagat ng mga Yumao");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 50;
        getStamina().setRegenRange(15, 25);
    }

    @Override
    public void basicAttack(Character target) {
        System.out.println(Colors.CYAN + "Umuusbong ang malamlam na ulap mula sa kailaliman..." + Colors.RESET);
        System.out.println(Colors.YELLOW + name + " uses " + skill1 + "!" + Colors.RESET);

        int damage = attack + random.nextInt(50);
        target.takeDamage(damage);
        System.out.println(Colors.RED + target.getName() + " feels weakened and disoriented." + Colors.RESET);
    }

    @Override
    public void specialSkill(Character target) {
        System.out.println(Colors.YELLOW + name + " uses " + skill2 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Isang matalim na agos ng tubig ang sumasalpok sa kalaban!" + Colors.RESET);

        int damage = attack * 2 + random.nextInt(80);
        target.takeDamage(damage);

        int heal = 150;
        hp = Math.min(maxHp, this.hp + heal);
        System.out.println(Colors.GREEN + name + " absorbed lost souls and restores " + heal + " HP." + Colors.RESET);
    }

    @Override
    public void ultimateSkill(Character target) {
        System.out.println(Colors.YELLOW + name + " uses " + skill3 + "!" + Colors.RESET);
        System.out.println(Colors.RED + "Nagbubukas ang dagat ng mga yumao... hindi maiiwasan ang pagtawid!" + Colors.RESET);

        int damage = attack * 3 + random.nextInt(150);
        target.takeDamage(damage);

        int soulDrain = 120; // Upcycled Damage over time component
        target.takeDamage(soulDrain);
        System.out.println(Colors.RED + "The spirits continue to drag " + target.getName() + "..." + Colors.RESET);
        System.out.println(Colors.RED + target.getName() + " suffers " + soulDrain + " extra soul damage." + Colors.RESET);
    }
}