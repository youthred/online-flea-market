package net.add1s.ofm.cache;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.captcha.ICaptcha;

public class TimedCacheManager {

    /**
     * 图片验证码超时缓存
     */
    public static TimedCache<String, ICaptcha> IMAGE_CAPTCHA;

    static {
        IMAGE_CAPTCHA = CacheUtil.newTimedCache(30 * 1000); // 30s
        IMAGE_CAPTCHA.schedulePrune(1000);    // 定时每一秒清理缓存中的过期值
    }
}
