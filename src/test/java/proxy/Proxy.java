package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author YaoKeQi
 * @date 2017/11/10
 */
public class Proxy implements InvocationHandler {
    private Object target;

    public Object getInstance(Object object) {
        this.target = object;
        return java.lang.reflect.Proxy.newProxyInstance(object.getClass().getClassLoader()
                ,object.getClass().getInterfaces()
                ,this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        System.out.println("before");
        result = method.invoke(target,args);
        System.out.println("after");
        return result;
    }

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        Service service = (Service) proxy.getInstance(new ServiceImp());
        service.queryService();
    }
}
