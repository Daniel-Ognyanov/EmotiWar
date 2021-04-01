package emotiWar.service.impl;

import emotiWar.model.entity.EmotiEntity;
import emotiWar.model.entity.ItemEntity;
import emotiWar.model.entity.UserEntity;
import emotiWar.model.entity.UserRoleEntity;
import emotiWar.model.entity.enums.UserRole;
import emotiWar.model.service.UserLoginServiceModel;
import emotiWar.model.service.UserRegistrationServiceModel;
import emotiWar.repository.ItemRepository;
import emotiWar.repository.UserRepository;
import emotiWar.repository.UserRoleRepository;
import emotiWar.service.EmotiService;
import emotiWar.service.EmotiWarUserService;
import emotiWar.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final EmotiWarUserService emotiWarUserService;
    private final EmotiService emotiService;
    private final ItemRepository itemRepository;


    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder,
                           UserRoleRepository userRoleRepository, UserRepository userRepository,
                           EmotiWarUserService emotiWarUserService, EmotiService emotiService, ItemRepository itemRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.emotiWarUserService = emotiWarUserService;
        this.emotiService = emotiService;
        this.itemRepository = itemRepository;
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel serviceModel) {
        UserEntity newUser = modelMapper.map(serviceModel, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(serviceModel.getPassword()));

        UserRoleEntity userRole = userRoleRepository.
                findByRole(UserRole.USER).orElseThrow(
                () -> new IllegalStateException("unknown user role"));

        newUser.addRole(userRole);
        EmotiEntity emoti = new EmotiEntity();

        emoti.setStrength(1);
        emoti.setArmor(1);
        emoti.setWins(0);
        emoti.setLoses(0);
        emoti.setCoins(10);
        emoti.setBaseArmor(1);
        emoti.setBaseStrength(1);
        emoti.setWeapon(itemRepository.findById(16L).orElse(null));
        emoti.setShield(itemRepository.findById(17L).orElse(null));
        emoti.setHead(itemRepository.findById(18L).orElse(null));
        emoti.setBoots(itemRepository.findById(19L).orElse(null));
        emoti.setTrinkets(itemRepository.findById(20L).orElse(null));
        emotiService.emotiSave(emoti);
        newUser.setEmoti(emoti);
        userRepository.save(newUser);


    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void loginUser(UserLoginServiceModel userServiceModel) {
        UserDetails principal = emotiWarUserService.loadUserByUsername(userServiceModel.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                userServiceModel.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserEntity getCurrentUser() {
        return userRepository.findByUsername(getCurrentUsername()).orElse(null);
    }

    @Override
    public List<UserEntity> getTopPlayers() {
        return userRepository.findAll().stream().sorted((a, b) -> {
            if (Integer.compare(b.getEmoti().getWins() - b.getEmoti().getLoses(), a.getEmoti().getWins() - a.getEmoti().getLoses()) == 0) {
              return   Integer.compare(b.getEmoti().getWins(), a.getEmoti().getWins());
            } else {
                return Integer.compare(b.getEmoti().getWins() - b.getEmoti().getLoses(), a.getEmoti().getWins() - a.getEmoti().getLoses());
            }
                }
        ).collect(Collectors.toList());

    }

    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
