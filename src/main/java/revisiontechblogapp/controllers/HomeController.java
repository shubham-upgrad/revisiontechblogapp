package revisiontechblogapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import revisiontechblogapp.models.Post;
import revisiontechblogapp.services.PostService;

import java.util.ArrayList;
import java.util.Date;

@Controller //The Request Handlers (are friends with Services)
public class HomeController {
    @Autowired
    private PostService postService; // Dependency of HomeController
    @RequestMapping("/")
    public String index(Model model){
        ArrayList<Post> al=postService.getAllPosts();
        model.addAttribute("al_post",al);
        return "index"; // logical view name index --> index.html
    }
}
