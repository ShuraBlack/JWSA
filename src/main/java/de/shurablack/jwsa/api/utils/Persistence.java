package de.shurablack.jwsa.api.utils;

import de.shurablack.jwsa.api.entities.IJsonMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.*;

/**
 * Utility class for handling persistence operations such as serialization, deserialization,
 * and saving JSON mappings to files.
 */
public class Persistence {

    /** Logger instance for logging errors and information related to persistence operations. */
    private static final Logger LOGGER = LogManager.getLogger(Persistence.class);

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Persistence() {
        // Prevent instantiation
    }

    /**
     * Serializes a given object to a file.
     *
     * @param object   The object to serialize. Must implement {@link Serializable}.
     * @param filePath The file path where the serialized object will be saved.
     */
    public static void serializeToFile(Serializable object, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(object);
        } catch (IOException e) {
            LOGGER.error("Failed to serialize object to file: {}", filePath, e);
        }
    }

    /**
     * Deserializes an object from a file.
     *
     * @param clazz    The class type of the object to deserialize.
     * @param filePath The file path from which the object will be deserialized.
     * @param <T>      The type of the object to deserialize.
     * @return The deserialized object if successful, or null if an error occurs or the object is not of the expected type.
     */
    public static <T> T deserializeFromFile(Class<T> clazz, String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = in.readObject();
            if (clazz.isInstance(obj)) {
                return clazz.cast(obj);
            } else {
                LOGGER.error("Deserialized object is not of type: {}", clazz.getName());
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Failed to deserialize object from file: {}", filePath, e);
            return null;
        }
    }

    /**
     * Writes the JSON representation of an object implementing {@link IJsonMapping} to a file.
     *
     * @param object   The object to be serialized to JSON. Must implement {@link IJsonMapping}.
     * @param filePath The file path where the JSON representation will be saved.
     */
    public static void jsonMapToFile(IJsonMapping object, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(object.serialize().toString(4)); // Pretty print with 4 spaces
        } catch (IOException e) {
            LOGGER.error("Failed to write JSON mapping to file: {}", filePath, e);
        }
    }

    /**
     * Reads a JSON object from a file.
     *
     * @param filePath The file path from which the JSON object will be read.
     * @return A {@link JSONObject} representing the contents of the file, or null if an error occurs.
     */
    public static JSONObject readJsonFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            return new JSONObject(jsonBuilder.toString());
        } catch (IOException e) {
            LOGGER.error("Failed to read JSON from file: {}", filePath, e);
            return null;
        }
    }
}