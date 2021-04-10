package emotiWar.web;

import emotiWar.model.binding.AdminRoleBindingModel;
import emotiWar.model.service.AdminRoleServiceModel;
import emotiWar.service.UserRoleService;
import emotiWar.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminControlller {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;

    public AdminControlller(UserRoleService userRoleService,UserService userService, ModelMapper modelMapper) {
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("usernameNotFound")
    public boolean usernameNotFound() {
        return false;
    }

    @ModelAttribute("roleChange")
    public boolean roleChange() {
        return false;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping ("/changeRole")
    public String changeRole(AdminRoleBindingModel adminRoleBindingModel,
                              RedirectAttributes redirectAttributes){

        if (!userService.usernameExists(adminRoleBindingModel.getUsername())){
           redirectAttributes.addFlashAttribute("usernameNotFound", true);
            return "redirect:/admin";
        }

        AdminRoleServiceModel serviceModel = modelMapper.map(adminRoleBindingModel, AdminRoleServiceModel.class);
        userRoleService.changeRole(serviceModel);
        redirectAttributes.addFlashAttribute("roleChange", true);
        return "redirect:/admin";
    }
}
