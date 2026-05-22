package characters.heroes;

import characters.Character;
import util.Colors; // Imported for console color styling

public class MariaMakiling extends Character {
    public MariaMakiling() {
        super("Maria Makiling", "Hero", 3000, 255, 110,
                0.80, 0.90, 0.70,
                "Hampas ng Baging",
                "Lunas ng Kalikasan",
                "Galit ng Makiling");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 50;

        getStamina().setRegenRange(10, 25);
    }

    @Override
    public void basicAttack(Character target) {
        System.out.println(Colors.YELLOW + name + " uses " + skill1 + "!" + Colors.RESET);

        if (random.nextDouble() >= getAccuracy().getBasicAccuracy()) {
            System.out.println(Colors.RED + "The vines missed " + target.getName() + "!" + Colors.RESET);
            return;
        }

        System.out.println(Colors.CYAN + "Humahampas ng mahiwagang baging para magdulot ng damage sa kalaban." + Colors.RESET);
        int damage = attack + random.nextInt(40);
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        System.out.println(Colors.YELLOW + name + " uses " + skill2 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Tumatawag siya ng malalakas na hangin at gumagaling ang katawan sa kapangyarihan ng kalikasan." + Colors.RESET);

        // Support theme: Healing happens regardless of hitting the target
        int healAmount = attack + random.nextInt(50);
        hp = Math.min(maxHp, this.hp + healAmount);
        System.out.println(Colors.GREEN + name + " restored " + healAmount + " HP using Lunas ng Kalikasan." + Colors.RESET);

        if (random.nextDouble() >= getAccuracy().getSpecialAccuracy()) {
            System.out.println(Colors.RED + "But the attack missed " + target.getName() + "!" + Colors.RESET);
            return;
        }

        int damage = (int)(attack * 2) + random.nextInt(85);
        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target) {
        System.out.println(Colors.RED + name + " uses " + skill3 + "!" + Colors.RESET);

        if (random.nextDouble() >= getAccuracy().getUltimateAccuracy()) {
            System.out.println(Colors.RED + "The wrath of Makiling missed the target!" + Colors.RESET);
            return;
        }

        System.out.println(Colors.CYAN + "Inilalabas ang buong kapangyarihan ng kalikasan para sa napakalakas na area attack!" + Colors.RESET);
        int damage = (int)(attack * 3) + random.nextInt(125);
        target.takeDamage(damage);
    }
}