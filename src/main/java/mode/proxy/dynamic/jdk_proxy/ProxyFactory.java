package mode.proxy.dynamic.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 获取代理对象的工厂类
 */
public class ProxyFactory {

    private TrainStation station = new TrainStation();

    /**
     * 返回代理对象
     * @return
     */
    public SellTickets getProxyObject(){
        /**
         ClassLoader loader, 类加载器，用于加载代理类。可以通过目标对象获取类加载器
         Class<?>[] interfaces, 代理类实现的接口字节码对象
         InvocationHandler h   代理对象的调用处理器
         */
       SellTickets sellTickets =  (SellTickets)Proxy.newProxyInstance(
               station.getClass().getClassLoader(),
               station.getClass().getInterfaces(),
               new InvocationHandler() {
                   @Override
                   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                       System.out.println("代售收取一定的服务费用（jdk）动态代理");
                       Object obj = method.invoke(station, args);
                       return obj;
                   }
               }
       );
       return sellTickets;
    }

}
