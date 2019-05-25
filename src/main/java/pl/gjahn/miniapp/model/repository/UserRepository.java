package pl.gjahn.miniapp.model.repository;

import org.springframework.data.repository.CrudRepository;
import pl.gjahn.miniapp.model.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
