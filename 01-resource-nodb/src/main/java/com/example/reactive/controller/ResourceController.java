package com.example.reactive.controller;

import com.example.reactive.model.MappedResponse;
import com.example.reactive.model.Resource;
import com.example.reactive.util.ResourceUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.stream.StreamSupport;

@RestController
public class ResourceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @GetMapping("/resources/{id}")
    public Mono<Resource> getResource(@PathVariable Long id) {
        LOGGER.info("getResource: /resources/{}", id);
        return Mono.just(ResourceUtil.makeResource(id));
    }
}