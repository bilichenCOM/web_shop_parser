package com.bilichenko.tasks.web_shop_parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebShopParser {

    private static final String TARGET_URL = "https://www.aboutyou.de/maenner/bekleidung";
    private static final String TARGET_CLASS_NAME = "";

    public static void main(String[] args) {
        Document document = JsoupHelper.getDocument(TARGET_URL);

        Elements elements = document.getElementsByClass(TARGET_CLASS_NAME);

        //TODO: implement mapping elements to product list
        //TODO: implement writing list of products to file
    }
}
