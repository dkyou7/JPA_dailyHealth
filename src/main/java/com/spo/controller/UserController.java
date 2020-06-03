package com.spo.controller;

import com.spo.domain.User;
import com.spo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입 페이지
    @GetMapping(value = "/users/new")
    public String createForm(Model model){
        model.addAttribute("userForm",new UserForm());
        return "users/createUserForm";
    }

    // 회원가입 처리
    @PostMapping(value = "/users/new")
    public String create(@Valid UserForm form, BindingResult result){
        if(result.hasErrors()){
            return "users/createUserForm";
        }
        User user = form.toEntity();    // DTO를 엔티티 형식에 맞추어 변경
        userService.join(user);

        return "redirect:/";
    }

    // 로그인 페이지
    @GetMapping("/users/login")
    public String dispLogin(Model model) {
        model.addAttribute("form",new UserLoginForm());
        return "users/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/users/login/result")
    public String dispLoginResult() {
        return "users/loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/users/logout/result")
    public String dispLogout() {
        return "users/logoutSuccess";
    }

    // 접근 거부 페이지
    @GetMapping("/users/denied")
    public String dispDenied() {
        return "users/denied";
    }

    // 내 정보 페이지
    @GetMapping("/users/info")
    public String dispMyInfo() {
        return "users/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/users/admin")
    public String dispAdmin() {
        return "/admin";
    }

    @GetMapping(value = "/users")
    public String list(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users",users);
        return "users/userList";
    }
}
