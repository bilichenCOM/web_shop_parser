package com.bilichenko.tasks.web_shop_parser;

import com.bilichenko.tasks.web_shop_parser.mapper.ElementMapper;
import com.bilichenko.tasks.web_shop_parser.mapper.ElementToProductMapper;
import com.bilichenko.tasks.web_shop_parser.mapper.JsonNodeMapper;
import com.bilichenko.tasks.web_shop_parser.mapper.JsonNodeToProductMapper;
import com.bilichenko.tasks.web_shop_parser.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebShopParser {

    private static final String TARGET_URL = "https://www.aboutyou.de/maenner/bekleidung";
    private static final String TARGET_API_URL = "https://api-cloud.aboutyou.de/v1/products";

    private static final String OUTPUT_FILE_BROWSER = "products1.json";
    private static final String OUTPUT_FILE_API = "products2.json";

    private static ElementMapper<Product> elementMapper = new ElementToProductMapper();
    private static JsonNodeMapper<Product> jsonNodeMapper = new JsonNodeToProductMapper();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        getProductsFromBrowser();
        getProductsFromApi();
    }

    private static void getProductsFromApi() throws IOException {
        JsonNode responseJson = JacksonHelper.readProductsNode(TARGET_API_URL);
        JsonNode entities = responseJson.get("entities");
        List<Product> productList = new ArrayList<>();
        entities.elements()
                .forEachRemaining(jsonNode -> productList.add(jsonNodeMapper.map(jsonNode)));
        objectMapper.writeValue(new File(OUTPUT_FILE_API), productList);
    }

    private static void getProductsFromBrowser() throws IOException {
        Document document = JsoupHelper.getDocument(TARGET_URL);
        System.out.printf("Request number: %d;\r\n", 1);

        Elements elements = document.getElementsByAttributeValue("data-test-id", "ProductTile");

        List<Product> productList = elements.stream()
                .map(element -> elementMapper.mapElement(element))
                .collect(Collectors.toList());
        System.out.printf("Total number of products parsed: %d;\r\n", productList.size());

        objectMapper.writeValue(new File(OUTPUT_FILE_BROWSER), productList);
    }
}
