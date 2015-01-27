package com.shartfinder.encounter.query.repository.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.shartfinder.encounter.query.repository.EncounterRepository;
import com.shartfinder.web.model.EncounterViewModel;

@Repository
public class InMemoryEncounterRepository implements EncounterRepository {

    private final Map<UUID, EncounterViewModel> idToEncounterDTOMap;

    public InMemoryEncounterRepository() {
        idToEncounterDTOMap = new ConcurrentHashMap<>();
    }

    @Override
    public EncounterViewModel fetchById(UUID encounterId) {
        return idToEncounterDTOMap.get(encounterId);
    }

    @Override
    public void save(EncounterViewModel encounterViewModel) {
        idToEncounterDTOMap.put(encounterViewModel.getEncounterId(), encounterViewModel);
    }
}
