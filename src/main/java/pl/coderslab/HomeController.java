package pl.coderslab;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String hello(@AuthenticationPrincipal UserDetails customUser) {
        System.out.println(customUser.getUsername());
        User byUsername = userRepository.findByUsername(customUser.getUsername());
        Duck duck = new Duck();
        duck.setName("Sknerus");
        duck.setUser(byUsername);
        return "home";
    }

    @GetMapping("/about")
    @ResponseBody
    public String about(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.getAttribute("userId");
        return "Here you can find some details for logged users";
    }
}
