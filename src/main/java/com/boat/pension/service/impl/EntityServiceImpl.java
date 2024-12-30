package com.boat.pension.service.impl;

import com.boat.pension.model.Admin;
import com.boat.pension.service.EntityService;
import org.springframework.stereotype.Service;

@Service
public class EntityServiceImpl implements EntityService {
    private Admin admin;

    @Override
    public void setAdmin(Admin admin) {
        this.admin=admin;
    }

    @Override
    public Admin getAdmin() {
        return admin;
    }
}
