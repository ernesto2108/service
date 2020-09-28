package org.stream.helpnwn.profile.application;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.stream.helpnwn.profile.domain.model.Profile;

@Repository
public interface ProfilePostgresRepository extends ReactiveCrudRepository<Profile, String> {
}
