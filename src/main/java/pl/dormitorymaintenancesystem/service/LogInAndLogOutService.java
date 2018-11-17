package pl.dormitorymaintenancesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.model.users.Administrator;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.User;
import pl.dormitorymaintenancesystem.model.users.Worker;
import pl.dormitorymaintenancesystem.repositories.AdministratorRepository;
import pl.dormitorymaintenancesystem.repositories.InhabitantRepository;
import pl.dormitorymaintenancesystem.repositories.UserRepository;
import pl.dormitorymaintenancesystem.repositories.WorkerRepository;
import pl.dormitorymaintenancesystem.utils.dataInput.CredentialsDTO;
import pl.dormitorymaintenancesystem.utils.dataInput.PasswordChangeDTO;
import pl.dormitorymaintenancesystem.utils.dataOutput.AuthenticationResponseDTO;
import pl.dormitorymaintenancesystem.utils.dataOutput.MessageDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;

@Service
@Transactional
public class LogInAndLogOutService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private InhabitantRepository inhabitantRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdministratorRepository administratorRepository;

    public ResponseEntity login(CredentialsDTO credentialsDTO, HttpServletRequest request) {

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentialsDTO.getEmail(), credentialsDTO.getPassword());

        final Authentication authentication = this.authenticationManager.authenticate(token);

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        final HttpSession httpSession = request.getSession(true);

        httpSession.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext()
        );

        return ResponseEntity.ok().body(new AuthenticationResponseDTO(httpSession.getId()));

    }

    public ResponseEntity logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    public ResponseEntity info() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();
        User user = userRepository.findByEmail(currentEmail);
        if(user==null) {
                return ResponseEntity.badRequest().body(new MessageDTO("Nie znaleziono takie użytkownika"));
            }
        else {
            Inhabitant inhabitant = inhabitantRepository.findByEmail(currentEmail);
            if(inhabitant == null) {
                Worker worker = workerRepository.findByEmail(currentEmail);
                if(worker == null) {
                    Administrator administrator = administratorRepository.findByEmail(currentEmail);
                    HashMap<String,Object> adminToReturn = new HashMap<>();
                    adminToReturn.put("firstname",administrator.getFirstName());
                    adminToReturn.put("lastname",administrator.getLastName());
                    adminToReturn.put("email",administrator.getEmail());
                    adminToReturn.put("roles",administrator.getUserRoles());
                    adminToReturn.put("phoneNumber",administrator.getPhoneNumber());
                    adminToReturn.put("contactEmail",administrator.getContactEmail());

                    return ResponseEntity.ok().body(adminToReturn);
                }
                HashMap<String,Object> workerToReturn = new HashMap<>();
                workerToReturn.put("firstname",worker.getFirstName());
                workerToReturn.put("lastname",worker.getLastName());
                workerToReturn.put("email",worker.getEmail());
                workerToReturn.put("roles",worker.getUserRoles());
                workerToReturn.put("phoneNumber",worker.getPhoneNumber());
                workerToReturn.put("contactEmail",worker.getContactEmail());

                return ResponseEntity.ok().body(workerToReturn);

            }

            HashMap<String,Object> inhabitantToReturn = new HashMap<>();
            inhabitantToReturn.put("firstname",inhabitant.getFirstName());
            inhabitantToReturn.put("lastname",inhabitant.getLastName());
            inhabitantToReturn.put("email",inhabitant.getEmail());
            inhabitantToReturn.put("roles",inhabitant.getUserRoles());
            inhabitantToReturn.put("roomNumber",inhabitant.getRoom().getRoomNumber() + ", " + inhabitant.getRoom().getDormitoryNameEnum());
            inhabitantToReturn.put("dormitoryName",inhabitant.getRoom().getDormitoryNameEnum().name());

            return ResponseEntity.ok().body(inhabitantToReturn);
        }
    }

    public ResponseEntity changePassword(PasswordChangeDTO passwordChangeDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            User currentUser = userRepository.findByEmail(currentEmail);
            if (currentUser == null && passwordChangeDTO.getOldPassword().length()>0 && passwordChangeDTO.getNewPassword().length()>0) {
                return ResponseEntity.badRequest().build();
            }
            if( passwordEncoder.matches(passwordChangeDTO.getOldPassword(),currentUser.getPassword())) {
                currentUser.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
                userRepository.save(currentUser);
                return ResponseEntity.ok().body(new MessageDTO("Hasło zostało poprawnie zmienione"));
            }
            else {
                return ResponseEntity.badRequest().body(new MessageDTO("Obecne hasło nie jest poprawne"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
