package pl.gjahn.miniapp.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gjahn.miniapp.model.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<UserEntity> findById(int id);


   // "SELECT CASE WHEN EXISTS (  SELECT * FROM `user` WHERE `id` = ?1) THEN CAST(TRUE) ELSE CAST(FALSE) END")

    @Query(nativeQuery = true, value = "SELECT (CASE WHEN COUNT(*) > 0 THEN 'TRUE' ELSE 'FALSE' END) FROM `user` WHERE `id` =?1")
    boolean existsById(int id);


}
