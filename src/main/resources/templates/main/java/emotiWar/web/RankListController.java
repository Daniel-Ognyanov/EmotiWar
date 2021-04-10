package emotiWar.web;

import emotiWar.model.entity.EmotiEntity;
import emotiWar.model.entity.UserEntity;
import emotiWar.service.EmotiService;
import emotiWar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class RankListController {

    private final EmotiService emotiService;
    private final UserService userService;

    public RankListController(EmotiService emotiService, UserService userService) {
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

    @GetMapping("/rankList")
    public String rankList() {
        return "rankList";
    }

    @ModelAttribute("getRankList")
    public List<UserEntity> getRankList() {
        return userService.getTopPlayers();
    }

}
