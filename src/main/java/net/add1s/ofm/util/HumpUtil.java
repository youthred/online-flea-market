package net.add1s.ofm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下划线驼峰互转
 *
 * @author pj.w@qq.com
 */
public class HumpUtil {

    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     *
     * @param str 下划线字符串
     * @return 驼峰字符串
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str 驼峰字符串
     * @return 下划线字符串
     */
    public static String humpToLine(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer().append("`");
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.append("`").toString();
    }
}
