//package net.add1s.ofm.config.netty.democlient;
//
//import cn.hutool.core.thread.ThreadUtil;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Service;
//
//@Service
//public class Init implements ApplicationRunner {
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        ThreadUtil.execute(() -> {
//            try {
//                new DemoServer().run();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//}
