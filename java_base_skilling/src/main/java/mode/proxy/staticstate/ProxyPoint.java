package mode.proxy.staticstate;



/**
 * 代售点
 */

public class ProxyPoint implements SellTickets {


    private TrainStation station = new TrainStation();

    @Override
    public void sell() {
        System.out.println("收取一定的手续费");
        station.sell();
    }
}
