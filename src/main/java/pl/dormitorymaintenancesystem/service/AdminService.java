package pl.dormitorymaintenancesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.dormitorymaintenancesystem.model.users.Administrator;
import pl.dormitorymaintenancesystem.repositories.AdministratorRepository;
import pl.dormitorymaintenancesystem.utils.dataInput.AdminInfoDTO;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public ResponseEntity updateAdminInfo(AdminInfoDTO adminInfoDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Administrator admin = administratorRepository.findByEmail(currentEmail);
            if (admin == null)
                return ResponseEntity.badRequest().build();

            if(adminInfoDTO.getContactEmail().length()<255 && adminInfoDTO.getPhone().length()<255 && adminInfoDTO.getFirstname().length()<32 && adminInfoDTO.getLastname().length()<64) {
                admin.setContactEmail(adminInfoDTO.getContactEmail());
                admin.setPhoneNumber(adminInfoDTO.getPhone());
                admin.setFirstName(adminInfoDTO.getFirstname());
                admin.setLastName(adminInfoDTO.getLastname());
                administratorRepository.save(admin);
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
