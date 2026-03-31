package dev.horbatiuk.pcds.lab2.service;

import dev.horbatiuk.pcds.lab2.model.Quest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class QuestService {
    private final Map<Long, Quest> quests = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Quest> getAll() {
        return new ArrayList<>(quests.values());
    }

    public Quest getById(Long id) {
        return quests.get(id);
    }

    public Quest create(Quest quest) {
        Long id = counter.getAndIncrement();
        quest.setId(id);
        quests.put(id, quest);
        return quest;
    }

    public Quest update(Long id, Quest updatedQuest) {
        if (!quests.containsKey(id)) {
            return null;
        }
        updatedQuest.setId(id);
        quests.put(id, updatedQuest);
        return updatedQuest;
    }

    public boolean delete(Long id) {
        return quests.remove(id) != null;
    }

    public boolean exists(Long id) {
        return quests.containsKey(id);
    }
}