package com.bilichenko.tasks.web_shop_parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupHelper {

    public static Document getDocument(String targetUrl) {
        Document document = null;
        try {
            document = Jsoup.connect(targetUrl).get();
        } catch (IOException e) {
            System.err.println("Cannot get document with url: " + targetUrl);
            System.err.println("Reason: " + e.getMessage());
        }
        return document;
    }
}
