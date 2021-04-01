package emotiWar.service.impl;

import emotiWar.model.entity.UserRoleEntity;
import emotiWar.model.entity.enums.UserRole;
import emotiWar.repository.UserRoleRepository;
import emotiWar.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRolesImpl(UserRoleRepository userRoleRepository) {
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
}
