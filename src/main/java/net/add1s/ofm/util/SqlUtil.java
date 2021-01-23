package net.add1s.ofm.util;

import net.add1s.ofm.common.enums.Symbol;
import org.apache.commons.lang3.StringUtils;

public class SqlUtil {

    public static final String[] LIKE_ESCAPE_ITEM = {
            Symbol.APOSTROPHE.getValue(),
            Symbol.UNDERSCORE.getValue(),
            Symbol.PERCENT.getValue(),
            Symbol.BACKSLASH.forSplit()
    };
    public static final String LIKE_PATTERN = StringUtils.join("[", StringUtils.join(LIKE_ESCAPE_ITEM), "]");

    public static String escapeLike(String source) {
        return source.matches(LIKE_PATTERN)
                ? escapeSql(source)
                : source;
    }

    private static String escapeSql(String source) {
        for (String s : LIKE_ESCAPE_ITEM) {
            source = source.replace(s, Symbol.BACKSLASH.getValue() + s);
        }
        return source;
    }
}
