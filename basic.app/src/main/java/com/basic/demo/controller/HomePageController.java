package com.basic.demo.controller;

import com.basic.demo.dto.SignInAccountDto;
import com.basic.demo.model.Entry;
import com.basic.demo.repository.EntryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @Autowired
    private EntryRepositoryImpl entryRepository;

    @GetMapping
    public String homepage(@PageableDefault(value = 5) Pageable pageable, Model model) {
        Page<Entry> entryPage = this.entryRepository.getAll(pageable);
        model.addAttribute("entryPage",entryPage);
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

}
