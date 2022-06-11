package com.fit.fittech.services;

import com.fit.fittech.models.FitUser;
import com.fit.fittech.models.Role;
import com.fit.fittech.repos.RoleRepo;
import com.fit.fittech.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FitUserServiceImp implements FitUserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        FitUser fitUser = userRepo.findByEmail(email);
        if(fitUser == null) {
            log.error("Fit user not found with email = {}", email);
            throw new UsernameNotFoundException("Fit user not found with given email");
        } else {
            log.info("Fit user found with email = {}", email);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : fitUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(fitUser.getEmail(), fitUser.getPassword(), authorities);
    }

    @Override
    public FitUser saveUser(FitUser user) {
        log.info("Saving user to database");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public FitUser getUserByName(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public List<FitUser> getFitUsers() {
        log.info("Fetching all users from database");
        return userRepo.findAll();
    }

}
