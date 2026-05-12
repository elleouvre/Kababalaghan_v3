public class TestDummy extends Character{
    public TestDummy(){
        super("Attacker", 100, 5, 5, 100,
                "basic",
                "special skill",
                "ultimate"
        );

    }
    public void useSkill1(Character target){
        int damage = attack + 5;
        System.out.println(name + "Uses basic attack");
    }
    public void useSkill2(Character target){
        int damage = attack + 15;
        System.out.println(name + "Uses special skill");
    }
    public void useSkill3(Character target){
        int damage = attack + 40;
        System.out.println(name + "Uses ultimate skill");
    }
}
