package net.add1s.ofm.config.init;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import net.add1s.ofm.cache.SimpleCacheManager;
import net.add1s.ofm.config.netty.GoodsPrivateChatNettyServer;
import net.add1s.ofm.config.netty.GroupSexNettyServer;
import net.add1s.ofm.service.IChinaCityService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InitService implements ApplicationRunner {

    private final GroupSexNettyServer groupSexNettyServer;
    public final IChinaCityService iChinaCityService;
    public final GoodsPrivateChatNettyServer goodsPrivateChatNettyServer;

    public InitService(GroupSexNettyServer groupSexNettyServer,
                       IChinaCityService iChinaCityService,
                       GoodsPrivateChatNettyServer goodsPrivateChatNettyServer) {
        this.groupSexNettyServer = groupSexNettyServer;
        this.iChinaCityService = iChinaCityService;
        this.goodsPrivateChatNettyServer = goodsPrivateChatNettyServer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThreadUtil.execute(() -> {
            try {
                groupSexNettyServer.run();
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        });
        ThreadUtil.execute(() -> {
            try {
                goodsPrivateChatNettyServer.run();
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        });
        ThreadUtil.execute(() -> SimpleCacheManager.cityTreeDeep1 = iChinaCityService.generateCityTreeDeep1());
        ThreadUtil.execute(() -> SimpleCacheManager.cityTreeDeep2 = iChinaCityService.generateCityTreeDeep2());
        ThreadUtil.execute(() -> SimpleCacheManager.cityTreeDeep3 = iChinaCityService.generateCityTreeDeep3());
    }
}
