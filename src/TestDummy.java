import characters.Character;

public class TestDummy extends Character {
    public TestDummy(){
        super("Attacker", "Hero",100, 5, 100,
                "basic",
                "special skill",
                "ultimate"
        );

    }

    @Override
    public void basicAttack(characters.Character target){
        int damage = attack + 5;
        System.out.println(name + " Uses basic attack");
        target.takeDamage(damage); // damage
        System.out.println(target.getName()+" took '"+ damage+ "' dmg!");
    }

    @Override
    public void specialSkill(characters.Character target){
        int damage = attack + 15;
        System.out.println(name + " Uses special skill");

        target.takeDamage(damage); // damage
        System.out.println(target.getName()+" took '"+ damage+ "' dmg!");
    }

    @Override
    public void ultimateSkill(Character target){
        int damage = attack + 40;
        System.out.println(name + " Uses ultimate skill");

        target.takeDamage(damage); // damage
        System.out.println(target.getName()+" took '"+ damage+ "' dmg!");
    }
}
