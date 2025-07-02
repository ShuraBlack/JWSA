package de.shurablack.jwsa.api.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class ServerOffsetTimeTest {

    @Test
    void convertsValidTimestampToLocalDateTime() {
        String validTimestamp = "2023-03-15T10:00:00+01:00";
        LocalDateTime result = ServerOffsetTime.of(validTimestamp);
        assertNotNull(result);
        assertEquals(2023, result.getYear());
        assertEquals(3, result.getMonthValue());
        assertEquals(15, result.getDayOfMonth());
        assertEquals(10, result.getHour());
    }

    @Test
    void returnsNullForNullTimestamp() {
        assertNull(ServerOffsetTime.of(null));
    }

    @Test
    void throwsExceptionForInvalidTimestampFormat() {
        String invalidTimestamp = "invalid-timestamp";
        assertThrows(Exception.class, () -> ServerOffsetTime.of(invalidTimestamp));
    }

    @Test
    void handlesTimestampWithDifferentOffset() {
        String timestampWithOffset = "2023-03-15T10:00:00-05:00";
        LocalDateTime result = ServerOffsetTime.of(timestampWithOffset);
        assertNotNull(result);
    }


}