package net.avishkar.springboot.controller;

import jakarta.validation.Valid;
import net.avishkar.springboot.dto.CommentDto;
import net.avishkar.springboot.dto.PostDto;
import net.avishkar.springboot.service.CommentService;
import net.avishkar.springboot.service.PostService;
import net.avishkar.springboot.util.Role;
import net.avishkar.springboot.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController
{
    private PostService postService;
    private CommentService commentService;

    // Constructor Based Dependency
    public PostController(PostService postService,CommentService commentService)
    {
        this.postService = postService;
        this.commentService = commentService;
    }

    // create handler method, GET request and return model and view
    // http://localhost:8080/admin/posts
    @GetMapping("/admin/posts")
    public String posts(Model model)
    {
        String role = SecurityUtils.getRole();
        List<PostDto> posts = null;
        if(Role.ROLE_ADMIN.name().equals(role))
        {
            posts = postService.findAllPosts();
        }else{
            posts = postService.findPostsByUser();
        }
        model.addAttribute("posts",posts);
        return "/admin/posts";
    }

    // handler method to handle list comments request
    @GetMapping("/admin/posts/comments")
    public String postComment(Model model)
    {
        String role = SecurityUtils.getRole();
        List<CommentDto> comments = null;
        if(Role.ROLE_ADMIN.name().equals(role))
        {
           comments = commentService.findAllComments();
        }else{
            comments = commentService.findCommentByPost();
        }

        model.addAttribute("comments",comments);
        return "admin/comments";
    }

    // handler method to handle delete comment request
    @GetMapping("/admin/posts/comments/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId)
    {
        commentService.deletComment(commentId);
        return "redirect:/admin/posts/comments";
    }


    // create handler method, to handle new post request
    // http://localhost:8080/admin/posts/newpost
    @GetMapping("admin/posts/newpost")
    public String newPostForm(Model model)
    {
        PostDto postDto = new PostDto();
        model.addAttribute("post",postDto);
        return "admin/create_post";
    }

    // create handler method, to handle form submit request
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("post",postDto);
            return "admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    // handler method to handle edit post request
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId, Model model)
    {
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post",postDto);
        return "admin/edit_post";
    }

    // handler method to handle edit post submit request
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,@Valid @ModelAttribute("post") PostDto post, BindingResult bindingResult, Model model)
    {
            if(bindingResult.hasErrors())
            {
                model.addAttribute("post",post);
                return "admin/edit_post";
            }

            post.setId(postId);
            postService.updatePost(post);
            return "redirect:/admin/posts";
    }

    // handler method to handle delete request
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId)
    {
        postService.deletPost(postId);
        return "redirect:/admin/posts";
    }

    // handler method to handle view post request
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl,Model model)
    {
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post",postDto);
        return "admin/view_post";
    }

    // handler method to handle search blog posts request
    // localhost:8080/admin/posts/?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query,Model model)
    {
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts",posts);
        return "admin/posts";
    }



    private static String getUrl(String postTitle)
    {
        // OOPS Concept Explained
        // oops-concept-explained
        String title = postTitle.trim().toLowerCase();
        String url = title.replace("\\s+","-");
        url = url.replaceAll("[^A-Za-z0-9]","-");
        return url;
    }
}
