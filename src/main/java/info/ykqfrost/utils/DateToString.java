package info.ykqfrost.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yaoke
 * @date 2017/11/25
 */
public class DateToString {
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
