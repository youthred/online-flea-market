package net.add1s.ofm.config.init;

import lombok.extern.slf4j.Slf4j;
import net.add1s.ofm.cache.SimpleCacheManager;
import net.add1s.ofm.config.chat.ChatNettyServer;
import net.add1s.ofm.service.IChinaCityLevel4Service;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InitService implements ApplicationRunner {

    private final ChatNettyServer chatNettyServer;
    public final IChinaCityLevel4Service iChinaCityLevel4Service;

    public InitService(ChatNettyServer chatNettyServer,
                       IChinaCityLevel4Service iChinaCityLevel4Service) {
        this.chatNettyServer = chatNettyServer;
        this.iChinaCityLevel4Service = iChinaCityLevel4Service;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("系统初始化中...");
        new Thread(() -> {
            try {
                chatNettyServer.start();
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }).start();
        SimpleCacheManager.cityTree = iChinaCityLevel4Service.generateCityTree();
        log.info("系统初始化完成。");
    }
}
