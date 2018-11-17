package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dormitorymaintenancesystem.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/setAccepted/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity activateUser(@PathVariable Long id) {
        return userService.activateUser(id);
    }

    @PostMapping(value = "/setSuspended/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity suspendUser(@PathVariable Long id) {
        return userService.suspendUser(id);
    }
}
