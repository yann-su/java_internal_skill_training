package mode.proxy.dynamic.jdk_proxy;


/**
 * 火车站类
 */
public class TrainStation implements SellTickets {


    @Override
    public void sell() {
        System.out.println("火车站卖票");
    }

    @Override
    public void notice() {
        System.out.println("截止20号之前票已经卖完了");
    }


}
