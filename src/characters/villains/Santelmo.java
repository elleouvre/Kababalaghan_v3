package characters.villains;

import characters.Character;

public class Santelmo extends Character{

    // this is Santelmo specific
    private int heatLevel = 0;
    public Santelmo(){
        super("Santelmo", "Villain", 3000, 320, 100,
                0.95,0.89,0.80,
                "SpARK!",
                "BlaZe",
                "FiREBALL!");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 15;
        this.ultimateSkillStaminaCost = 50;

        getStamina().setRegenRange(20, 35);
    }

    //spark
    @Override
    public void basicAttack(Character target) {
        int damage = attack + random.nextInt(65);
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
            int damage = attack * 2 + random.nextInt(90) + bonusDamage;
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
            int damage = (attack * 3) + (heatLevel * 10) + random.nextInt((int) (140));
            System.out.println(name + " unleashes a massive " + skill3 + "!");
            System.out.println("The air burns as the heat level reached " + heatLevel + "!");
            target.takeDamage(damage);
            
            // reset heatLevel after ultimate
            heatLevel = 0;
            System.out.println("Santelmo's flames settle. Heat level reset.");
        }
    }
}
