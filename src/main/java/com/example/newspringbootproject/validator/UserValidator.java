package com.example.newspringbootproject.validator;

import com.example.newspringbootproject.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target == null) {
            errors.rejectValue("", null, "用户不能为空");
        }
        User user = (User) target;
        if(!StringUtils.hasText(user.getUserName())) {
            errors.rejectValue("userName", null, "用户名不能为空");
        }
    }
}
