package characters.heroes;
import characters.Character;

public class MariaMakiling extends Character {
    public MariaMakiling() {
        super("Maria Makiling", "Hero", 150, 15, 110,
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
            int damage = attack + random.nextInt(8);

            System.out.println(name+" uses " + skill1 + "!");
            System.out.println("Humahampas ng mahiwagang baging para magdulot ng damage sa kalaban.");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");
        }
    }

    @Override
    public void specialSkill(Character target){
        if(getStamina().spend(specialSkillStaminaCost)){
            int damage = attack * 2 + random.nextInt(15);
            int healAmount = attack + random.nextInt(10);

            System.out.println(name + " uses " + skill2 + "!");
            System.out.println("Tumatawag siya ng malalakas na hangin at gumagaling ang katawan sa kapangyarihan ng kalikasan.");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");

            int heal = 10;
            hp = Math.min(maxHp, this.hp + heal);
            System.out.println(name + " restored " + heal + " HP using Lunas ng Kalikasan.");
        }
    }

    @Override
    public void ultimateSkill(Character target){
        if(getStamina().spend(ultimateSkillStaminaCost)){
            int damage = attack * 3  + random.nextInt(25);
            System.out.println(name + " uses " + skill3 + "!");
            System.out.println("Inilalabas ang buong kapangyarihan ng kalikasan para sa napakalakas na area attack!");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");
        }
    }
}
