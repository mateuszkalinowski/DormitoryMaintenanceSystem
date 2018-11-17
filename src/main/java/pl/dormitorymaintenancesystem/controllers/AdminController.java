package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dormitorymaintenancesystem.service.AdminService;
import pl.dormitorymaintenancesystem.utils.dataInput.AdminInfo;

@RestController
@RequestMapping(value = "api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/updateInfo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity updateAdminInfo(@RequestBody AdminInfo adminInfo) {
        return adminService.updateAdminInfo(adminInfo);
    }
}
