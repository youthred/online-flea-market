package net.add1s.ofm.controller;

import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.config.props.ChatProps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/netty")
public class NettyController {

    private final ChatProps chatProps;

    public NettyController(ChatProps chatProps) {
        this.chatProps = chatProps;
    }

    @GetMapping("/groupSex.html")
    public ModelAndView groupSexPage() {
        return new ModelAndView("chat/groupSex");
    }

    /**
     * 群聊HOST
     *
     * @return Res
     */
    @GetMapping("/groupSexNettyHost")
    public Res groupSexNettyHost() {
        return Res.ok(chatProps.getGroupSexServerHost());
    }

    /**
     * 私聊HOST
     *
     * @return Res
     */
    @GetMapping("/goodsPrivateNettyHost")
    public Res goodsPrivateNettyHost() {
        return Res.ok(chatProps.getGoodsPrivateChatServerHost());
    }
}
