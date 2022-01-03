package com.basic.demo.controller;

import com.basic.demo.dto.SignInAccountDto;
import com.basic.demo.model.Account;
import com.basic.demo.model.Follow;
import com.basic.demo.model.MyUserDetail;
import com.basic.demo.model.User;
import com.basic.demo.repository.AccountRepositoryImpl;
import com.basic.demo.repository.FollowRepository;
import com.basic.demo.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AccountRepositoryImpl accountRepository;

    @Autowired
    private FollowRepository followRepository;

    @PostMapping("/create")
    public String createUser(@ModelAttribute("signInAccountDto") SignInAccountDto signInAccountDto) {
        Account newAccount = new Account(signInAccountDto.getAccountUsername(),
                new BCryptPasswordEncoder().encode(signInAccountDto.getAccountPassword()),
                signInAccountDto.getAccountEmail());
        this.accountRepository.createAccount(newAccount);
        User newUser = new User(signInAccountDto.getUserName(),
                signInAccountDto.getUserBirthday(),
                this.accountRepository.findNewAccount());
        this.userRepository.createUser(newUser);
        return "redirect:/";
    }

//    @GetMapping("/follow/{id}")
//    public String doFollow(@AuthenticationPrincipal MyUserDetail userDetail,
//                           @PathVariable("id") Integer id){
//        User user = this.userRepository.findById(id);
//        Follow newFollow = new Follow(user,userDetail.getUser());
//        this.followRepository.save(newFollow);
//        return "redirect:/profile/"+id;
//    }

    @GetMapping("/follow/{id}")
    public String doFollow(@AuthenticationPrincipal MyUserDetail userDetail,
                           @PathVariable("id") Integer id){
        User user = this.userRepository.findById(id);
        Follow newFollow = new Follow(user,userDetail.getUser());
        this.followRepository.save(newFollow);
        return "/button :: doFollow";
    }



    @GetMapping("/unfollow/{id}")
    public String doUnFollow(@AuthenticationPrincipal MyUserDetail userDetail,
                           @PathVariable("id") Integer id,
                             Model model){
        User user = this.userRepository.findById(id);
        Follow follow = this.followRepository.findByFollowerAndUser(userDetail.getUser(),user);
        if (follow != null){
            this.followRepository.deleteById(follow.getId());
        }
        model.addAttribute("check",1);
        return "redirect:/profile/"+id;
    }



}
