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

    @PostMapping(path = "/resources", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MappedResponse> saveResources(@RequestBody JsonNode requestBody) {
        LOGGER.info("saveResources: /resources: {} events to process", requestBody.size());
        return Flux.fromStream(StreamSupport.stream(requestBody.spliterator(), false))
                .map(ResourceUtil::mapToResource)
                .map(ResourceUtil::saveResource);
    }


    @GetMapping("/resources/{id}")
    public Mono<Resource> getResource(@PathVariable Long id) {
        LOGGER.info("getResource: /resources/{}", id);
        return Mono.just(ResourceUtil.makeResource(id));
    }

    @GetMapping(path = "/resources", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Resource> getResources() {
        LOGGER.info("getResources: /resources");
        return getAllResources();
    }

    @GetMapping(path = "/resourcesNonReactive")
    public Flux<Resource> getResourcesNotReactive() {
        LOGGER.info("getResourcesNotReactive: /resourcesNonReactive");
        return getAllResources();
    }

    private Flux<Resource> getAllResources() {
        int noOfResources = getNoOfResources();
        LOGGER.info("Returning {} resources", noOfResources);
        return Flux.range(1, noOfResources)
                .map(Long::new)
                .map(ResourceUtil::makeResource);
    }

    private int getNoOfResources() {
        return new Random().nextInt(25)+5;
    }
}