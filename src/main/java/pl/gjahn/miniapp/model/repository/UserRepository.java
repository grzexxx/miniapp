package pl.gjahn.miniapp.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gjahn.miniapp.model.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email); //zapytanie czytane są po nazwach metod

    boolean existsByEmail(String email);


}
