package mode.proxy.staticstate;

import mode.proxy.dynamic.jdk_proxy.SellTickets;
import mode.proxy.dynamic.jdk_proxy.TrainStation;

/**
 * 代售点
 */

public class ProxyPoint implements SellTickets {



    //声明火车站对象

    private TrainStation station = new TrainStation();

    @Override
    public void sell() {
        System.out.println("收取一定的手续费");
        station.sell();
    }
}
