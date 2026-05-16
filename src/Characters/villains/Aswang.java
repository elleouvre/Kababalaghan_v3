package Characters.villains;
import Characters.Character;

public class Aswang extends Character {
    public Aswang() {
        super("Aswang", "Hero", 150, 15,100,
                "Kagat ng Dilim",
                "Anino Lurker",
                "Sigaw ng Kadiliman");
        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 45;
    }

    @Override
    public void basicAttack(Character target){
        if(stamina.spend(basicAttackStaminaCost)){
            int damage = attack + random.nextInt(5);

            System.out.println(name + " uses " + skill1 + "!");
            System.out.println("Sumuko ka sa gutom ng dilim!");

            target.takeDamage(damage);
            System.out.println(target.getName() + " took " + damage + " damage.");
        }
    }

    @Override
    public void specialSkill(Character target){
        if(stamina.spend(specialSkillStaminaCost)){
            int damage = attack * 2 + random.nextInt(10);

            System.out.println(name + " uses " + skill2 + "!");
            System.out.println("Hindi mo ako makikita sa dilim!");

            target.takeDamage(damage);
            System.out.println(target.getName() + " is struck from the shadows!");
        }
    }

    @Override
    public void ultimateSkill(Character target){
        if(stamina.spend(ultimateSkillStaminaCost)){
            int damage = attack * 3 + random.nextInt(20);

            System.out.println(name + " uses " + skill3 + "!");
            System.out.println("Lulunukin kayo ng kadiliman!");

            target.takeDamage(damage);
            System.out.println("A massive wave of darkness engulfs the battlefield!");
        }
    }
}
