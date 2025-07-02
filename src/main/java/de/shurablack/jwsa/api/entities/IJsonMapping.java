package de.shurablack.jwsa.api.entities;

import org.json.JSONObject;

/**
 * Interface for mapping objects to JSON representations.
 * Classes implementing this interface should provide a method to convert their instances into a {@link JSONObject}.
 */
public interface IJsonMapping {

    /**
     * Converts the implementing class instance into a {@link JSONObject}.
     *
     * @return A {@link JSONObject} representation of the instance.
     */
    JSONObject serialize();

}