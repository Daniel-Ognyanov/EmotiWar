package emotiWar.service.impl;

import emotiWar.model.entity.UserEntity;
import emotiWar.model.entity.UserRoleEntity;
import emotiWar.model.entity.enums.UserRole;
import emotiWar.model.service.AdminRoleServiceModel;
import emotiWar.repository.UserRepository;
import emotiWar.repository.UserRoleRepository;
import emotiWar.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRolesImpl implements UserRoleService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserRolesImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initRoles() {
        if (userRoleRepository.findAll().isEmpty()) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    @Override
    public void changeRole(AdminRoleServiceModel serviceModel) {
        List <UserRoleEntity> userRoles = new ArrayList<>();
         UserEntity curUser =  userRepository.findByUsername(serviceModel.getUsername()).orElse(null);
         if (serviceModel.getRole().equals("USER")) {
            userRoles.add(userRoleRepository.findByRole(UserRole.USER).orElse(null));
            curUser.setRoles(userRoles);
            userRepository.save(curUser);
         } else if (serviceModel.getRole().equals("ADMIN")){
             userRoles.add(userRoleRepository.findByRole(UserRole.USER).orElse(null));
             userRoles.add(userRoleRepository.findByRole(UserRole.ADMIN).orElse(null));
             curUser.setRoles(userRoles);
             userRepository.save(curUser);
         }
    }
}
