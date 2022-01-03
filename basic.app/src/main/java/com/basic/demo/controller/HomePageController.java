package com.basic.demo.controller;

import com.basic.demo.dto.SignInAccountDto;
import com.basic.demo.model.Entry;
import com.basic.demo.model.Follow;
import com.basic.demo.model.MyUserDetail;
import com.basic.demo.model.User;
import com.basic.demo.repository.EntryRepositoryImpl;
import com.basic.demo.repository.FollowRepository;
import com.basic.demo.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Controller
public class HomePageController {

    @Autowired
    private EntryRepositoryImpl entryRepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private FollowRepository followRepository;

    @GetMapping
    public String homepage(@PageableDefault(value = 5, direction = Sort.Direction.DESC,sort = "entryId") Pageable pageable, Model model) {
        Page<Entry> entryPage = this.entryRepository.getAll(pageable);
        model.addAttribute("entryPage", entryPage);
        model.addAttribute("newEntry", new Entry());
        model.addAttribute("date", new Date());
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login/login-page";
    }

    @GetMapping("/signin")
    public String signInPage(Model model) {
        model.addAttribute("signInAccountDto", new SignInAccountDto());
        return "login/sign-in-page";
    }

    @GetMapping("/profile/{id}")
    public String getProfile(@AuthenticationPrincipal MyUserDetail userDetail, @PathVariable Integer id,
                             Model model, @PageableDefault(value = 5) Pageable pageable) {
        User profileUser = this.userRepository.findById(id);
        Follow follow = null;
        follow = this.followRepository.findByFollowerAndUser(userDetail.getUser(), profileUser);
        if (follow == null) {
            model.addAttribute("check", 1);
        } else {
            model.addAttribute("check", 2);
        }
        model.addAttribute("userProfile", profileUser);
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("author", 2);
        model.addAttribute("entryList",
                this.entryRepository.findAllByUser(profileUser.getUserId(), pageable));
        return "profile";
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal MyUserDetail userDetail, Model model,
                             @PageableDefault(value = 5) Pageable pageable) {
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("author", 1);
        model.addAttribute("entryList",
                this.entryRepository.findAllByUser(userDetail.getUser().getUserId(), pageable));
        return "profile";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403Page";
    }


}
