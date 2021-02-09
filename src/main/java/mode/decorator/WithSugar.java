package mode.decorator;

public class WithSugar extends CoffeeDecorator {

    public WithSugar(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {

        return super.getCost()+1;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients()+", Sugar";
    }
}
