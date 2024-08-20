package io.sridhar.graph.util;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    //This function returns the no of overlap days and if both end dates are null, it returns with a negative value
    public static float overlapDays(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
        if(endDate1 == null && endDate2 == null) {
            Date maxStart = startDate1.after(startDate2) ? startDate1 : startDate2;
            long diffInMillis = Date.from(Instant.now()).getTime() - maxStart.getTime();
            return -TimeUnit.MILLISECONDS.toDays(diffInMillis);
        } else if(endDate1 == null) {
            Date maxStart = startDate1.after(startDate2) ? startDate1 : startDate2;
            long diffInMillis = endDate2.getTime() - maxStart.getTime();
            return TimeUnit.MILLISECONDS.toDays(diffInMillis);
        } else if (endDate2 == null) {
            Date maxStart = startDate1.after(startDate2) ? startDate1 : startDate2;
            long diffInMillis = endDate1.getTime() - maxStart.getTime();
            return TimeUnit.MILLISECONDS.toDays(diffInMillis);
        } else {
            Date maxStart = startDate1.after(startDate2) ? startDate1 : startDate2;
            Date minEnd = endDate1.before(endDate2) ? endDate1 : endDate2;
            long diffInMillis = minEnd.getTime() - maxStart.getTime();
            return TimeUnit.MILLISECONDS.toDays(diffInMillis);
        }
    }

    //This function returns if there is a overlap between two given dates
    public static boolean overlaps(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
        if(endDate1 == null && endDate2 == null) {
            return true;
        } else if(endDate1 == null) {
            return endDate2.after(startDate1);
        } else if (endDate2 == null) {
            return endDate1.after(startDate2);
        } else {
            return !(endDate1.before(startDate2) || endDate2.before(startDate1));
        }
    }


}
