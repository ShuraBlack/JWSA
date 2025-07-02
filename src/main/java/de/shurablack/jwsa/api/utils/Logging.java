package de.shurablack.jwsa.api.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * Utility class for managing logging configurations.
 */
public class Logging {

    /**
     * Sets the log level for the root logger.
     *
     * @param level The {@link Level} to set for the root logger.
     *              This determines the minimum severity of log messages that will be output.
     */
    public static void setLevelTo(Level level) {
        Configurator.setRootLevel(level);
    }

}