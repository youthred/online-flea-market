package net.add1s.ofm.util;

/**
 * @author pj.w@qq.com
 */
public class HexUtil {

    private static final String HEX_PREFIX = "0x";
    private static final int HEX_RADIX = 16;

    public static String toHexStr(Number number) {
        return String.format("%04x", number);
    }

    public static String toHexStrUpper(Number number) {
        return String.format("%04X", number);
    }

    public static String toHexStrFull(Number number) {
        return String.format("%s%04x", HEX_PREFIX, number);
    }

    public static String toHexStrFullUpper(Number number) {
        return String.format("%s%04X", HEX_PREFIX, number);
    }

    public static short hexStr2Short(String hexStr) {
        return Short.parseShort(hexStr, HEX_RADIX);
    }

    public static short hexStrFull2Short(String hexStr) {
        return Short.parseShort(hexStr.replace(HEX_PREFIX, ""), HEX_RADIX);
    }
}
