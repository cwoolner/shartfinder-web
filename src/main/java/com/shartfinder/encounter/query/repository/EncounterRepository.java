package com.shartfinder.encounter.query.repository;

import java.util.UUID;

import com.shartfinder.web.model.EncounterViewModel;

public interface EncounterRepository {

    EncounterViewModel fetchById(UUID encounterId);

    void save(EncounterViewModel encounterViewModel);

}
