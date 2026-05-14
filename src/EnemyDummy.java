import Characters.Character;

public class EnemyDummy extends Character {
    public EnemyDummy(){
        super("Punching bag", "Villian",150, 5, 100,
                "basic",
                "special skill",
                "ultimate"
        );
    }
    public void basicAttack(Characters.Character target){
        int damage = attack + 5;
        System.out.println(name + " Uses basic attack");
        target.takeDamage(damage);
        System.out.println(target.getName()+" took '"+ damage+ "' dmg!");
    }
    public void specialSkill(Characters.Character target){
        int damage = attack + 15;
        System.out.println(name + " Uses special skill");
        target.takeDamage(damage);
        System.out.println(target.getName()+" took '"+ damage+ "' dmg!");
    }
    public void ultimateSkill(Character target){
        int damage = attack + 40;
        System.out.println(name + " Uses ultimate skill");
        target.takeDamage(damage);
        System.out.println(target.getName()+" took '"+ damage+ "' dmg!");
    }
}
