package com.fit.fittech.api;

import com.fit.fittech.models.FitUser;
import com.fit.fittech.models.Role;
import com.fit.fittech.services.FitUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FitUserController {
    private final FitUserService fitUserService;

    @GetMapping("/fit-users")
    public ResponseEntity<List<FitUser>> getUsers() {
        return ResponseEntity.ok().body(fitUserService.getFitUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<FitUser> saveUser(@RequestBody FitUser fitUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(fitUserService.saveUser(fitUser));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(null).body(fitUserService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        fitUserService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm {
    private String email;
    private String roleName;
}