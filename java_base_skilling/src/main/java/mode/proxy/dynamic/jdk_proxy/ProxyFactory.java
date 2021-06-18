package mode.proxy.dynamic.jdk_proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * 获取代理对象的工厂类
 */
@Slf4j
public class ProxyFactory {



    private final SellTickets station = new TrainStation();

    /**
     * 返回代理对象
     *
     * @return
     */
    public SellTickets getProxyObject() {
        /**
         ClassLoader loader, 类加载器，用于加载代理类。可以通过目标对象获取类加载器
         Class<?>[] interfaces, 代理类实现的接口字节码对象
         InvocationHandler h   代理对象的调用处理器
         */
        return  (SellTickets) Proxy.newProxyInstance(
                station.getClass().getClassLoader(),
                station.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    log.info("进入动态代理方法");
                    return method.invoke(station, args);
                });
    }


}
