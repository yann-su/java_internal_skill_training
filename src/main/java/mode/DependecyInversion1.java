package mode;

public class DependecyInversion1 {

    public static void main(String[] args) {
        OpenAndClose openAndClose = new OpenAndClose();
        openAndClose.open(new ChangHong());
    }
}



interface IOpenAndClose{
    public void open(ITv iTv);
}

interface ITv{
    public void play();
}

class OpenAndClose implements IOpenAndClose{

    @Override
    public void open(ITv iTv) {
        iTv.play();
    }
}

class ChangHong implements ITv{

    @Override
    public void play() {
        System.out.println("打开电视机，长虹");
    }
}