package fodorpapbalazsdev.kioskcore.sprint.util;

import fodorpapbalazsdev.kioskcore.sprint.model.Sprint;

import java.util.Date;

public interface SprintUtil {
    boolean dateIsInSprint(Date date, Sprint sprint);
}
