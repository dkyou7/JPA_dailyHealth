package com.spo.controller;

import com.spo.domain.User;
import com.spo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/users/new")
    public String createForm(Model model){
        model.addAttribute("userForm",new UserForm());
        return "users/createUserForm";
    }

    @PostMapping(value = "/users/new")
    public String create(@Valid UserForm form, BindingResult result){

        if(result.hasErrors()){
            return "users/createUserForm";
        }

        User user = new User();
        user.setName(form.getName());
        user.setAge(form.getAge());
        user.setCardNum(form.getCardNum());
        user.setHeight(form.getHeight());
        user.setWeight(form.getWeight());

        userService.join(user);

        return "redirect:/";
    }

    @GetMapping(value = "/users")
    public String list(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users",users);
        return "users/userList";
    }
}
