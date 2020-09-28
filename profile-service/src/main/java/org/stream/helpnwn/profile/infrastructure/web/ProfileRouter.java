package org.stream.helpnwn.profile.infrastructure.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProfileRouter {

    @Bean
    public RouterFunction<ServerResponse> route(ProfileHandler profileHandler) {
        return RouterFunctions.route()
                .path("/profile", builder -> builder
                        .GET("/",profileHandler::findAll)
                        .GET("/{profileId}", profileHandler::findById)
                        .POST("/", profileHandler::save)
                        .PUT("/{profileId}", profileHandler::update)
                        .DELETE("/{profileId}", profileHandler::deleteById))
                .build();
    }

}
