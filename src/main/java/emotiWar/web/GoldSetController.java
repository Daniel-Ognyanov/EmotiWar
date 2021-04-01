package emotiWar.web;

import emotiWar.model.entity.EmotiEntity;
import emotiWar.service.EmotiService;
import emotiWar.service.ItemService;
import emotiWar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GoldSetController {

    private final UserService userService;
    private final EmotiService emotiService;
    private final ItemService itemService;

    public GoldSetController(UserService userService, EmotiService emotiService, ItemService itemService) {
        this.userService = userService;
        this.emotiService = emotiService;
        this.itemService = itemService;
    }

    @GetMapping("/shop/goldSet")
    public String getCopperSet() {
        return "goldSet";
    }


    @ModelAttribute("noCoinsError")
    public boolean noCoinsError() {
        return false;
    }

    @ModelAttribute("successBuy")
    public boolean successBuy() {
        return false;
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

    @GetMapping("/shop/goldSet/buy/weapon/{id}")
    public String buyWeaponByID(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        if (itemService.buyWeaponByID(id)) {
            redirectAttributes.addFlashAttribute("successBuy", true);
        } else {
            redirectAttributes.addFlashAttribute("noCoinsError", true);
        }
        return "redirect:/shop/goldSet";
    }


    @GetMapping("/shop/goldSet/buy/shield/{id}")
    public String buyShieldByID(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        if (itemService.buyShieldByID(id)) {
            redirectAttributes.addFlashAttribute("successBuy", true);
        } else {
            redirectAttributes.addFlashAttribute("noCoinsError", true);
        }
        return "redirect:/shop/goldSet";
    }


    @GetMapping("/shop/goldSet/buy/head/{id}")
    public String buyHeadByID(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        if (itemService.buyHeadByID(id)) {
            redirectAttributes.addFlashAttribute("successBuy", true);
        } else {
            redirectAttributes.addFlashAttribute("noCoinsError", true);
        }
        return "redirect:/shop/goldSet";
    }


    @GetMapping("/shop/goldSet/buy/boots/{id}")
    public String buyBootsByID(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        if (itemService.buyBootsByID(id)) {
            redirectAttributes.addFlashAttribute("successBuy", true);
        } else {
            redirectAttributes.addFlashAttribute("noCoinsError", true);
        }
        return "redirect:/shop/goldSet";
    }


    @GetMapping("/shop/goldSet/buy/trinkets/{id}")
    public String buyTrinketsByID(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        if (itemService.buyTrinketsByID(id)) {
            redirectAttributes.addFlashAttribute("successBuy", true);
        } else {
            redirectAttributes.addFlashAttribute("noCoinsError", true);
        }
        return "redirect:/shop/goldSet";
    }
}
