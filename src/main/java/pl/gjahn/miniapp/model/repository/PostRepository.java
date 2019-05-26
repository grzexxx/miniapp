package pl.gjahn.miniapp.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gjahn.miniapp.model.entity.PostEntity;

@Repository
public interface PostRepository extends CrudRepository <PostEntity, Integer> {


}
