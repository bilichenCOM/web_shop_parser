package com.bilichenko.tasks.web_shop_parser.mapper;

import org.jsoup.nodes.Element;

public interface ElementMapper<T> {

    T mapElement(Element element);
}
