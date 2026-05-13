public class TestDummy extends Character{
    public TestDummy(){
        super("Attacker", 100, 5, 5, 100,
                "basic",
                "special skill",
                "ultimate"
        );

    }

    @Override
    public void useSkill1(Character target){
        int damage = attack + 5;
        System.out.println(name + " Uses basic attack");
        target.takeDamage(damage); // damage
    }

    @Override
    public void useSkill2(Character target){
        int damage = attack + 15;
        System.out.println(name + " Uses special skill");

        target.takeDamage(damage); // damage
    }

    @Override
    public void useSkill3(Character target){
        int damage = attack + 40;
        System.out.println(name + " Uses ultimate skill");

        target.takeDamage(damage); // damage
    }
}
