package net.add1s.ofm.config.netty.democlient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoClientController {

    private final DemoClient client;

    public DemoClientController(DemoClient client) {
        this.client = client;
    }

    @GetMapping("/send")
    public void send(@RequestParam("a") Integer a) throws InterruptedException {
        log.info(client.toString());
        client.sendAndGetResponse(new DemoMessage().setA(a));
    }
}
