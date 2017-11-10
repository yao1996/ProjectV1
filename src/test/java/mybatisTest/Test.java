package mybatisTest;

import info.ykqfrost.beans.Reader;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import info.ykqfrost.service.UserService;

/**
 * @author YaoKeQi
 * @date 2017/10/26
 */
@SpringBootTest(classes = info.ykqfrost.SpringBoot.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private UserService userService;

//    @org.junit.Test
//    public void testSelectAll() {
//        ArrayList<Reader> users = userService.selectAll();
//        System.out.println(users.size());
//        System.out.println(users.get(6).getUsername());
//    }
//    @org.junit.Test
//    public void testSelectById() {
//        Reader user = userService.selectOneById(7);
//        System.out.println(user);
//    }

    @org.junit.Test
    public void testRegister() {
        Reader reader = new Reader();
        reader.setUsername("reader");
        reader.setPassword("555555");
        try {
            boolean b = userService.register(reader);
            System.out.println("注册： " + b);
        }catch (Exception e){
            System.out.println("用户名已被注册");
        }
        reader.setUsername("aaaaaabb");
        try {
            boolean b= userService.register(reader);
            System.out.println("注册： " + b);
        }catch (Exception e){
            System.out.println("用户名已被注册");
        }
    }
}
