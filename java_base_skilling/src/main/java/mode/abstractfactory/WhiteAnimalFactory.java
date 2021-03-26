package mode.abstractfactory;

public class WhiteAnimalFactory implements IAnimalFactory {
    @Override
    public ICat createCat() {
        return new WhiteCat();
    }

    @Override
    public IDog createDog() {
        return new WhiteDog();
    }

    @Override
    public Pig createPig() {
        return new WhitePig();
    }
}
