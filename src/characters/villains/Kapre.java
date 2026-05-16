package characters.villains;

import characters.Character;

public class Kapre extends Character {
    public Kapre() {
        super("Kapre", "Villain", 170, 17,100,
                "Suntok ng Higante",
                "Usok ng Sigarilyo",
                "Galit ng Bantay-Puno");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 18;
        this.ultimateSkillStaminaCost = 50;

        getStamina().setRegenRange(8, 20);
    }

    @Override
    public void basicAttack(Character target) {
        if(getStamina().spend(basicAttackStaminaCost)) {
            int damage = attack + random.nextInt(8);

            System.out.println("Umuuga ang lupa sa lakas ng suntok ng Kapre!");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");
        }
    }

    @Override
    public void specialSkill(Character target) {

        if (getStamina().spend(specialSkillStaminaCost)) {
            int damage = attack * 2 + random.nextInt(12);

            System.out.println(name + " uses " + skill2 + "!");
            System.out.println("Bumabalot ang makapal na usok... naliligaw ang diwa ng kalaban!");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");

            int heal = 12;
            hp = Math.min(maxHp, this.hp + heal);
            System.out.println(name + " restores " + heal + " HP.");
        }
    }

    @Override
    public void ultimateSkill(Character target) {

        if (getStamina().spend(ultimateSkillStaminaCost)) {
            int damage = attack * 3 + random.nextInt(25) + 10;

            System.out.println(name + " uses " + skill3 + "!");
            System.out.println("Nagngangalit ang Kapre sa sinumang lumalapastangan sa kanyang punong binabantayan!");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");
        }
    }
}