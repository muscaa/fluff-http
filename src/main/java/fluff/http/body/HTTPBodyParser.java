package fluff.http.body;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import fluff.http.HTTPException;
import fluff.json.JSON;
import fluff.json.JSONArray;
import fluff.json.JSONObject;

/**
 * Interface representing a parser for HTTP body content.
 *
 * @param <V> the type of the parsed result
 */
public interface HTTPBodyParser<V> {
	
	/**
	 * Represents a parser for input streams. This does not handle input stream closing.
	 */
	HTTPBodyParser<InputStream> INPUT_STREAM = new HTTPBodyParser<>() {
		@Override
		public InputStream serialize(InputStream value) throws HTTPException, IOException {
			return value;
		}
		
		@Override
		public InputStream deserialize(InputStream in) throws HTTPException, IOException {
			return in;
		}
	};
	
	/**
	 * Represents a parser for byte arrays.
	 */
    HTTPBodyParser<byte[]> BYTES = new HTTPBodyParser<>() {
		@Override
		public InputStream serialize(byte[] value) throws HTTPException, IOException {
			return new ByteArrayInputStream(value);
		}
		
		@Override
		public byte[] deserialize(InputStream in) throws HTTPException, IOException {
			return in.readAllBytes();
		}
    };
    
	/**
	 * Represents a parser for strings.
	 */
    HTTPBodyParser<String> STRING = new HTTPBodyParser<>() {
		@Override
		public InputStream serialize(String value) throws HTTPException, IOException {
			return BYTES.serialize(value.getBytes());
		}
		
		@Override
		public String deserialize(InputStream in) throws HTTPException, IOException {
			return new String(BYTES.deserialize(in));
		}
    };
    
	/**
	 * Represents a parser for JSON objects.
	 */
    HTTPBodyParser<JSONObject> JSON_OBJECT = new HTTPBodyParser<>() {
		@Override
		public InputStream serialize(JSONObject value) throws HTTPException, IOException {
			return STRING.serialize(value.toString());
		}
		
		@Override
		public JSONObject deserialize(InputStream in) throws HTTPException, IOException {
			return JSON.object(STRING.deserialize(in));
		}
    };
    
	/**
	 * Represents a parser for JSON linked objects.
	 */
    HTTPBodyParser<JSONObject> JSON_LINKED_OBJECT = new HTTPBodyParser<>() {
		@Override
		public InputStream serialize(JSONObject value) throws HTTPException, IOException {
			return JSON_OBJECT.serialize(value);
		}
		
		@Override
		public JSONObject deserialize(InputStream in) throws HTTPException, IOException {
			return JSON.object(JSON::linkedObject, JSON::linkedArray, STRING.deserialize(in));
		}
    };
    
    /**
     * Represents a parser for JSON arrays.
     */
    HTTPBodyParser<JSONArray> JSON_ARRAY = new HTTPBodyParser<>() {
		@Override
		public InputStream serialize(JSONArray value) throws HTTPException, IOException {
			return STRING.serialize(value.toString());
		}
		
		@Override
		public JSONArray deserialize(InputStream in) throws HTTPException, IOException {
			return JSON.array(STRING.deserialize(in));
		}
    };
    
    /**
     * Represents a parser for JSON linked arrays.
     */
    HTTPBodyParser<JSONArray> JSON_LINKED_ARRAY = new HTTPBodyParser<>() {
		@Override
		public InputStream serialize(JSONArray value) throws HTTPException, IOException {
			return JSON_ARRAY.serialize(value);
		}
		
		@Override
		public JSONArray deserialize(InputStream in) throws HTTPException, IOException {
			return JSON.array(JSON::linkedObject, JSON::linkedArray, STRING.deserialize(in));
		}
    };
    
	/**
	 * Serializes the given value into an input stream.
	 *
	 * @param value the value to serialize
	 * @return the serialized input stream
	 * @throws HTTPException if an error occurs during serialization
	 * @throws IOException if an I/O error occurs
	 */
    InputStream serialize(V value) throws HTTPException, IOException;
    
    /**
     * Deserializes the given input stream into a value.
     *
     * @param in the input stream to parse
     * @return the parsed result
     * @throws HTTPException if an error occurs during parsing
     * @throws IOException if an I/O error occurs
     */
    V deserialize(InputStream in) throws HTTPException, IOException;
}
