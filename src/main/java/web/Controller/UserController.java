package web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.Model.User;
import web.Service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String showUsers(ModelMap model) {
        model.addAttribute("users", userService.listUser());
        return "allUsers";
    }

    @GetMapping(value = "/addUser")
    public String addUser(@ModelAttribute("newUser") User user) {
        return "newUser";
    }

    @PostMapping
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/editUser")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PostMapping("/editUser")
    public String update(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/";
    }



    @GetMapping("/deleteUser")
    public String deleteUserById(@RequestParam("id") int id) {
        userService.removeUser(id);
        return "redirect:/";
    }

}
