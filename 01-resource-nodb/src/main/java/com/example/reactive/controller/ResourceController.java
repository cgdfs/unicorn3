package com.example.reactive.controller;

import com.example.reactive.model.Resource;
import com.example.reactive.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ResourceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @GetMapping("/resources/{id}")
    public Mono<Resource> getResource(@PathVariable Long id) {
        LOGGER.info("getResource: /resources/{}", id);
        return Mono.just(ResourceUtil.makeResource(id));
    }
}