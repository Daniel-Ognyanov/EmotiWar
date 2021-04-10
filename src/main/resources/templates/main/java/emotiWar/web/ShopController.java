package emotiWar.web;

import emotiWar.model.entity.EmotiEntity;
import emotiWar.service.EmotiService;
import emotiWar.service.ItemService;
import emotiWar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final UserService userService;
    private final EmotiService emotiService;

    public ShopController(UserService userService, EmotiService emotiService) {
        this.userService = userService;
        this.emotiService = emotiService;
    }

    @GetMapping("/all")
    public String getShop() {
        return "shop";
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
            return userService.getCurrentUser().getUsername();

        } catch (Exception e) {
            return null;
        }
    }
}
