package mode.abstractfactory;

public class BlackAnimalFactory implements IAnimalFactory{
    @Override
    public ICat createCat() {
        return new BlackCat();
    }

    @Override
    public IDog createDog() {
        return new BlackDog();
    }

    @Override
    public Pig createPig() {
        return new BlackPig();
    }
}
