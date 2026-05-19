package characters.heroes;

import characters.Character;

public class Magwayen extends Character {
    public Magwayen() {
        super("Magwayen", "Hero", 140, 15, 120,
                0.95,0.80,0.65,
                "Ulap ng Kalaliman",
                "Luha ng Karagatan",
                "Pagtawid sa Dagat ng mga Yumao");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 50;

        getStamina().setRegenRange(10, 25);
    }

    @Override
    public void basicAttack(Character target) {

        if (getStamina().spend(basicAttackStaminaCost)) {
            int damage = attack + random.nextInt(8);

            System.out.println("Umuusbong ang malamlam na ulap mula sa kailaliman...");
            System.out.println("Naliligaw ang diwa ng kalaban sa hamog nga mga yumao!");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");
            System.out.println(target.getName() + " feels weakened and disoriented.");
        }
    }

    @Override
    public void specialSkill(Character target) {

        if (getStamina().spend(specialSkillStaminaCost)) {
            int damage = attack * 2 + random.nextInt(15);

            System.out.println(name + " uses " + skill2 + "!");
            System.out.println("Isang matalim na agos ng tubig ang sumasalpok sa kalaban!");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");

            int heal = 12;
            hp = Math.min(maxHp, this.hp + heal);
            System.out.println(name + " absorbed lost souls and restores " + heal + " HP.");
        }

    }

    @Override
    public void ultimateSkill(Character target) {

        if (getStamina().spend(ultimateSkillStaminaCost)) {
            int damage = attack * 3 + random.nextInt(25);

            System.out.println(name + " uses " + skill3 + "!");
            System.out.println("Nagbubukas ang dagat ng mga yumao... hindi maiiwasan ang pagtawid!");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");

            // damages over time (extra hit)
            int soulDrain = 10;
            target.takeDamage(soulDrain);
            System.out.println("The spirits continue to drag " + target.getName() + "...");
            System.out.println(target.getName() + " suffers " + soulDrain + " soul damage.");
        }
    }
}
