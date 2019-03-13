package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;

public class JsonConverter<T> {
    
    private final Gson gson;
    
    public JsonConverter() {
        
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public String convertToJson(List<T> objects) {
        
        JsonElement tree = gson.toJsonTree(objects);
        //tree might be null, so just in case return empty json array
        if(tree.isJsonNull()) {
            return "[]";
        }
        
        JsonArray jarray = tree.getAsJsonArray();
        return jarray.toString();
    }
}
