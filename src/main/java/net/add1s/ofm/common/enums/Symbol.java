package net.add1s.ofm.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author pj.w@qq.com
 */
@Getter
@AllArgsConstructor
public enum Symbol {

    EXCLAMATION("!"),
    DOUBLE_QUOTE("\""),
    NUMBER_SIGN("#"),
    DOLLAR("$"),
    PERCENT("%"),
    AMPERSAND("&"),
    APOSTROPHE("'"),
    PARENTHESIS_LEFT("("),
    PARENTHESIS_RIGHT(")"),
    ASTERISK("*"),
    PLUS("+"),
    COMMA(","),
    MINUS("-"),
    DOT("."),
    SLASH("/"),
    colon(":"),
    SEMICOLON(";"),
    LESS_THAN("<"),
    EQUALS("="),
    GREATER_THAN(">"),
    QUESTION("?"),
    AT("@"),
    BRACKETS_LEFT("["),
    BRACKETS_RIGHT("]"),
    BACKSLASH("\\"),
    CARET("^"),
    UNDERSCORE("_"),
    GRAVE_ACCENT("`"),
    CURLY_BRACES_LEFT("{"),
    CURLY_BRACES_RIGHT("}"),
    VERTICAL_BAR("|"),
    TILDE("~"),
    SPACE(" "),
    EMPTY_STRING(""),
    ;

    private final String value;

    public String forSplit() {
        return Symbol.BACKSLASH.value + this.value;
    }
}
