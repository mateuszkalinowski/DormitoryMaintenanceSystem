package pl.dormitorymaintenancesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.dormitorymaintenancesystem.model.Announcement;
import pl.dormitorymaintenancesystem.model.users.Administrator;
import pl.dormitorymaintenancesystem.model.users.Employee;
import pl.dormitorymaintenancesystem.repositories.AdministratorRepository;
import pl.dormitorymaintenancesystem.repositories.EmployeeRepository;
import pl.dormitorymaintenancesystem.repositories.AnnouncementRepository;
import pl.dormitorymaintenancesystem.utils.Page;
import pl.dormitorymaintenancesystem.utils.dataInput.NewAnnouncementDTO;
import pl.dormitorymaintenancesystem.utils.dataOutput.AnnouncementDTO;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    public ResponseEntity getAllAnnouncements(int page,int size){

        if(size==0)
            return ResponseEntity.badRequest().build();

        List<Announcement> announcementList = announcementRepository.findAll();

        List<AnnouncementDTO> announcementDTOList = new ArrayList<>();


        for(Announcement announcement : announcementList) {
            AnnouncementDTO announcementDTO = new AnnouncementDTO(announcement.getId().toString(), announcement.getTitle(), announcement.getContent(), announcement.getTimeStamp(), announcement.getSender().getEmail(), announcement.getSender().getFirstName(), announcement.getSender().getLastName());
            announcementDTOList.add(announcementDTO);
        }

        Collections.sort(announcementDTOList);

        return ResponseEntity.ok().body(Page.createPage(page,size,announcementDTOList));
    }

    public ResponseEntity addAnnouncement(NewAnnouncementDTO newAnnouncementDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Employee employee = employeeRepository.findByEmail(currentEmail);
            if(employee == null)
                return ResponseEntity.badRequest().build();
            if(newAnnouncementDTO.getTitle().equals("") || newAnnouncementDTO.getContent().equals(""))
                return ResponseEntity.badRequest().build();
            Announcement announcement = new Announcement(newAnnouncementDTO.getTitle(), newAnnouncementDTO.getContent(),employee);
            announcementRepository.save(announcement);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity getAllAnnouncementsBySender(int page, int size){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Employee employee = employeeRepository.findByEmail(currentEmail);
            if(employee == null)
                return ResponseEntity.badRequest().build();


            List<HashMap<String,Object>> announcementsList = new ArrayList<>();

            List<Announcement> announcements = employee.getAnnouncementList();
            announcements.sort((o1, o2) -> o2.getTimeStamp().compareTo(o1.getTimeStamp()));

            for(Announcement announcement : announcements) {
                HashMap<String,Object> element = new HashMap<>();
                element.put("id", announcement.getId());
                element.put("title", announcement.getTitle());
                element.put("content", announcement.getContent());
                element.put("timeStamp", announcement.getTimeStamp().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
                announcementsList.add(element);
            }

            return ResponseEntity.ok().body(Page.createPage(page,size,announcementsList));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity removeAnnouncementById(Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Employee employee = employeeRepository.findByEmail(currentEmail);
            if(employee == null)
                return ResponseEntity.badRequest().build();

            boolean deleted = false;

            for(int i = employee.getAnnouncementList().size()-1; i >= 0; i--) {
                if(employee.getAnnouncementList().get(i).getId().equals(id)) {
                    employee.getAnnouncementList().remove(i);
                    deleted = true;
                    break;
                }
            }

            if(deleted) {
                employeeRepository.save(employee);
                announcementRepository.deleteById(id);

                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.badRequest().build();
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity removeAnnouncementByIdAsAdmin(Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Administrator administrator = administratorRepository.findByEmail(currentEmail);
            if(administrator == null)
                return ResponseEntity.badRequest().build();


                announcementRepository.deleteById(id);

                return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
