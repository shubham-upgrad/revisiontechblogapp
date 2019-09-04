package revisiontechblogapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import revisiontechblogapp.models.Post;
import revisiontechblogapp.models.User;
import revisiontechblogapp.services.PostService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class PostController {

    @Autowired
    PostService postService;
    @RequestMapping("/posts")
    public String userPosts(Model model,HttpSession session){
        //1. Get user whose posts are to be displayed
        User user=(User)session.getAttribute("loggeduser");
        ArrayList<Post> al=postService.getUserPosts(user);
        model.addAttribute("userpostlist",al);
        return "posts";
    }
    @RequestMapping("/post/create") // GET method
    public String createPostPage(){

        return "createpost";
    }
    @RequestMapping(value = "/post/create",method = RequestMethod.POST)
    public String createPost(Post post, HttpSession session){
        // Post only has title and body, it needs date and user
        //1. set User
        User loggeduser=(User)session.getAttribute("loggeduser");
        post.setUser(loggeduser);
        //2. Ask service to add date and create this post in database
        postService.createPost(post);
        return "redirect:/posts";
    }


}
