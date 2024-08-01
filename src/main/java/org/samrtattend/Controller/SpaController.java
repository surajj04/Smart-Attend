package org.samrtattend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        // Forward to the index.html
        return "forward:/index.html";
    }
}
