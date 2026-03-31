package dev.horbatiuk.pcds.lab2.service;

import dev.horbatiuk.pcds.lab2.model.Registration;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RegistrationService {
    private final Map<Long, Registration> registrations = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Registration> getAll() {
        return new ArrayList<>(registrations.values());
    }

    public Registration getById(Long id) {
        return registrations.get(id);
    }

    public Registration create(Registration registration) {
        Long id = counter.getAndIncrement();
        registration.setId(id);
        registrations.put(id, registration);
        return registration;
    }

    public Registration update(Long id, Registration updatedRegistration) {
        if (!registrations.containsKey(id)) {
            return null;
        }
        updatedRegistration.setId(id);
        registrations.put(id, updatedRegistration);
        return updatedRegistration;
    }

    public boolean delete(Long id) {
        return registrations.remove(id) != null;
    }
}