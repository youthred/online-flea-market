package net.add1s.ofm.util;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

/**
 * 时间间隔工具
 */
public class IntervalsUtil {

    /**
     * 返回 1秒种/2分钟/3小时/4天前/（超一月(31days)）2021年1月1日
     *
     * @param millis 间隔毫秒速
     * @return 间隔秒速
     */
    public static String simple(Long millis) {
        String intervalsDesc = null;
        final String SUFFIX = "前";
        if (lessThanOneMinute(millis)) {
            intervalsDesc = "刚刚";
        } else if (lessThanOneHour(millis)) {
            intervalsDesc = DateUtil.formatBetween(millis, BetweenFormatter.Level.MINUTE) + SUFFIX;
        } else if (lessThanOneDay(millis)) {
            intervalsDesc = DateUtil.formatBetween(millis, BetweenFormatter.Level.HOUR) + SUFFIX;
        } else if (lessThanOneMonth(millis)) {
            intervalsDesc = DateUtil.formatBetween(millis, BetweenFormatter.Level.DAY) + SUFFIX;
        }
        return intervalsDesc;
    }

    private static boolean lessThanOneMonth(Long millis) {
        return millis < DateUnit.DAY.getMillis() * 31;
    }

    private static boolean lessThanOneWeek(Long millis) {
        return millis < DateUnit.WEEK.getMillis();
    }

    private static boolean lessThanOneDay(Long millis) {
        return millis < DateUnit.DAY.getMillis();
    }

    private static boolean lessThanOneHour(Long millis) {
        return millis < DateUnit.HOUR.getMillis();
    }

    private static boolean lessThanOneMinute(Long millis) {
        return millis < DateUnit.MINUTE.getMillis();
    }

    private static boolean lessThanOneSecond(Long millis) {
        return millis < DateUnit.SECOND.getMillis();
    }
}
