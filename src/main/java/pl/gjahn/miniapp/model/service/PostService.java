package pl.gjahn.miniapp.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gjahn.miniapp.model.entity.CommentEntity;
import pl.gjahn.miniapp.model.entity.PostEntity;
import pl.gjahn.miniapp.model.entity.UserEntity;
import pl.gjahn.miniapp.model.form.CommentForm;
import pl.gjahn.miniapp.model.form.PostForm;
import pl.gjahn.miniapp.model.repository.CommentRepository;
import pl.gjahn.miniapp.model.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    @Autowired
    SessionService sessionService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    public void addPost(PostForm postForm) {
        PostEntity post = new PostEntity();

        post.setPostName(postForm.getPostName());
        post.setPostText(postForm.getPostText());

        UserEntity user = new UserEntity();
        user.setId(sessionService.getUserId());


        post.setUser(user);

        postRepository.save(post);
    }

    public Iterable<PostEntity> getAllPosts(){
        return postRepository.findTop10ByOrderByIdDesc();
    }

    public void deletePost(int id) {
        postRepository.deleteById(id);
    }

    public PostEntity getPost(int id) {
        return postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void addComment (CommentForm commentForm, int postId, int userId){
        PostEntity postEntity = new PostEntity();
        postEntity.setId(postId) ;

       //todo UserEntity userEntity = n
    }

    //public List<CommentEntity> getAll
            //todo zeby nie odowływać sie do repozytorim bezpośrenidip
}
