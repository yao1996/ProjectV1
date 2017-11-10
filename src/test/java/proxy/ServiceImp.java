package proxy;

/**
 * @author YaoKeQi
 * @date 2017/11/10
 */
public class ServiceImp implements Service {
    @Override
    public void queryService() {
        System.out.println("query");
    }

    @Override
    public void insertService() {
        System.out.println("insert");
    }
}
