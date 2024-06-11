package fluff.http.body;

import fluff.http.HTTPException;
import fluff.json.JSON;
import fluff.json.JSONArray;
import fluff.json.JSONObject;

public interface HTTPBodyParser<V> {
	
	HTTPBodyParser<byte[]> BYTES = bytes -> bytes;
	HTTPBodyParser<String> STRING = bytes -> new String(bytes);
	HTTPBodyParser<JSONObject> JSON_OBJECT = bytes -> JSON.object(STRING.parse(bytes));
	HTTPBodyParser<JSONArray> JSON_ARRAY = bytes -> JSON.array(STRING.parse(bytes));
	
	V parse(byte[] bytes) throws HTTPException;
}
