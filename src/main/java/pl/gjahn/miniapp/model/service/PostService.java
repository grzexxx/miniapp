package pl.gjahn.miniapp.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gjahn.miniapp.model.entity.PostEntity;
import pl.gjahn.miniapp.model.entity.UserEntity;
import pl.gjahn.miniapp.model.form.PostForm;
import pl.gjahn.miniapp.model.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    SessionService sessionService;

    @Autowired
    PostRepository postRepository;

    public void addPost(PostForm postForm) {
        PostEntity post = new PostEntity();

        post.setPostName(postForm.getPostName());
        post.setPostText(postForm.getPostText());

        UserEntity user = new UserEntity();
        user.setId(sessionService.getUserId());

        post.setUser(user);

        postRepository.save(post);

    }
}
