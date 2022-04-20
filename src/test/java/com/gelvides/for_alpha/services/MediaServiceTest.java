package com.gelvides.for_alpha.services;

import com.gelvides.for_alpha.ForAlphaApplicationTests;
import com.gelvides.for_alpha.feign.MediaRestClient;
import com.gelvides.for_alpha.servicemethod.Json;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MediaServiceTest extends ForAlphaApplicationTests {

    @Value("${spring.request.giphy.rich-json-file-for-test}")
    private String richJson;
    @Value("${spring.request.giphy.broke-json-file-for-test}")
    private String brokeJson;

    @MockBean
    MediaRestClient mediaRestClient;

    @InjectMocks
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
