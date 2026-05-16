package characters.villains;

import characters.Character;

public class Mananananggal extends Character {

    public Mananananggal() {
        super("Manananggal", "Villain", 100, 20, 150,
                "Kalas ng Laman",
                "Hapdi ng Paglipad",
                "Pagtipon ng Hating Katawan!");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 15;
        this.ultimateSkillStaminaCost = 50;

    }

    // stance(if flying do more dmg,take more dmg)
    boolean isFlying = false;
    int flyVulnerability = 0;

    @Override
    public void basicAttack(Character target) {

        int damage = attack + random.nextInt(10);

        int flyingCost = isFlying ? 20 : 0;
        if (flyingCost > 0 && !this.stamina.spend(flyingCost)) {
            System.out.println(name + " is too exhausted to maintain flight attack!");
            return;
        }

        if (isFlying) {
            int followUp = attack / 2;

            damage += 8;
            damage += flyVulnerability;

            System.out.println("Sumisirit ang katawan sa hangin... may gutom na bumabalot sa dilim.");
            System.out.println("Ang hiwalay na laman ay sumusunod na parang aninong kumakagat.");

            target.takeDamage(damage);
            target.takeDamage(followUp);

        } else {

            System.out.println("🧍 Ang mabigat na katawan ay humahagis sa lupa na parang sumpa.");

            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target) {

        if (stamina.spend(specialSkillStaminaCost)) {
            isFlying = !isFlying;

            if (isFlying) {
                flyVulnerability = 20;

                System.out.println(name + " uses " + skill1 + "!");
                System.out.println("Nagkakalas ang laman... ang dilim ay bumubukas.");
                System.out.println("Ang itaas na katawan ay lumulutang, naghahanap ng biktima...");
                System.out.println("Ang katawan ay nagiging marupok sa himpapawid!");
            } else {
                flyVulnerability = 0;

                System.out.println(name + " uses " + skill2 + "!");
                System.out.println("Bumabalik ang laman sa lupa...");
                System.out.println("Ang katawang hinati ay muling nagiging isa... ngunit hindi ganap.");
            }
        }
    }

    @Override
    public void ultimateSkill(Character target) {
        if (stamina.spend(ultimateSkillStaminaCost)) {
            int damage = attack * 3 + random.nextInt(35);

            System.out.println(name + " uses " + skill3 + "!");
            System.out.println("Ang hiwalay na laman ay nagtatagpo sa isang pagputok ng dilim.");

            if (isFlying) {
                System.out.println("Mula sa himpapawid, bumabagsak ang gutom na anino.");
                damage += attack;
            } else {
                System.out.println("Mula sa lupa, sumisigaw ang katawan na hindi dapat nabubuo.");
            }

            target.takeDamage(damage);
        }
    }
}