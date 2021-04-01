package emotiWar.service;

import emotiWar.model.entity.UserEntity;
import emotiWar.model.service.UserLoginServiceModel;
import emotiWar.model.service.UserRegistrationServiceModel;

import java.util.List;

public interface UserService  {
    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);
    boolean usernameExists(String username);
    boolean emailExists(String email);
    void loginUser(UserLoginServiceModel userServiceModel);
    UserEntity getCurrentUser();
    List <UserEntity> getTopPlayers();
}
