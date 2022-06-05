package com.fit.fittech.services;

import com.fit.fittech.models.FitUser;
import com.fit.fittech.models.Role;

import java.util.List;

public interface FitUserService {
    FitUser saveUser(FitUser user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    FitUser getUser(String email);

    List<FitUser> getFitUsers();
}
