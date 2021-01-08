package net.add1s.ofm.config.init;

import lombok.extern.slf4j.Slf4j;
import net.add1s.ofm.config.chat.ChatNettyServer;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InitService implements ApplicationRunner {

    private final ChatNettyServer chatNettyServer;

    public InitService(ChatNettyServer chatNettyServer) {
        this.chatNettyServer = chatNettyServer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(() -> {
            try {
                chatNettyServer.start();
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }).start();
    }
}
