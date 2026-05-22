package characters.heroes;

import characters.Character;
import util.Colors;

public class Kaptan extends Character {
    // kaptan specific mechanic
    private int stormCharge = 0;

    public Kaptan() {
        super("Kaptan", "Hero", 3600, 220, 100,
                0.95, 0.87, 0.82,
                "KiDLAT",
                "BAGYOH!",
                "Hampas-Langit!");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 60;

        getStamina().setRegenRange(20, 35);
    }

    @Override
    public void basicAttack(Character target) {

        System.out.println(Colors.CYAN + "Umaatake ang KIDLAT!" + Colors.RESET);

        int damage = attack + random.nextInt(75);
        stormCharge++;

        System.out.println(Colors.YELLOW + "Kaptan gathers static energy in the air! (Storm Charges: " + stormCharge + ")" + Colors.RESET);
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target) {
        System.out.println(Colors.CYAN + "Pinaulanan ka ng KIDLAT!" + Colors.RESET);
        System.out.println(Colors.YELLOW + name + " uses " + skill2 + "!" + Colors.RESET);

        int damage = attack + random.nextInt(75);
        stormCharge++;

        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target) {
        System.out.println(Colors.YELLOW + name + " uses " + skill3 + "!" + Colors.RESET);
        System.out.println(Colors.RED + "Isang nagngangalit na hampas mula sa kalangitan!" + Colors.RESET);


        int damage = attack + random.nextInt(90);


        stormCharge = 0;
        target.takeDamage(damage);
    }
}