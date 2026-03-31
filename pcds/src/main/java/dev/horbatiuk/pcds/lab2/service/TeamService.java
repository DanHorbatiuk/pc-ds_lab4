package dev.horbatiuk.pcds.lab2.service;

import dev.horbatiuk.pcds.lab2.model.Team;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TeamService {
    private final Map<Long, Team> teams = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Team> getAll() {
        return new ArrayList<>(teams.values());
    }

    public Team getById(Long id) {
        return teams.get(id);
    }

    public Team create(Team team) {
        Long id = counter.getAndIncrement();
        team.setId(id);
        teams.put(id, team);
        return team;
    }

    public Team update(Long id, Team updatedTeam) {
        if (!teams.containsKey(id)) {
            return null;
        }
        updatedTeam.setId(id);
        teams.put(id, updatedTeam);
        return updatedTeam;
    }

    public boolean delete(Long id) {
        return teams.remove(id) != null;
    }

    public boolean exists(Long id) {
        return teams.containsKey(id);
    }
}