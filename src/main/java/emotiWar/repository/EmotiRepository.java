package emotiWar.repository;

import emotiWar.model.entity.EmotiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotiRepository extends JpaRepository<EmotiEntity, Long>{

}
