package pl.dormitorymaintenancesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.dormitorymaintenancesystem.model.Message;
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

        List<Message> messageList = announcementRepository.findAll();

        List<AnnouncementDTO> announcementDTOList = new ArrayList<>();


        for(Message message : messageList) {
            AnnouncementDTO announcementDTO = new AnnouncementDTO(message.getId().toString(),message.getTitle(), message.getContent(), message.getTimeStamp(), message.getSender().getEmail(), message.getSender().getFirstName(), message.getSender().getLastName());
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
            Message message = new Message(newAnnouncementDTO.getTitle(), newAnnouncementDTO.getContent(),employee);
            announcementRepository.save(message);
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

            List<Message> messages = employee.getMessageList();
            messages.sort((o1, o2) -> o2.getTimeStamp().compareTo(o1.getTimeStamp()));

            for(Message message : messages) {
                HashMap<String,Object> element = new HashMap<>();
                element.put("id",message.getId());
                element.put("title",message.getTitle());
                element.put("content",message.getContent());
                element.put("timeStamp", message.getTimeStamp().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
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

            for(int i = employee.getMessageList().size()-1; i >= 0; i--) {
                if(employee.getMessageList().get(i).getId().equals(id)) {
                    employee.getMessageList().remove(i);
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
