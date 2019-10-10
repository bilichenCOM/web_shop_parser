package com.bilichenko.tasks.web_shop_parser;

import com.bilichenko.tasks.web_shop_parser.mapper.ElementMapper;
import com.bilichenko.tasks.web_shop_parser.mapper.ElementToProductMapper;
import com.bilichenko.tasks.web_shop_parser.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class WebShopParser {

    private static final String TARGET_URL = "https://www.aboutyou.de/maenner/bekleidung";
    private static final String ATTRIBUTE = "data-test-id";
    private static final String ATTRIBUTE_VALUE = "ProductTile";

    private static final String OUTPUT_FILE = "products.json";

    private static ElementMapper<Product> elementMapper = new ElementToProductMapper();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Document document = JsoupHelper.getDocument(TARGET_URL);
        System.out.printf("Request number: %d;\r\n", 1);

        Elements elements = document.getElementsByAttributeValue(ATTRIBUTE, ATTRIBUTE_VALUE);

        List<Product> productList = elements.stream()
                .map(element -> elementMapper.mapElement(element))
                .collect(Collectors.toList());
        System.out.printf("Total number of products parsed: %d;\r\n", productList.size());

        objectMapper.writeValue(new File(OUTPUT_FILE), productList);
    }
}
