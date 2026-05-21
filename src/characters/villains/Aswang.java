package characters.villains;
import characters.Character;

public class Aswang extends Character {

    public Aswang() {
        super("Aswang", "Villain", 3000, 150, 100,
                0.85,0.80,0.75,
                "Kagat ng Dilim",
                "Anino Lurker",
                "Sigaw ng Kadiliman");
        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 45;
        getStamina().setRegenRange(10, 20);
    }

    @Override
    public void basicAttack(Character target){
        if(getStamina().spend(basicAttackStaminaCost)){

            System.out.println(name + " uses " + skill1 + "!");
            if(random.nextDouble() >= getAccuracy().getBasicAccuracy()) {
                System.out.println(name + "'s attack missed in the darkness!");
                return;
            }

            int damage = attack + random.nextInt(40);
            System.out.println("Sumuko ka sa gutom ng dilim!");
            target.takeDamage(damage);
        }
    }

    @Override
    public void specialSkill(Character target){
        if(getStamina().spend(specialSkillStaminaCost)){
            System.out.println(name + " uses " + skill2 + "!");

            if(random.nextDouble() > getAccuracy().getSpecialAccuracy()){

                System.out.println("Hindi mo ako makikita sa dilim!");
            }

            int damage = (int)(attack * 2 * 5.7) + random.nextInt(85);
            System.out.println("Hindi mo ako makikita sa dilim!");
            target.takeDamage(damage);
            System.out.println(target.getName() + " is struck from the shadows!");
        }
    }

    @Override
    public void ultimateSkill(Character target){
        if(getStamina().spend(ultimateSkillStaminaCost)){
            System.out.println(name + " uses " + skill3 + "!");

            if(random.nextDouble() >= getAccuracy().getUltimateAccuracy()) {
                System.out.println("The darkness consumed the attack! It missed!");
                return;
            }

            int damage = (int)(attack * 3 * 5.7) + random.nextInt(25);
            System.out.println("Lulunukin kayo ng kadiliman!");
            target.takeDamage(damage);
            System.out.println("A massive wave of darkness engulfs the battlefield!");
        }
    }
}