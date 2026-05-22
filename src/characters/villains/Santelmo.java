package characters.villains;

import characters.Character;
import util.Colors;

public class Santelmo extends Character {
    private int heatLevel = 0;

    public Santelmo(){
        super("Santelmo", "Villain", 3200, 230, 100,
                0.95, 0.89, 0.80,
                "SpARK!", "BlaZe", "FiREBALL!");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 15;
        this.ultimateSkillStaminaCost = 50;
        getStamina().setRegenRange(20, 35);
    }

    @Override
    public void basicAttack(Character target) {
        System.out.println(Colors.YELLOW + name + " uses " + skill1 + "!" + Colors.RESET);
        heatLevel++;
        System.out.println(Colors.RED + "Nagbabaga! Santelmo's heat rises to Level " + heatLevel + "!" + Colors.RESET);

        int damage = attack + random.nextInt(65);
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target){
        System.out.println(Colors.YELLOW + name + " uses " + skill2 + "!" + Colors.RESET);
        int bonusDamage = heatLevel * 45; // Balanced scaling multipliers
        System.out.println(Colors.RED + "Damhin mo ang init! (Bonus Heat Damage: +" + bonusDamage + ")" + Colors.RESET);

        int damage = (attack * 2) + random.nextInt(90) + bonusDamage;
        heatLevel += 2;
        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target){
        System.out.println(Colors.RED + name + " unleashes a massive " + skill3 + "!" + Colors.RESET);
        int bonusMultiplier = heatLevel * 90;
        System.out.println(Colors.RED + "The air burns completely! Heat Charge Level reached: " + heatLevel + " (+" + bonusMultiplier + " Damage)" + Colors.RESET);

        int damage = (attack * 3) + bonusMultiplier + random.nextInt(140);
        target.takeDamage(damage);

        heatLevel = 0; // Consumption mechanic reset
        System.out.println(Colors.CYAN + "Santelmo's flames settle. Heat level reset to 0." + Colors.RESET);
    }
}