package dev.horbatiuk.pcds.lab2.controller;

import dev.horbatiuk.pcds.lab2.model.Quest;
import dev.horbatiuk.pcds.lab2.service.QuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quests")
public class QuestController {

    private final QuestService questService;

    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping
    public List<Quest> getAll() {
        return questService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quest> getById(@PathVariable Long id) {
        Quest quest = questService.getById(id);
        return quest != null ? ResponseEntity.ok(quest) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Quest> create(@RequestBody Quest quest) {
        return ResponseEntity.ok(questService.create(quest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quest> update(@PathVariable Long id, @RequestBody Quest quest) {
        Quest updated = questService.update(id, quest);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return questService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
