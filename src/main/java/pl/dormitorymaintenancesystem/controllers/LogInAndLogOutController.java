package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.model.users.Administrator;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.User;
import pl.dormitorymaintenancesystem.model.users.Worker;
import pl.dormitorymaintenancesystem.repositories.AdministratorRepository;
import pl.dormitorymaintenancesystem.repositories.InhabitantRepository;
import pl.dormitorymaintenancesystem.repositories.UserRepository;
import pl.dormitorymaintenancesystem.repositories.WorkerRepository;
import pl.dormitorymaintenancesystem.service.LogInAndLogOutService;
import pl.dormitorymaintenancesystem.utils.dataInput.CredentialsDTO;
import pl.dormitorymaintenancesystem.utils.dataInput.PasswordChangeDTO;
import pl.dormitorymaintenancesystem.utils.dataOutput.AuthenticationResponseDTO;
import pl.dormitorymaintenancesystem.utils.dataOutput.MessageDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;

@RestController
@RequestMapping(value = "api")
@Transactional
public class LogInAndLogOutController {


    @Autowired
    private LogInAndLogOutService logInAndLogOutService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody CredentialsDTO credentialsDTO, HttpServletRequest request) {
        return logInAndLogOutService.login(credentialsDTO,request);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity logout(HttpSession session) {
        return logInAndLogOutService.logout(session);
    }

    @GetMapping(value = "/info")
    public ResponseEntity info() {
        return logInAndLogOutService.info();
    }

    @PutMapping(value = "/password")
    public ResponseEntity changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        return logInAndLogOutService.changePassword(passwordChangeDTO);
    }
}
