package pl.gjahn.miniapp.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.gjahn.miniapp.model.service.SessionService;

@Controller
public class DashboardController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/user/dashboard")
    public String dashboard() {

        if (sessionService.isLogin()) {
            return "user/dashboard";
        }
        return "redirect:/user/login";
    }


}


