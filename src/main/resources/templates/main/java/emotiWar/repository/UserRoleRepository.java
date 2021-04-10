package emotiWar.repository;

import emotiWar.model.entity.UserRoleEntity;
import emotiWar.model.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository <UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByRole(UserRole role);
}
