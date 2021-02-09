package mode.abstractfactory;



public class Test {
    public static void main(String[] args) {

        IAnimalFactory blackAnimalFactory = new BlackAnimalFactory();
        ICat blackCat = blackAnimalFactory.createCat();
        blackCat.eat();
        IDog blackDog = blackAnimalFactory.createDog();
        blackDog.eat();

        IAnimalFactory whiteAnimalFactory = new WhiteAnimalFactory();
        ICat cat = whiteAnimalFactory.createCat();
        cat.eat();
        IDog dog = whiteAnimalFactory.createDog();
        dog.eat();
    }
}
