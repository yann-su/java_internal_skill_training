package mode.proxy.staticstate;

import mode.proxy.dynamic.jdk_proxy.SellTickets;

public class Client {

    public static void main(String[] args) {

        //创建对象
        SellTickets proxyPoint = new ProxyPoint();
        proxyPoint.sell();



    }

}
