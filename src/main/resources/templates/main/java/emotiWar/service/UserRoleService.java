package emotiWar.service;

import emotiWar.model.service.AdminRoleServiceModel;

public interface UserRoleService {
    void initRoles ();

    void changeRole(AdminRoleServiceModel serviceModel);
}
