package net.add1s.ofm.config.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author pj.w@qq.com
 */
public class MyPasswordEncoder implements PasswordEncoder {

    /**
     * 加密前台传来的密码
     *
     * @param charSequence 前台传来的密码
     * @return String
     */
    @Override
    public String encode(CharSequence charSequence) {
        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 密码匹配
     *
     * @param charSequence 前台传来的密码
     * @param s 数据库里已经加密的密码
     * @return boolean
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(encode(charSequence));
    }
}
