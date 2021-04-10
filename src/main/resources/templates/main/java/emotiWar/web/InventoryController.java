package emotiWar.web;

import emotiWar.model.entity.EmotiEntity;
import emotiWar.service.UserService;
import emotiWar.service.impl.EmotiServiceImpl;
import emotiWar.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class InventoryController {

    private EmotiServiceImpl emotiService;
    private UserServiceImpl userService;

    public InventoryController(EmotiServiceImpl emotiService, UserServiceImpl userService) {
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
            return userService.getCurrentUser().getUsername();

        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/inventory")
    public String inventory() {
        return "inventory";
    }
}
