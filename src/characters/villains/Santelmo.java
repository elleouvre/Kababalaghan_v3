package characters.villains;

import characters.Character;

public class Santelmo extends Character{

    // this is Santelmo specific
    private int heatLevel = 0;
    public Santelmo(){
        super("Santelmo", "Villain", 150, 25, 100,  "SpARK!", "BlaZe", "FiREBALL!");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 15;
        this.ultimateSkillStaminaCost = 50;

    }

    //spark
    @Override
    public void basicAttack(Character target) {
        int damage = attack + random.nextInt(12);
        heatLevel++;
        System.out.println(name + " uses " + skill1 + "!");
        System.out.println("Nagbabaga! Santelmo's heat rises to " + heatLevel + "!");
        target.takeDamage(damage);
    }


    //SKILL blaze
    @Override
    public void specialSkill(Character target){
        if (getStamina().spend(specialSkillStaminaCost)){
            int bonusDamage = heatLevel * 5;
            int damage = attack * 2 + random.nextInt(18) + bonusDamage;
            System.out.println(name + " uses " + skill2 + "!");
            System.out.println("Damhin mo ang init! (Bonus Heat Damage: " + bonusDamage + ")");
            target.takeDamage(damage);
            heatLevel += 2;
        }
    }

    //SKILL fireball
    @Override
    public void ultimateSkill(Character target){
        if (getStamina().spend(ultimateSkillStaminaCost)) {
            int damage = (attack * 3) + (heatLevel * 10) + random.nextInt(25);
            System.out.println(name + " unleashes a massive " + skill3 + "!");
            System.out.println("The air burns as the heat level reached " + heatLevel + "!");
            target.takeDamage(damage);
            
            // reset heatLevel after ultimate
            heatLevel = 0;
            System.out.println("Santelmo's flames settle. Heat level reset.");
        }
    }
}

