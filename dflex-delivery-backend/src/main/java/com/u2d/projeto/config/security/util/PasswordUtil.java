package com.u2d.projeto.config.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    public static String encoder(String password){
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
        return encodedPassword;
    }

    public static void main(String[] args) {
        System.out.println(encoder("415782"));
    }
}
