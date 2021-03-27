package net.add1s.ofm.cache;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.captcha.ICaptcha;

import java.util.concurrent.TimeUnit;

public class TimedCacheManager {

    /**
     * 图片验证码超时缓存
     */
    public static TimedCache<String, ICaptcha> IMAGE_CAPTCHA;

    /**
     * 新用户验证超时缓存
     */
    public static TimedCache<String, Long> NEW_USER_VERIFY;

    static {
        IMAGE_CAPTCHA = CacheUtil.newTimedCache(TimeUnit.SECONDS.toMillis(30)); // 30s
        IMAGE_CAPTCHA.schedulePrune(TimeUnit.SECONDS.toMillis(1));    // 定时每一秒清理缓存中的过期值

        NEW_USER_VERIFY = CacheUtil.newTimedCache(TimeUnit.DAYS.toMillis(1)); // 24h
        NEW_USER_VERIFY.schedulePrune(TimeUnit.MINUTES.toMillis(30));
    }
}
