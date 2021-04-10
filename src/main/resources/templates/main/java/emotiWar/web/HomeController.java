package emotiWar.web;

import emotiWar.model.entity.EmotiEntity;
import emotiWar.service.EmotiService;
import emotiWar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Locale;

@Controller
public class HomeController {


    private final EmotiService emotiService;
    private final UserService userService;


    public HomeController(EmotiService emotiService, UserService userService) {
        this.emotiService = emotiService;
        this.userService = userService;
    }

    @ModelAttribute("currEmoti")
    public EmotiEntity getCurrEmoti() {
        try {
            return emotiService.getEmoti();
        } catch (Exception e) {
            return null;
        }
    }


    @ModelAttribute("username")
    public String getUsername() {
        try {
            String username = userService.getCurrentUser().getUsername();
            String first = username.charAt(0) + "";
            first = first.toUpperCase(Locale.ROOT);
            username = first + username.substring(1);
            return username;


        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/collectCoins")
        public String collectCoins(){
        emotiService.collectCoins();
        return "redirect:/home";
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }



//    public String getCurrentUsername() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            return ((UserDetails) principal).getUsername();
//        } else {
//            return principal.toString();
//        }
//    }

}
