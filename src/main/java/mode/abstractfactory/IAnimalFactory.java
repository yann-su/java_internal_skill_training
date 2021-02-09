package mode.abstractfactory;


/**
 * java动物工厂接口类
 */

public interface IAnimalFactory {

    /**
     * 创建定义ICat接口方法
     * @return
     */
    ICat createCat();

    /**
     *  创建定义IDog接口方法
     * @return
     */
    IDog createDog();

    /**
     *
     * @return
     */
    Pig createPig();

}