package mode.decorator;

public class Test {
    public static void main(String[] args) {
        //原味咖啡
        Coffee coffee = new SimpleCoffee();
        //增加牛奶的咖啡
        coffee = new WithMilk(coffee);
        //再加一点糖
        coffee = new WithSugar(coffee);
        print(coffee);
    }

    static void print(Coffee c) {
        System.out.println("花费了: " + c.getCost());
        System.out.println("配料: " + c.getIngredients());
        System.out.println("============");
    }

}
