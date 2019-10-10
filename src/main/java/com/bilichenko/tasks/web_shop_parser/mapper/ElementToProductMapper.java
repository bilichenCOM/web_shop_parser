package com.bilichenko.tasks.web_shop_parser.mapper;

import com.bilichenko.tasks.web_shop_parser.model.Product;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class ElementToProductMapper implements ElementMapper<Product> {

    private static final String NAME_ATTRIBUTE = "data-test-id";
    private static final String NAME_ATTRIBUTE_VALUE = "BrandName";
    private static final String BRAND_ATTRIBUTE = "data-test-id";
    private static final String BRAND_ATTRIBUTE_VALUE = "BrandName";
    private static final String COLOR_ATTRIBUTE = "data-test-id";
    private static final String COLOR_ATTRIBUTE_VALUE = "ColorBubble";
    private static final String PRICE_ATTRIBUTE = "data-test-id";
    private static final String PRICE_ATTRIBUTE_VALUE_1 = "FormattedSalePrice";
    private static final String PRICE_ATTRIBUTE_VALUE_2 = "ProductPriceFormattedBasePrice";

    @Override
    public Product mapElement(Element element) {
        Product product = new Product();
        product.setArticelId(element.attr("id"));
        product.setName(getTextOfFirstElementByAttributeValue(element, NAME_ATTRIBUTE, NAME_ATTRIBUTE_VALUE));
        product.setBrand(getTextOfFirstElementByAttributeValue(element, BRAND_ATTRIBUTE, BRAND_ATTRIBUTE_VALUE));
        product.setColors(getColorListFromElementByAttributeValue(element, COLOR_ATTRIBUTE, COLOR_ATTRIBUTE_VALUE));
        product.setPrice(getPriceFromElement(element));
        return product;
    }

    private List<String> getColorListFromElementByAttributeValue(Element element, String key, String value) {
        Elements colorElements = element.getElementsByAttributeValue(key, value);
        List<String> colorList = colorElements.eachAttr("color");

        return colorList;
    }

    private Double getPriceFromElement(Element element) {
        Double price;
        if ((price = getDoubleFromElementByAttributeValue(element, PRICE_ATTRIBUTE, PRICE_ATTRIBUTE_VALUE_1)) != null) {
            return price;
        }
        if ((price = getDoubleFromElementByAttributeValue(element, PRICE_ATTRIBUTE, PRICE_ATTRIBUTE_VALUE_2)) != null) {
            return price;
        }

        return null;
    }

    private Double getDoubleFromElementByAttributeValue(Element element, String key, String value) {
        Elements currentElement = element.getElementsByAttributeValue(key, value);
        Double doubleValue = null;

        if (currentElement.hasText()) {
            doubleValue = convertTextToDouble(currentElement.text());
        }
        return doubleValue;
    }

    private double convertTextToDouble(String text) {
        String filteredText = text.replaceAll("[^\\d,.]", "").replace(',', '.');
        double doubleValue = Double.valueOf(filteredText);
        return doubleValue;
    }

    private String getTextOfFirstElementByAttributeValue(Element element, String key, String value) {
        String text = element.getElementsByAttributeValue(key, value).first().text();

        return text;
    }
}
