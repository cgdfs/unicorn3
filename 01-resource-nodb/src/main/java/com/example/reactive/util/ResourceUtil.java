package com.example.reactive.util;


import com.example.reactive.model.MappedResponse;
import com.example.reactive.model.Resource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.lang.Thread.sleep;

public class ResourceUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceUtil.class);

    public static Resource makeResource(Long id) {
        sleepForRandomTime(id);
        return new Resource(id, valueOf(id), format("/resources/%s", id), id * 10);
    }

    private static void sleepForRandomTime(Long id) {
        try {
            seeIfExceptionalCase(id);
            sleep(2000);
            LOGGER.info("Resource {} processed", id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void seeIfExceptionalCase(Long id) {
        if(id%19==0)
            throw new RuntimeException("Excptional case: " + id);
    }

    public static Resource mapToResource(JsonNode jsonNode) {
        try {
            Long id = (new Random()).nextInt();
            String name = jsonNode.rget("name").asText();
            String path = "";
            Long size = 0L;
            return new Resource(id, name, path, size);
        } catch (Exception e) {
            return null;
        }
    }

    public static MappedResponse saveResource(Resource resource) {
        sleepForRandomTime(resource.getId());
        return new MappedResponse(resource.getId(),"processed");
    }
}