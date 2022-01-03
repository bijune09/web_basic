package com.basic.demo.controller;

import com.basic.demo.dto.CloudinaryDto;
import com.basic.demo.model.Entry;
import com.basic.demo.model.MyUserDetail;
import com.basic.demo.repository.EntryRepositoryImpl;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/entry")
public class EntryController {
    @Autowired
    private EntryRepositoryImpl entryRepository;

    @Autowired
    private CloudinaryDto cloudinaryDto;

    @GetMapping("/createEntry")
    public String showCreateEntry(Model model) {
        model.addAttribute("entry", new Entry());
        return "create_entry";
    }

    @PostMapping("/createEntry")
    public String createEntry(@ModelAttribute("newEntry") Entry entry,
                              @RequestParam("image") MultipartFile multipartFile,
                              @AuthenticationPrincipal MyUserDetail userDetail) throws IOException {
        Cloudinary cloudinary = cloudinaryDto.config();
        Map resolver = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        String filename = (String) resolver.get("secure_url");
        entry.setEntryImg(filename);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
//        entry.setCreateDate(String.valueOf(date));
        entry.setCreateDate(date);
        entry.setUser(userDetail.getUser());
        this.entryRepository.createEntry(entry);
        return "redirect:/";
    }


}
