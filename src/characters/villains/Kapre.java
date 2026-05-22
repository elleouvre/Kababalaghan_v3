package characters.villains;

import characters.Character;
import util.Colors;

public class Kapre extends Character {
    public Kapre() {
        super("Kapre", "Villain", 3700, 220, 100,
                0.96, 0.84, 0.69,
                "Suntok ng Higante",
                "Usok ng Sigarilyo",
                "Galit ng Bantay-Puno");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 18;
        this.ultimateSkillStaminaCost = 50;
        getStamina().setRegenRange(10, 20);
    }

    @Override
    public void basicAttack(Character target) {
        System.out.println(Colors.CYAN + "Umuuga ang lupa sa lakas ng suntok ng Kapre!" + Colors.RESET);
        int damage = attack + random.nextInt(60);
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        System.out.println(Colors.YELLOW + name + " uses " + skill2 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Bumabalot ang makapal na usok... naliligaw ang diwa ng kalaban!" + Colors.RESET);

        int damage = (attack * 2) + random.nextInt(75);
        target.takeDamage(damage);

        int heal = 180; // Scaled to meet high health pools
        this.hp = Math.min(maxHp, this.hp + heal);
        System.out.println(Colors.GREEN + name + " inhales cigar energy and restores " + heal + " HP." + Colors.RESET);
    }

    @Override
    public void ultimateSkill(Character target) {
        System.out.println(Colors.RED + name + " uses " + skill3 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Nagngangalit ang Kapre sa sinumang lumalapastangan sa kanyang punong binabantayan!" + Colors.RESET);

        int damage = (attack * 3) + random.nextInt(150);
        target.takeDamage(damage);
    }
}