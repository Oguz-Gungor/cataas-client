package com.cataasClientBE.cataasClientBE.services.util;


import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@Service
public class RequestService extends RestTemplate {
    public ClientHttpResponse get(String url) throws URISyntaxException, IOException {
        return this.getRequestFactory().createRequest(new URI(url), HttpMethod.GET).execute();
    }

    public ClientHttpResponse get(String url, Map<?, Optional<?>> map) throws URISyntaxException, IOException {
        String requestUrl = url +
                urlEncodeUTF8(map);
        return get(requestUrl);
    }

    private String urlEncodeUTF8(Map<?, Optional<?>> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Optional<?> value;
            if (!(entry.getValue() instanceof Optional<?>)) {
                value = Optional.ofNullable(entry.getValue());
            } else {
                value = (Optional<?>) entry.getValue();
            }
            if (value.isPresent()) {
                if (!sb.isEmpty()) {
                    sb.append("&");
                }
                sb.append(String.format("%s=%s",
                        urlEncodeUTF8(entry.getKey().toString()),
                        urlEncodeUTF8(value.get().toString())
                ));
            }
        }
        if (!sb.isEmpty()) {
            sb.insert(0, "?");
        }
        return sb.toString();
    }

    private String urlEncodeUTF8(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }
}
