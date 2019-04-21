package com.bigsea.service.impl;

import com.bigsea.entity.BootUser;
import com.bigsea.mapper.BootUserMapper;
import com.bigsea.service.BootUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BootUserServiceImpl implements BootUserService {
    @Autowired
    private BootUserMapper bootUserMapper;

    @Override
    public BootUser login(String name, String password) {
        BootUser bootUser = new BootUser();
        bootUser.setName(name);
        bootUser.setPassword(password);
        List<BootUser> bootUserList = bootUserMapper.selectByContition(bootUser);
        return bootUserList.get(0);
    }
}
