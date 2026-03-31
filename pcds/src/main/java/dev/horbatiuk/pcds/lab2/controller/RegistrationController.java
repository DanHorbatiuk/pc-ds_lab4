package dev.horbatiuk.pcds.lab2.controller;

import dev.horbatiuk.pcds.lab2.model.Registration;
import dev.horbatiuk.pcds.lab2.service.QuestService;
import dev.horbatiuk.pcds.lab2.service.RegistrationService;
import dev.horbatiuk.pcds.lab2.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final QuestService questService;
    private final TeamService teamService;

    public RegistrationController(RegistrationService registrationService,
                                  QuestService questService,
                                  TeamService teamService) {
        this.registrationService = registrationService;
        this.questService = questService;
        this.teamService = teamService;
    }

    @GetMapping
    public List<Registration> getAll() {
        return registrationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registration> getById(@PathVariable Long id) {
        Registration registration = registrationService.getById(id);
        return registration != null ? ResponseEntity.ok(registration) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Registration registration) {
        if (!questService.exists(registration.getQuestId())) {
            return ResponseEntity.badRequest().body("Quest with given id does not exist");
        }
        if (!teamService.exists(registration.getTeamId())) {
            return ResponseEntity.badRequest().body("Team with given id does not exist");
        }
        return ResponseEntity.ok(registrationService.create(registration));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Registration registration) {
        if (!questService.exists(registration.getQuestId())) {
            return ResponseEntity.badRequest().body("Quest with given id does not exist");
        }
        if (!teamService.exists(registration.getTeamId())) {
            return ResponseEntity.badRequest().body("Team with given id does not exist");
        }

        Registration updated = registrationService.update(id, registration);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return registrationService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}