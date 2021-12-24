package com.basic.demo.controller;

import com.basic.demo.dto.SignInAccountDto;
import com.basic.demo.model.Account;
import com.basic.demo.model.User;
import com.basic.demo.repository.AccountRepositoryImpl;
import com.basic.demo.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AccountRepositoryImpl accountRepository;

    @PostMapping("/create")
    public String createUser(@ModelAttribute("signInAccountDto") SignInAccountDto signInAccountDto,
                             Model model, RedirectAttributes redirectAttributes) {
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
}
