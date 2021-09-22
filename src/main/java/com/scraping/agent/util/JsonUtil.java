package com.scraping.agent.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

    public JSONObject jsonParser(String message) throws ParseException{
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(message);
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }

    public String getType(JSONObject jsonObject) {
        String type = jsonObject.get("type").toString();
        return type;
    }

}
