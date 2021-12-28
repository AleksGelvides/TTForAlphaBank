package com.gelvides.for_alpha.services;
import com.gelvides.for_alpha.controllers.MediaRestClient;
import com.gelvides.for_alpha.servicemethod.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class MediaServiceTest {
    @Value("${spring.request.giphy.rich-json-file-for-test}")
    private String richJson;
    @Value("${spring.request.giphy.broke-json-file-for-test}")
    private String brokeJson;

    @MockBean
    MediaRestClient mediaRestClient;
    @Autowired
    MediaService mediaService;



    @Test
    public void newCreatePositiveMedia(){
        when(mediaRestClient.getPositiveMedia()).thenReturn(Json.getJSONString(richJson));

        var urlMedia = "https://i.giphy.com/RNWCDUDZiK49C8n1J2.gif";
        var json = mediaRestClient.getPositiveMedia();
        var media = mediaService.createMedia(json);

        assertNotNull(media);
        assertEquals(media.uri(), urlMedia);

        verify(mediaRestClient, times(1)).getPositiveMedia();
    }

    @Test
    public void newCreateNegativeMedia(){
        when(mediaRestClient.getPositiveMedia()).thenReturn(Json.getJSONString(brokeJson));

        var urlMedia = "https://i.giphy.com/UU108BDSM6VWrzPHho.gif";
        var json = mediaRestClient.getPositiveMedia();
        var media = mediaService.createMedia(json);

        assertNotNull(media);
        assertEquals(media.uri(), urlMedia);

        verify(mediaRestClient, times(1)).getPositiveMedia();
    }
}
