package characters.villains;

import characters.Character;
import util.Colors;

public class Aswang extends Character {

    public Aswang() {
        super("Aswang", "Villain", 3300, 265, 100,
                0.93, 0.84, 0.80,
                "Kagat ng Dilim",
                "Anino Lurker",
                "Sigaw ng Kadiliman");
        this.basicAttackStaminaCost = 0;
        this.specialSkillStaminaCost = 20;
        this.ultimateSkillStaminaCost = 45;
        getStamina().setRegenRange(10, 25);
    }

    @Override
    public void basicAttack(Character target){
        System.out.println(Colors.YELLOW + name + " uses " + skill1 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Sumuko ka sa gutom ng dilim!" + Colors.RESET);

        int damage = attack + random.nextInt(65);
        target.takeDamage(damage);
    }

    @Override
    public void specialSkill(Character target){
        System.out.println(Colors.YELLOW + name + " uses " + skill2 + "!" + Colors.RESET);
        System.out.println(Colors.CYAN + "Hindi mo ako makikita sa dilim! Struck from the shadows!" + Colors.RESET);

        int damage = (attack * 2) + random.nextInt(110);
        target.takeDamage(damage);
    }

    @Override
    public void ultimateSkill(Character target){
        System.out.println(Colors.RED + name + " uses " + skill3 + "!" + Colors.RESET);
        System.out.println(Colors.RED + "Lulunukin kayo ng kadiliman! A massive wave of darkness engulfs the battlefield!" + Colors.RESET);

        int damage = (attack * 3) + random.nextInt(150);
        target.takeDamage(damage);
    }
}