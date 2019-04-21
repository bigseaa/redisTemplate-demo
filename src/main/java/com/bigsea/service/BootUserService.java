package com.bigsea.service;

import com.bigsea.entity.BootUser;

public interface BootUserService {
    BootUser login(String name, String password);
}
