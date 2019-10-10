package com.bilichenko.tasks.web_shop_parser.mapper;

import com.bilichenko.tasks.web_shop_parser.model.Product;
import org.jsoup.nodes.Element;

public class ElementToProductMapper implements ElementMapper<Product> {

    @Override
    public Product mapElement(Element element) {
        Product product = new Product();
        //TODO: implement mapping of product
        return product;
    }
}
