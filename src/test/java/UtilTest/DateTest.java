package UtilTest;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YaoKeQi
 * @date 2017/10/28
 */
public class DateTest {
    @Test
    public void testDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        String s = sdf.format(calendar.getTime());
        System.out.println(s);
    }

    public void testUtils() {
        Hashtable hashtable = new Hashtable();
        Enumeration enumeration = hashtable.elements();
    }

}
