package characters.heroes;
import characters.Character;

public class MariaMakiling extends Character {
    public MariaMakiling() {
        super("Maria Makiling", "Hero", 3000, 150, 110,
                0.80,0.90,0.70,
                "Hampas ng Baging",
                "Lunas ng Kalikasan",
                "Galit ng Makiling");

        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 50;

        getStamina().setRegenRange(10, 25);
    }

    @Override
    public void basicAttack(Character target){
        if(getStamina().spend(basicAttackStaminaCost)){
            System.out.println(name + " uses " + skill1 + "!");

            if(random.nextDouble() >= getAccuracy().getBasicAccuracy()) {
                System.out.println("The vines missed " + target.getName() + "!");
                return;
            }

            int damage = attack + random.nextInt(40);
            System.out.println("Humahampas ng mahiwagang baging para magdulot ng damage sa kalaban.");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target){
        if(getStamina().spend(specialSkillStaminaCost)){
            System.out.println(name + " uses " + skill2 + "!");
            System.out.println("Tumatawag siya ng malalakas na hangin at gumagaling ang katawan sa kapangyarihan ng kalikasan.");

            // Heal happens regardless (support theme)
            int healAmount = attack + random.nextInt(50);
            hp = Math.min(maxHp, this.hp + healAmount);
            System.out.println(name + " restored " + healAmount + " HP using Lunas ng Kalikasan.");

            if(random.nextDouble() >= getAccuracy().getSpecialAccuracy()) {
                System.out.println("But the attack missed " + target.getName() + "!");
                return;
            }

            int damage = (int)(attack * 2 * 5.7) + random.nextInt(85);
            target.takeDamage(damage);
        }
    }

    @Override
    public void ultimateSkill(Character target){
        if(getStamina().spend(ultimateSkillStaminaCost)){
            System.out.println(name + " uses " + skill3 + "!");

            if(random.nextDouble() >= getAccuracy().getUltimateAccuracy()) {
                System.out.println("The wrath of Makiling missed the target!");
                return;
            }

            int damage = (int)(attack * 3 * 5.7) + random.nextInt(25);
            System.out.println("Inilalabas ang buong kapangyarihan ng kalikasan para sa napakalakas na area attack!");
            target.takeDamage(damage);
        }
    }
}