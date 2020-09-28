package org.stream.helpnwn.profile.infrastructure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stream.helpnwn.profile.domain.model.Profile;
import org.stream.helpnwn.profile.domain.ports.ProfileRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    private final ProfileRepository repository;

    @Autowired
    public ProfileController(ProfileRepository profileRepository) {
        this.repository = profileRepository;
    }

    @GetMapping(path = "/")
    public Flux<Profile> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{profileId}")
    public Mono<ResponseEntity<Profile>> findById(@PathVariable(name = "profileId") String profileId) {
        return repository.findById(profileId)
                .map(profile -> ResponseEntity.ok(profile))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/")
    public Mono<Profile> save(@RequestBody Profile profile) {
        return repository.save(profile);
    }

    @PutMapping(path = "/{profileId}")
    public Mono<ResponseEntity<Profile>> update(@PathVariable(value = "profileId") String profileId,
                                                @RequestBody Profile profile) {
        return repository.findById(profileId)
                .flatMap(saveProfile -> {
                    saveProfile.setName(profile.getName());
                    return repository.save(saveProfile);
                })
                .map(updateProfile -> new ResponseEntity<>(updateProfile, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{profileId}")
    public Mono<ResponseEntity<Profile>> deleteById(@PathVariable(value = "profileId") String profileId){
        return repository.findById(profileId)
                .flatMap(profile -> repository.delete(profileId)
                .thenReturn(ResponseEntity.ok(profile)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
