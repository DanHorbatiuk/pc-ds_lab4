package dev.horbatiuk.pcds.lab3.service;

import dev.horbatiuk.pcds.lab3.model.ArchiveRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ArchiveService {
    private final Map<Long, ArchiveRecord> archive = new LinkedHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<ArchiveRecord> getAll() {
        return new ArrayList<>(archive.values());
    }

    public ArchiveRecord getById(Long id) {
        return archive.get(id);
    }

    public ArchiveRecord create(ArchiveRecord record) {
        long id = counter.getAndIncrement();
        record.setId(id);
        archive.put(id, record);
        return record;
    }

    public ArchiveRecord update(Long id, ArchiveRecord record) {
        if (!archive.containsKey(id)) {
            return null;
        }
        record.setId(id);
        archive.put(id, record);
        return record;
    }

    public boolean delete(Long id) {
        return archive.remove(id) != null;
    }
}