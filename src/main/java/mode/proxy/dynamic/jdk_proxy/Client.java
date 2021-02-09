package mode.proxy.dynamic.jdk_proxy;

public class Client {

    public static void main(String[] args) {

        //获取代理对象
        //创建代理工厂对象
        ProxyFactory proxyFactory = new ProxyFactory();
        SellTickets proxyObject = proxyFactory.getProxyObject();
        //2、使用factory对象的方法获取代理对象
        proxyObject.sell();



    }
}
