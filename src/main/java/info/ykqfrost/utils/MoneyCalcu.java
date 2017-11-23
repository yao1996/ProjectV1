package info.ykqfrost.utils;

import java.util.Date;

public class MoneyCalcu {
    public MoneyCalcu() {
    }

    public static int getMoney(Date borrowDate, Date returnDate) {
        long mils = (returnDate.getTime() - borrowDate.getTime()) / 1000L;
        int days = (int)mils / 3600 / 24;
        return days <= 30 ? 0 : days - 30;
    }
}
