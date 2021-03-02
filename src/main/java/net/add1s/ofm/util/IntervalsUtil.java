package net.add1s.ofm.util;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

/**
 * 时间间隔工具
 */
public class IntervalsUtil {

    /**
     * 返回 1秒种/2分钟/3小时/4天前/（超一周）2021年1月1日
     *
     * @param millis 间隔毫秒速
     * @return 间隔秒速
     */
    public static String simple(Long millis) {
        if (moreThenOneWeek(millis)) {
            return null;
        } else if (moreThenOneDay(millis)) {
            return DateUtil.formatBetween(millis, BetweenFormatter.Level.DAY);
        } else if (moreThenOneHour(millis)) {
            return DateUtil.formatBetween(millis, BetweenFormatter.Level.HOUR);
        } else if (moreThenOneMinute(millis)) {
            return DateUtil.formatBetween(millis, BetweenFormatter.Level.MINUTE);
        } else if (moreThenOneSecond(millis)) {
            return DateUtil.formatBetween(millis, BetweenFormatter.Level.SECOND);
        }
        return "即时";
    }

    private static boolean moreThenOneWeek(Long millis) {
        return millis > DateUnit.WEEK.getMillis();
    }

    private static boolean moreThenOneDay(Long millis) {
        return millis > DateUnit.DAY.getMillis();
    }

    private static boolean moreThenOneHour(Long millis) {
        return millis > DateUnit.HOUR.getMillis();
    }

    private static boolean moreThenOneMinute(Long millis) {
        return millis > DateUnit.MINUTE.getMillis();
    }

    private static boolean moreThenOneSecond(Long millis) {
        return millis > DateUnit.SECOND.getMillis();
    }
}
