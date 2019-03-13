package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;

public class JsonConverter<T> {
    
    private final Gson gson;
    
    public JsonConverter() {
        
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public String convertToJson(List<T> objects) {
        
        JsonArray jarray = gson.toJsonTree(objects).getAsJsonArray();
        //JsonObject jsonObject = new JsonObject();
        //jsonObject.add("objs", jarray);

        //return jsonObject.toString();
        return jarray.toString();
    }
}
