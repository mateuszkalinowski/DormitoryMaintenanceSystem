package pl.dormitorymaintenancesystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.service.MessageService;
import pl.dormitorymaintenancesystem.utils.dataInput.NewAnnouncement;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "api/announcement")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity getAllAnnouncements(@RequestParam int page, @RequestParam int size){
        return messageService.getAllAnnouncements(page,size);
    }

    @PostMapping
    public ResponseEntity addAnnouncement(@RequestBody NewAnnouncement newAnnouncement) {
        return messageService.addAnnouncement(newAnnouncement);
    }

    @GetMapping(value = "/getAllBySender")
    public ResponseEntity getAllAnnouncementsBySender(@RequestParam int page, @RequestParam int size){
        return messageService.getAllAnnouncementsBySender(page,size);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity removeAnnouncementById(@PathVariable Long id) {
        return messageService.removeAnnouncementById(id);
    }

    @DeleteMapping(value = "/asAdmin/{id}")
    public ResponseEntity removeAnnouncementByIdAsAdmin(@PathVariable Long id) {
        return messageService.removeAnnouncementByIdAsAdmin(id);
    }

}
