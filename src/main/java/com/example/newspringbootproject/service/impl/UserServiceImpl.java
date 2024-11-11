package com.example.newspringbootproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.newspringbootproject.mapper.UserMapper;
import com.example.newspringbootproject.pojo.User;
import com.example.newspringbootproject.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
