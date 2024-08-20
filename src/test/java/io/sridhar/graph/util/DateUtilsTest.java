package io.sridhar.graph.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateUtilsTest {

    public Date getDate(String dateStr) throws ParseException {
        String format = "yyyy-MM-dd";
        return new SimpleDateFormat(format).parse(dateStr);
    }

    @Test
    public void testBoth() throws ParseException {

        boolean overlaps = DateUtils.overlaps(getDate("2022-01-01"),
                getDate("2022-12-31"),
                getDate("2022-12-01"),
                getDate("2023-08-10"));

        // Assert
        assertTrue(overlaps);

        float days = DateUtils.overlapDays(getDate("2022-01-01"),
                getDate("2022-12-31"),
                getDate("2022-12-01"),
                getDate("2023-08-10"));

        assertEquals(30, days);

    }

    @Test
    public void testEndNull() throws ParseException {
        boolean overlaps = DateUtils.overlaps(getDate("2022-01-01"),
                null,
                getDate("2022-12-01"),
                null);

        assertTrue(overlaps);

        float days = DateUtils.overlapDays(getDate("2022-01-01"),
                null,
                getDate("2022-12-01"),
                null);
        assertEquals(-628, days);
    }

    @Test
    public void testOneNull() throws ParseException {
        boolean overlaps = DateUtils.overlaps(
                getDate("2022-01-01"),
                null,
                getDate("2022-12-01"),
                getDate("2023-08-10"));

        assertTrue(overlaps);

        float days = DateUtils.overlapDays(
                getDate("2022-01-01"),
                null,
                getDate("2022-12-01"),
                getDate("2023-08-10"));
        assertEquals(251, days);

    }

}
