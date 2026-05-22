package characters.heroes;

import characters.Character;
import util.Colors;

public class Mayari extends Character {

    public Mayari() {
        super("Mayari", "Hero", 3550, 255, 100,
                0.92, 0.87, 0.82,
                "Tumatagos ang Liwanag ng Buwan",
                "Saplot ng One-Eyed Queen",
                "Dekreto ng Celestial Divide");

        this.basicAttackStaminaCost = 5;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 45;
        getStamina().setRegenRange(20, 35);
    }

    @Override
    public void basicAttack(Character target) {
        System.out.println(Colors.CYAN + "Itinutok ni Mayari ang kanyang sibat! 'Hindi ka itinatago ng mga anino.'" + Colors.RESET);
        System.out.println(Colors.YELLOW + name + " uses " + skill1 + "!" + Colors.RESET);

        int damage = attack + random.nextInt(75);
        if (random.nextBoolean()) {
            System.out.println(Colors.RED + "Kritikal na Hit! Ang Full Moon ay nagbibigay kapangyarihan sa strike! (+35 DMG)" + Colors.RESET);
            damage += 35;
        }
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        System.out.println(Colors.YELLOW + name + " uses " + skill2 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Ang talim ng gasuklay ni Mayari ay tumatawid sa larangan ng digmaan!" + Colors.RESET);

        int damage = attack * 2 + random.nextInt(75);
        target.takeDamage(damage);

        int healAmount = 150;
        this.hp = Math.min(maxHp, this.hp + healAmount);
        System.out.println(Colors.GREEN + "Nagbabalik ang liwanag ng buwan ng " + healAmount + " HP kay " + name + "!" + Colors.RESET);
    }

    @Override
    public void ultimateSkill(Character target) {
        System.out.println(Colors.YELLOW + name + " uses " + skill3 + "!" + Colors.RESET);
        System.out.println(Colors.RED + "Nahati ang langit! 'Ako ang tunay na panginoon ng langit!'" + Colors.RESET);

        int damage = attack * 3 + random.nextInt(125);
        target.takeDamage(damage);

        int healAmount = 300;
        this.hp = Math.min(maxHp, this.hp + healAmount);
        System.out.println(Colors.GREEN + "Nag-restore ang isang Silver Shield ng " + healAmount + " HP kay " + name + "!" + Colors.RESET);
    }
}