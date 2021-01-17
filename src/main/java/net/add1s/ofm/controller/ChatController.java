package net.add1s.ofm.controller;

import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.config.props.ChatProps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatProps chatProps;

    public ChatController(ChatProps chatProps) {
        this.chatProps = chatProps;
    }

    @GetMapping("/groupSex.html")
    public ModelAndView groupSexPage() {
        return new ModelAndView("chat/groupSex");
    }

    @GetMapping("/nettyHost")
    public Res nettyHost() {
        return Res.ok(chatProps.getServerHost());
    }
}
