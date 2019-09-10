package revisiontechblogapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping("/editPost")
    public String editPostPage(@RequestParam(name="postId") Integer id,Model model){
        Post p=postService.getOnePost(id); //using find() method of EntityManager(interface)
        model.addAttribute("post",p);
        return "post/edit";
    }
    @RequestMapping(value = "/editPost",method = RequestMethod.POST)
    public String editPost(Post updatedPost,Model model){
        /*
        * updatedPost contains(example) :
        *   id = 5 (not null, it is coming from hidden field named id)
        *   title = "Some updated Title" (not null, coming from a text field "title")
        *   body = "Some updated Body"
        *   date = null
        *   user = null
        * */
        // First set user and date from the database(existing post)
        Post existing=postService.getOnePost(updatedPost.getId());
        updatedPost.setDate(existing.getDate());
        updatedPost.setUser(existing.getUser());
        // Now merge() [--> updation] this post with the post in database
        postService.editPost(updatedPost);
        return "redirect:/posts";
    }
    @RequestMapping(value="/deletePost")
    public String deletePost(@RequestParam(name="postId")Integer id){
        postService.deletePost(id);
        return "redirect:/posts";
    }

}
