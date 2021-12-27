package com.basic.demo.dto;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryDto {
    public Cloudinary config() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dnljocati",
                "api_key", "788233159117744",
                "api_secret", "wJko7UoOZqQrjaRkzz3pZnvcgmQ"
        ));
        return cloudinary;
    }

}
