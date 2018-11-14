package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.model.Message;
import pl.dormitorymaintenancesystem.model.users.Employee;
import pl.dormitorymaintenancesystem.repositories.EmployeeRepository;
import pl.dormitorymaintenancesystem.repositories.MessageRepository;
import pl.dormitorymaintenancesystem.utils.Page;
import pl.dormitorymaintenancesystem.utils.dataInput.NewAnnouncement;
import pl.dormitorymaintenancesystem.utils.dataOutput.AnnouncementDTO;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "api/announcement")
@Transactional
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity getAllAnnouncements(@RequestParam int page, @RequestParam int size){

        if(size==0)
            return ResponseEntity.badRequest().build();

        List<Message> messageList = messageRepository.findAll();

        List<AnnouncementDTO> announcementDTOList = new ArrayList<>();


        for(Message message : messageList) {
            AnnouncementDTO announcementDTO = new AnnouncementDTO(message.getTitle(), message.getContent(), message.getTimeStamp(), message.getSender().getEmail(), message.getSender().getFirstName(), message.getSender().getLastName());
            announcementDTOList.add(announcementDTO);
        }

        Collections.sort(announcementDTOList);

        return ResponseEntity.ok().body(Page.createPage(page,size,announcementDTOList));
    }

    @PostMapping
    public ResponseEntity addAnnouncement(@RequestBody NewAnnouncement newAnnouncement) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Employee employee = employeeRepository.findByEmail(currentEmail);
            if(employee == null)
                return ResponseEntity.badRequest().build();
            if(newAnnouncement.getTitle().equals("") || newAnnouncement.getContent().equals(""))
                return ResponseEntity.badRequest().build();
            Message message = new Message(newAnnouncement.getTitle(),newAnnouncement.getContent(),employee);
            messageRepository.save(message);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/getAllBySender")
    public ResponseEntity getAllAnnouncementsBySender(@RequestParam int page, @RequestParam int size){
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

}
