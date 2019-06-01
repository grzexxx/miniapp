package pl.gjahn.miniapp.controllers.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.gjahn.miniapp.model.entity.UserEntity;
import pl.gjahn.miniapp.model.form.CommentForm;
import pl.gjahn.miniapp.model.form.PostForm;
import pl.gjahn.miniapp.model.service.PostService;
import pl.gjahn.miniapp.model.service.SessionService;


@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/user/post/add")
    public String add(Model model) {

        if (!sessionService.isLogin()) {
            return "redirect:/user/login";
        }
        model.addAttribute("postForm", new PostForm());
        return "user/post/add";
    }

    @PostMapping("/user/post/add")
    public String add(@ModelAttribute PostForm postForm,
                      RedirectAttributes redirectAttributes) {

        postService.addPost(postForm);

        redirectAttributes.addFlashAttribute("info", "New post added");
        return "redirect:/user/dashboard";

    }

    @GetMapping("user/post/delete/{id}")
    public String deletePost(@PathVariable("id") int id) {
        if (sessionService.getAccountType() != UserEntity.AccountType.ADMIN) {
            return "redirect:/user/dashboard";
        }

        postService.deletePost(id);
        return "redirect:/user/dashboard";
    }

    @GetMapping("/user/post/details_post/{id}")
    public String addComment(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        model.addAttribute("comments", postService.getAllCommentsByPost(id));
        model.addAttribute("commentForm", new CommentForm());

        return "user/post/details_post";
    }

    @PostMapping("/comment/add/{postId}")
    public String addComment(@ModelAttribute CommentForm commentForm, @PathVariable("postId") int postId, Model model) {
        if (!sessionService.isLogin()) {
            return "redirect:/user/login";
        }

        postService.addComment(commentForm, postId, sessionService.getUserId());
        return "redirect:/user/post/details_post/" + postId;


    }

}
