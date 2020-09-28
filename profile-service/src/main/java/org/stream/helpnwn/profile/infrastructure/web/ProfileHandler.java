package org.stream.helpnwn.profile.infrastructure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.stream.helpnwn.profile.domain.model.Profile;
import org.stream.helpnwn.profile.domain.ports.ProfileRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProfileHandler {

    private final ProfileRepository repository;

    @Autowired
    public ProfileHandler(ProfileRepository profileRepository) {
        this.repository = profileRepository;
    }

    public Mono<ServerResponse> findAll(ServerRequest serverRequest){
        Flux<Profile> profileFlux = repository.findAll();

        return ServerResponse.ok()
                .body(profileFlux, Profile.class);
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        String profileId = serverRequest.pathVariable("profileId");

        return repository.findById(profileId)
                .flatMap(profile -> ServerResponse.ok().bodyValue(profile))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        Mono<Profile> profileMono = serverRequest.bodyToMono(Profile.class)
                .flatMap(repository::save);

        return ServerResponse.status(HttpStatus.CREATED)
                .body(profileMono, Profile.class);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        String profileId = serverRequest.pathVariable("profileId");

        return repository.findById(profileId)
                .flatMap(profile -> {
                    Mono<Profile> updated = serverRequest.bodyToMono(Profile.class)
                            .flatMap(repository::save);
                    return ServerResponse.ok().body(updated, Profile.class);
                })
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteById(ServerRequest serverRequest) {
        String profileId = serverRequest.pathVariable("profileId");

        return repository.findById(profileId)
                .flatMap(profile -> {
                    repository.delete(profile.getProfileId());
                    return ServerResponse.ok().bodyValue(profile);
                })
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    private void Validate(Profile profile) {
        Errors errors = new BeanPropertyBindingResult(profile, "profile");

        
    }

}
