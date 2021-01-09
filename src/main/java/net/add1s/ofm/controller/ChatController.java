package net.add1s.ofm.controller;

import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.config.props.ChatProps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatProps chatProps;

    public ChatController(ChatProps chatProps) {
        this.chatProps = chatProps;
    }

    @GetMapping("/groupSex.html")
    public String groupSexPage() {
        return "chat/groupSex";
    }

    @ResponseBody
    @GetMapping("/nettyHost")
    public Res nettyHost() {
        return Res.ok(chatProps.getServerHost());
    }
}
