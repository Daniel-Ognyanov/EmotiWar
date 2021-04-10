package emotiWar.web;

import emotiWar.model.entity.EmotiEntity;
import emotiWar.service.EmotiService;
import emotiWar.service.FightService;
import emotiWar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class FightController {

    private final EmotiService emotiService;
    private final UserService userService;
    private final FightService fightService;

    public FightController(EmotiService emotiService, UserService userService, FightService fightService) {
        this.emotiService = emotiService;
        this.userService = userService;
        this.fightService = fightService;
    }
    @ModelAttribute("haveFights")
    public boolean haveFights() {
        if (getCurrEmoti().getBattles() == 0) {
            return false;
        }
        return true;
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

    @GetMapping ("/fight")
    public String fight(){
        return "fight";
    }

    @GetMapping ("/attack")
        public String attack(){
        if (getCurrEmoti().getBattles() > 0) {
            fightService.attack();
        }
        return "redirect:/fight";
    }
}
