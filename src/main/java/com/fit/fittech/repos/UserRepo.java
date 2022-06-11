package com.fit.fittech.repos;

import com.fit.fittech.models.FitUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<FitUser, Long> {
    FitUser findByEmail(String email);

    FitUser findByName(String name);
}
