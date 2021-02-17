package core.util;

import core.sprint.Sprint;

import java.util.Date;

public class SprintUtilImpl implements SprintUtil {
    public boolean dateIsInSprint(Date date, Sprint sprint) {
        return (date.after(sprint.getStartDate()) || date.compareTo(sprint.getStartDate()) == 0)
                && (date.before(sprint.getEndDate()) || date.compareTo(sprint.getEndDate()) == 0);
    }
}
