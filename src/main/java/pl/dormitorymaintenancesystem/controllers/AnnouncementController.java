package pl.dormitorymaintenancesystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.service.AnnouncementService;
import pl.dormitorymaintenancesystem.utils.dataInput.NewAnnouncementDTO;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "api/announcement")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    public ResponseEntity getAllAnnouncements(@RequestParam int page, @RequestParam int size){
        return announcementService.getAllAnnouncements(page,size);
    }

    @PostMapping
    public ResponseEntity addAnnouncement(@RequestBody NewAnnouncementDTO newAnnouncementDTO) {
        return announcementService.addAnnouncement(newAnnouncementDTO);
    }

    @GetMapping(value = "/getAllBySender")
    public ResponseEntity getAllAnnouncementsBySender(@RequestParam int page, @RequestParam int size){
        return announcementService.getAllAnnouncementsBySender(page,size);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity removeAnnouncementById(@PathVariable Long id) {
        return announcementService.removeAnnouncementById(id);
    }

    @DeleteMapping(value = "/asAdmin/{id}")
    public ResponseEntity removeAnnouncementByIdAsAdmin(@PathVariable Long id) {
        return announcementService.removeAnnouncementByIdAsAdmin(id);
    }

}
