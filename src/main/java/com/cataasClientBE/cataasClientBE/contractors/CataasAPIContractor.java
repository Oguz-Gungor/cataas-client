package com.cataasClientBE.cataasClientBE.contractors;

import com.cataasClientBE.cataasClientBE.services.util.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

@Component
public class CataasAPIContractor {

    private static final String BASE_PATH = "https://cataas.com/cat";

    private static final String TEXT_ENDPOINT_PATH = "/says/";

    private static final String WIDTH_PARAMETER_KEY = "width";

    private static final String HEIGHT_PARAMETER_KEY = "height";
    @Autowired
    private RequestService requestService;


    public byte[] getRandomCat(Map<String, Optional<?>> params) throws URISyntaxException, IOException {
        return get(BASE_PATH, params);
    }

    public byte[] getCatWithTag(String tag) throws URISyntaxException, IOException {
        return get(getCatWithTagURLBuilder(BASE_PATH, tag));
    }

    public byte[] getCatWithTagAndText(String tag, String text) throws URISyntaxException, IOException {
        String withTag = getCatWithTagURLBuilder(BASE_PATH, tag);
        return get(getCatWithTextURLBuilder(withTag, text));
    }

    public byte[] getCatWithText(String text) throws URISyntaxException, IOException {
        return get(getCatWithTextURLBuilder(BASE_PATH, text));
    }

    public byte[] getCatWithWidthAndHeight(Optional<Integer> width, Optional<Integer> height) throws URISyntaxException, IOException {
        return get(BASE_PATH, Map.of(WIDTH_PARAMETER_KEY, width, HEIGHT_PARAMETER_KEY, height));
    }

    //api utils
    private byte[] readResponse(ClientHttpResponse response) throws IOException {
        return response.getBody().readAllBytes();
    }

    private byte[] get(String url) throws URISyntaxException, IOException {
        return readResponse(requestService.get(url));
    }

    private byte[] get(String url, Map<?, Optional<?>> map) throws URISyntaxException, IOException {
        return readResponse(requestService.get(url, map));
    }

    //tag utils
    private String getCatWithTagURLBuilder(String url, String tag) {
        StringBuilder urlString = new StringBuilder();
        urlString.append(url);
        urlString.append(File.separator);
        urlString.append(tag);
        return urlString.toString();
    }

    private String getCatWithTextURLBuilder(String url, String text) {
        StringBuilder urlString = new StringBuilder();
        urlString.append(url);
        urlString.append(TEXT_ENDPOINT_PATH);
        urlString.append(text);
        return urlString.toString();
    }


}
