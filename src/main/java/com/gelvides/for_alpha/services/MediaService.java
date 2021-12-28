package com.gelvides.for_alpha.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gelvides.for_alpha.entity.Media;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class MediaService {

    @SneakyThrows
    public Media createMedia(String json){
        var urlMedia = getMediaElement(json);
        if(urlMedia == null && urlMedia.equals(""))
            throw new Exception("Media not found");
        return new Media(urlMedia);
    }

    private String getMediaElement(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(json);
            return "https://i.giphy.com/" + jsonNode.get("data").get("id").asText().concat(".gif");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
