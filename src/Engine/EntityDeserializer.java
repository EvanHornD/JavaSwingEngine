package Engine;


import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import Engine.Components.Component;
import Engine.Components.Transform;

public class EntityDeserializer implements JsonDeserializer<Entity> {

    @Override
    public Entity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        JsonArray components = jsonObject.getAsJsonArray("components");
        Transform transform = context.deserialize(jsonObject.get("transform"), Transform.class);
        int zIndex = context.deserialize(jsonObject.get("zIndex"), int.class);

        Entity entity = new Entity(name, transform, zIndex);
        for (JsonElement element : components) {
            Component component = context.deserialize(element, Component.class);
            entity.addComponent(component);
        }
        return entity;
    }
    
}
