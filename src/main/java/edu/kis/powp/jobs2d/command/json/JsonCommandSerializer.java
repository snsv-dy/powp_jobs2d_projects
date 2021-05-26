package edu.kis.powp.jobs2d.command.json;

import com.google.gson.*;

import java.lang.reflect.Type;

//helpful link
//https://technology.finra.org/code/serialize-deserialize-interfaces-in-java.html
public class JsonCommandSerializer implements JsonSerializer, JsonDeserializer {
	private static final String PROPERTY_CLASSNAME = "ClassName";

	@Override
	public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonPrimitive prim = jsonObject.get(PROPERTY_CLASSNAME).getAsJsonPrimitive();
		String className = prim.getAsString();
		Class<?> command;
		try {
			command = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return jsonDeserializationContext.deserialize(jsonObject, command);
	}

	@Override
	public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(PROPERTY_CLASSNAME, object.getClass().getName());
		jsonObject.addProperty("posX", jsonSerializationContext.serialize(object).getAsJsonObject().get("posX").getAsInt());
		jsonObject.addProperty("posY", jsonSerializationContext.serialize(object).getAsJsonObject().get("posY").getAsInt());
		return jsonObject;
	}
}
