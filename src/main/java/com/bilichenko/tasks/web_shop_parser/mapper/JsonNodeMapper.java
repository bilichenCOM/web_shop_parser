package com.bilichenko.tasks.web_shop_parser.mapper;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonNodeMapper<T> {
    T map(JsonNode jsonNode);
}
