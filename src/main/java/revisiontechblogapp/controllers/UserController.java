package revisiontechblogapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import revisiontechblogapp.models.User;
import revisiontechblogapp.models.UserProfile;
import revisiontechblogapp.services.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/login")
    public String login(HttpSession session){
       User loggeduser=(User)session.getAttribute("loggeduser");
       if(loggeduser==null){
           return "user/login";
       }

       return "redirect:/posts";


    }



    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    public String loginUser(User user, HttpSession session){
        User checkedUser=userService.loginUser(user);
        if(checkedUser!=null){
            session.setAttribute("loggeduser",checkedUser);
            return "redirect:/posts";
        }
        return "user/login";
    }


    @RequestMapping("/user/registration") // Default request method is GET
    public String registration(Model model){

//        User user=new User();
//        user = {username:null ,password:null ,profile:null }
        User user = new User();
        UserProfile profile=new UserProfile();
        user.setProfile(profile);
//        user = {username : null, password:null , profile : {email : null,
//        fullName : null,
//        mobile : null
//        }}
        model.addAttribute("User",user);
        return "user/registration";
    }




    @RequestMapping(value="/user/registration",method = RequestMethod.POST)
    public String registerUser(User user){
        userService.registerUser(user);
        return "user/login";
    }
    @RequestMapping(value="/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
