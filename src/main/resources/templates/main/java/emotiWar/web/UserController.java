package emotiWar.web;

import emotiWar.model.binding.UserLoginBindingModel;
import emotiWar.model.binding.UserRegistrationBindingModel;
import emotiWar.model.service.UserLoginServiceModel;
import emotiWar.model.service.UserRegistrationServiceModel;
import emotiWar.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("errorUserExist")
    public boolean errorUserExist() {
        return false;
    }

    @ModelAttribute("errorPasswordMatch")
    public boolean errorPasswordMatch() {
        return false;
    }

    @ModelAttribute("errorEmailExists")
    public boolean errorEmailExists() {
        return false;
    }

    @ModelAttribute("registrationBindingModel")
    public UserRegistrationBindingModel createBindingModel() {
        return new UserRegistrationBindingModel();
    }

    @ModelAttribute("userNotFound")
    public boolean userNotFound() {
        return false;
    }

    @ModelAttribute("loginBindingModel")
    public UserLoginBindingModel createLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping ("/login")
     public String login(){

        return "login";
    }


    @GetMapping ("/signup")
    public String signup(){

        return "signup";
    }

    @PostMapping("/signup")
    public String signupConfirm(
            @Valid UserRegistrationBindingModel registrationBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registrationBindingModel", bindingResult);
            return "redirect:/users/signup";
        }

        if (userService.emailExists(registrationBindingModel.getEmail())) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute("errorEmailExists", true);
            return "redirect:/users/signup";
        }

        if (userService.usernameExists(registrationBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute("errorUserExist", true);

            return "redirect:/users/signup";
        }

        if (!registrationBindingModel.getPassword().equals(registrationBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute("errorPasswordMatch", true);
            return "redirect:/users/signup";
        }

        UserRegistrationServiceModel userServiceModel = modelMapper
                .map(registrationBindingModel, UserRegistrationServiceModel.class);

        userService.registerAndLoginUser(userServiceModel);

        return "login";
    }

    @PostMapping("/login")
    public String loginPost(UserLoginBindingModel loginBindingModel,
                            RedirectAttributes redirectAttributes) {

        UserLoginServiceModel userServiceModel = modelMapper
                .map(loginBindingModel, UserLoginServiceModel.class);


        try {
            userService.loginUser(userServiceModel);

        } catch (Exception e) {
           redirectAttributes.addFlashAttribute("userNotFound", true);
            return "redirect:/users/login";
        }


        return "redirect:/home";
    }
}
