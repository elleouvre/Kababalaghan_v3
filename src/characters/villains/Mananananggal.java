package characters.villains;

import characters.Character;
import util.Colors;

public class Mananananggal extends Character {
    private boolean isFlying = false;

    public Mananananggal() {
        super("Manananggal", "Villain", 3100, 260, 150,
                0.95, 1.0, 0.80,
                "Kalas ng Laman",
                "Hapdi ng Paglipad",
                "Pagtipon ng Hating Katawan!");

        this.basicAttackStaminaCost = 25;
        this.specialSkillStaminaCost = 0; // Stance switch is free
        this.ultimateSkillStaminaCost = 60;
        getStamina().setRegenRange(20, 35);
    }

    @Override
    public void basicAttack(Character target) {
        if (isFlying) {
            System.out.println("                                           " + Colors.CYAN + "Sumisirit ang katawan sa hangin... may gutom na bumabalot sa dilim." + Colors.RESET);
            System.out.println("                                           " + Colors.RED + "Ang hiwalay na laman ay sumusunod na parang aninong kumakagat! (Double Strike Mode)" + Colors.RESET);

            int mainDamage = attack + random.nextInt(60);
            int followUp = attack / 2 + random.nextInt(30);
            target.takeDamage(mainDamage);
            target.takeDamage(followUp);
        } else {
            System.out.println("                                           " + Colors.CYAN + "Ang mabigat na katawan ay humahagis sa lupa na parang sumpa." + Colors.RESET);
            int damage = attack + random.nextInt(75);
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {
        isFlying = !isFlying;
        if (isFlying) {
            System.out.println("                                           " + Colors.YELLOW + name + " uses " + skill1 + "!" + Colors.RESET);
            System.out.println("                                           " + Colors.RED + "Nagkakalas ang laman... Ang itaas na katawan ay lumulutang! Attack speed up, but vulnerabilities increase!" + Colors.RESET);
        } else {
            System.out.println("                                           " + Colors.YELLOW + name + " uses " + skill2 + "!" + Colors.RESET);
            System.out.println("                                           " + Colors.CYAN + "Bumabalik ang laman sa lupa... Ang katawang hinati ay muling nagiging isa." + Colors.RESET);
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        System.out.println("                                           " + Colors.RED + name + " uses " + skill3 + "!" + Colors.RESET);
        System.out.println("                                           " + Colors.CYAN + "Ang hiwalay na laman ay nagtatagpo sa isang pagputok ng dilim." + Colors.RESET);

        int damage = attack * 3 + random.nextInt(150);
        if (isFlying) {
            System.out.println("                                           " + Colors.RED + "Mula sa himpapawid, bumabagsak ang gutom na anino! Bonus Dive damage added!" + Colors.RESET);
            damage += attack;
        }
        target.takeDamage(damage);
    }
}