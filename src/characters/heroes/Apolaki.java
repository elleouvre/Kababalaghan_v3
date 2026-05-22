package characters.heroes;

import characters.Character;
import util.Colors;

public class Apolaki extends Character {

    public Apolaki() {
        super("Apolaki", "Hero", 3550, 243, 100,
                0.95, 0.89, 0.82,
                "Banal ng Digmaan",
                "Bagsik ng Araw",
                "Paghampas ng Bathalang Mandirigma!");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 15;
        this.ultimateSkillStaminaCost = 50;

        getStamina().setRegenRange(20, 35);
    }

    // burst mechanic
    int burstStacks = 0;

    @Override
    public void basicAttack(Character target) {

        System.out.println(Colors.CYAN + "Umiinit ang hangin sa bawat galaw ni Apolaki!" + Colors.RESET);
        System.out.println(Colors.YELLOW + name + " uses " + skill1 + "!" + Colors.RESET);

        int damage = attack + random.nextInt(50);
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        int damage = attack + random.nextInt(100);

        burstStacks++;
        if (burstStacks > 5) burstStacks = 5;

        System.out.println(Colors.YELLOW + name + " uses " + skill2 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Lumalakas ang init ng digmaan! [" + (burstStacks * 20) + "% charge]" + Colors.RESET);

        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target) {
        int damage = attack * 2 + random.nextInt(175);
        int chance = burstStacks * 20;

        System.out.println(Colors.YELLOW + name + " uses " + skill3 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Ang mundo ay yayanig sa ilalim ng hatol ng walang hanggang sinag!" + Colors.RESET);

        if (random.nextInt(100) < chance) {
            damage *= 2;
            System.out.println(Colors.RED + "🔥 BURST x2! PAGHUKOM NI APOLAKI!" + Colors.RESET);
        }

        burstStacks = 0;
        target.takeDamage(damage);
    }
}