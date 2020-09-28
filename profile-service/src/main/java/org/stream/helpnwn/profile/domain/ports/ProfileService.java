package org.stream.helpnwn.profile.domain.ports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.stream.helpnwn.profile.application.ProfilePostgresRepository;
import org.stream.helpnwn.profile.domain.model.Profile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProfileService implements ProfileRepository {

    private final ProfilePostgresRepository repository;

    @Autowired
    public ProfileService(ProfilePostgresRepository profileRepository) {
        this.repository = profileRepository;
    }

    @Override
    @Transactional
    public Flux<Profile> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Mono<Profile> findById(String profileId) {
        return repository.findById(profileId);
    }

    @Override
    @Transactional
    public Mono<Profile> save(Profile profile) {
        return repository.save(profile);
    }

    @Override
    @Transactional
    public Mono<Profile> delete(String profileId) {
        return repository.findById(profileId)
                .flatMap(profile -> repository.deleteById(profile.getProfileId())
                .thenReturn(profile));
    }

}
