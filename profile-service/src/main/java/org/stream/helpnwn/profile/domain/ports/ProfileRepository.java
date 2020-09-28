package org.stream.helpnwn.profile.domain.ports;

import org.springframework.stereotype.Service;
import org.stream.helpnwn.profile.domain.model.Profile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ProfileRepository {

    Flux<Profile> findAll();

    Mono<Profile> findById(String profileId);

    Mono<Profile> save(Profile profile);

    Mono<Profile> delete(String profileId);

}
