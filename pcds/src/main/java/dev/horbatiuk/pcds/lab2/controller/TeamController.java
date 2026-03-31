package dev.horbatiuk.pcds.lab2.controller;

import dev.horbatiuk.pcds.lab2.model.Team;
import dev.horbatiuk.pcds.lab2.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAll() {
        return teamService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable Long id) {
        Team team = teamService.getById(id);
        return team != null ? ResponseEntity.ok(team) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Team> create(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.create(team));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable Long id, @RequestBody Team team) {
        Team updated = teamService.update(id, team);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return teamService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
