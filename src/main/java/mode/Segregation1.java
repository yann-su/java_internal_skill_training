package mode;



interface IpettyGirl {
    //要有较好的面孔
    public void goodLooking();
    //要有较好的身材
    public void niceFigure();
    //要有较好的气质
    public void greatTemperament();


}

class PettyGirl implements IpettyGirl{

    private String name;

    public PettyGirl(String name) {
        this.name = name;
    }

    @Override
    public void goodLooking() {
        System.out.println("------");
    }

    @Override
    public void niceFigure() {
        System.out.println("------");
    }

    @Override
    public void greatTemperament() {
        System.out.println("------");
    }
}


abstract class AbstractSearcher{

    protected IpettyGirl ipettyGirl;

    public AbstractSearcher(IpettyGirl ipettyGirl) {
        this.ipettyGirl = ipettyGirl;
    }
    //搜索美女， 列出美女信息
    public abstract void show();


}

class Searcher extends AbstractSearcher{

    public Searcher(IpettyGirl ipettyGirl) {
        super(ipettyGirl);
    }

    @Override
    public void show() {
        System.out.println("--------美女的信息如下： ---------------");
        //展示面容
        super.ipettyGirl.goodLooking();
        //展示身材
        super.ipettyGirl.niceFigure();
        //展示气质
        super.ipettyGirl.greatTemperament();
    }

    public static void main(String[] args) {
        PettyGirl pettyGirl = new PettyGirl("小美");
        Searcher searcher = new Searcher(pettyGirl);
        searcher.show();
    }
}
