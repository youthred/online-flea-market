package net.add1s.ofm.util;

import net.add1s.ofm.common.enums.Symbol;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SqlUtil {

    public static final String[] LIKE_ESCAPE_ITEM = {
            Symbol.APOSTROPHE.getValue(),
            Symbol.UNDERSCORE.getValue(),
            Symbol.PERCENT.getValue(),
            Symbol.BACKSLASH.forSplit()
    };

    public static final String LIKE_PATTERN = StringUtils.join("[", StringUtils.join(LIKE_ESCAPE_ITEM), "]");

    public static String spaceToPresent(String source) {
        // " +": 匹配所有连续空格，replace为一个空格
        String[] searches = escapeLike(source.trim()).replaceAll(" +", " ").split(Symbol.SPACE.getValue());
        List<String> searchesTrimmed = Arrays.stream(searches).distinct().map(String::trim).collect(Collectors.toList());
        return StringUtils.join(searchesTrimmed, Symbol.PERCENT.getValue());
    }

    /**
     * 转义LIKE查询值
     *
     * @param source 源值
     * @return 转义后的值
     */
    public static String escapeLike(String source) {
        return source.matches(LIKE_PATTERN)
                ? escapeSql(source)
                : source;
    }

    /**
     * SQL转义
     *
     * @param source 源SQL语句
     * @return 转义后的SQL
     */
    private static String escapeSql(String source) {
        for (String s : LIKE_ESCAPE_ITEM) {
            source = source.replace(s, Symbol.BACKSLASH.getValue() + s);
        }
        return source;
    }
}
