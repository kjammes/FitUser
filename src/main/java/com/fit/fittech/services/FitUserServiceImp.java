package com.fit.fittech.services;

import com.fit.fittech.models.FitUser;
import com.fit.fittech.models.Role;
import com.fit.fittech.repos.RoleRepo;
import com.fit.fittech.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FitUserServiceImp implements FitUserService{
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public FitUser saveUser(FitUser user) {
        log.info("Saving user to database");
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role to database");
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Giving user new role = "+ roleName );
        FitUser user = userRepo.findByEmail(email);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public FitUser getUser(String email) {
        log.info("Fetching user from database");
        return userRepo.findByEmail(email);
    }

    @Override
    public List<FitUser> getFitUsers() {
        log.info("Fetching all users from database");
        return userRepo.findAll();
    }


}
