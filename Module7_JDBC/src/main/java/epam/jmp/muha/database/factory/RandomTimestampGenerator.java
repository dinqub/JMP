package epam.jmp.muha.database.factory;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTimestampGenerator {
    private static Random random = new Random();

    public static Instant between(Instant lowerBoundDate, int days) {
        Instant randomInstant = lowerBoundDate.plus(random.nextInt(days - 1), ChronoUnit.DAYS);
        while(randomInstant.equals(lowerBoundDate)) {
            randomInstant = lowerBoundDate.plus(random.nextInt(days - 1), ChronoUnit.DAYS);
        }
        return randomInstant;
    }

    public static List<Instant> between(Instant lowerBound, int days, int totalDays) {
        List<Instant> timestamps = new ArrayList<Instant>();
        for(int i = 0; i < totalDays; i++) {
            timestamps.add(between(lowerBound, days));
        }
        return timestamps;
    }

}