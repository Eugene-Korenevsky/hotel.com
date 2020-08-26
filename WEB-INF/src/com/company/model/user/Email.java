package com.company.model.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private String email;

    public Email(String email) {
        this.email = email;
    }
    
    public boolean isEmail () {
       Pattern pattern = Pattern.compile("(^\\S*@\\S*)");
       Matcher matcher = pattern.matcher(email);
       if(matcher.find()) {
            return true;
       }else {
            return false;
       }
    }

}
