package pl.gjahn.miniapp.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gjahn.miniapp.model.dto.PostDto;
import pl.gjahn.miniapp.model.entity.PostEntity;
import pl.gjahn.miniapp.model.service.PostService;
import pl.gjahn.miniapp.model.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostControllerApi {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    private final String API_KEY = "8n4yteahfvsawebeat5427609pmlfytRQBRVDsaddddwn..wevfsdffdfdff";

    @GetMapping("/post")
    public ResponseEntity getAllPosts() {

        return ResponseEntity.ok(postService.getAllPosts());

    }

    @GetMapping("/post/{id}")
    public ResponseEntity getOnePost(@PathVariable("id") int id) {

        Optional<PostEntity> post = postService.getPostOptional(id);
        if (!post.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with this id not exist");
        }
        return ResponseEntity.ok(post);

    }

    @PostMapping(value = "/post", consumes = "application/json")
    public ResponseEntity savePost(@RequestBody PostDto postDto, @RequestHeader("api-key") String key) {

        if (!key.equals(API_KEY)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!userService.idExist(postDto.getUserId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user with that id");
        }

        return ResponseEntity.ok(
                postService.addPost(postDto)
        );
    }
}
