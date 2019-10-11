package com.bilichenko.tasks.web_shop_parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class JacksonHelper {

    private static final int TARGET_PAGE_NUMBER = 1;
    private static final String TARGET_CATEGORY = "20290";

    private static final String ATTRIBUTES = "with=attributes:key(brand|name|plusSize|sustainability|colorDetail),categories,priceRange&shopId=139&perPage=20";
    private static final String FILTERS = "filters[category]=";
    private static final String PAGE = "page=";

    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode readProductsNode(String targetApiUrl) {
        String url = buildUrlString(targetApiUrl);
        String responseString = connect(url);
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(responseString);
        } catch (JsonProcessingException e) {
            System.err.println("Cannot read input json form string: " + responseString);
        }
        return jsonNode;
    }

    private static String connect(String url) {
        StringBuilder response = null;
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            //TODO: implement properly - currently no response can be readed
            reader.lines().forEach(line -> response.append(line));
        } catch (IOException e) {
            System.err.println("cannot connect and read response!");
        }
        return response.toString();
    }

    private static String buildUrlString(String targetApiUrl) {
        StringBuilder urlStringBuilder = new StringBuilder(targetApiUrl);
        urlStringBuilder.append("?");
        urlStringBuilder.append(encode(ATTRIBUTES));
        urlStringBuilder.append("&");
        urlStringBuilder.append(encode(FILTERS + TARGET_CATEGORY));
        urlStringBuilder.append("&");
        urlStringBuilder.append(encode(PAGE + TARGET_PAGE_NUMBER));

        return urlStringBuilder.toString();
    }

    private static String encode(String stringToEncode) {
        String encodedString = null;
        try {
            encodedString = URLEncoder.encode(stringToEncode, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {}

        return encodedString;
    }
}
