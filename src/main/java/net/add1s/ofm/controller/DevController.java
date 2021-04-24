package net.add1s.ofm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dev")
public class DevController {

    @GetMapping("/log.html")
    public ModelAndView logPage() {
        return new ModelAndView("common/devLog");
    }
}
