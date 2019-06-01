package pl.gjahn.miniapp.model.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gjahn.miniapp.model.entity.CommentEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {

//todo    @Query (nativeQuery = true, name = "SELECT * FROM comment WHERE post_id")

}
