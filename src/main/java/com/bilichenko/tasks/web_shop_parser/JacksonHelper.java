package com.bilichenko.tasks.web_shop_parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonHelper {

    private static final int TARGET_PAGE_NUMBER = 1;
    private static final String TARGET_CATEGORY = "20290";

    private static final String ATTRIBUTES = "with=attributes:key(brand|name|plusSize|sustainability|colorDetail),categories,priceRange&shopId=139";
    private static final String FILTERS = "filters[category]=";
    private static final String PAGE = "page=";

    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode readProductsNode(String targetApiUrl) {
        String url = buildUrlString(targetApiUrl);
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(url);
        } catch (JsonProcessingException e) {
            System.err.println("problems with parsing json from: " + targetApiUrl);
            System.out.println("url: " + url);
        }
        return jsonNode;
    }

    private static String buildUrlString(String targetApiUrl) {
        StringBuilder urlStringBuilder = new StringBuilder(targetApiUrl);
        urlStringBuilder.append("?");
        urlStringBuilder.append(ATTRIBUTES);
        urlStringBuilder.append("&");
        urlStringBuilder.append(FILTERS + TARGET_CATEGORY);
        urlStringBuilder.append("&");
        urlStringBuilder.append(PAGE + TARGET_PAGE_NUMBER);

        return urlStringBuilder.toString();
    }
}
