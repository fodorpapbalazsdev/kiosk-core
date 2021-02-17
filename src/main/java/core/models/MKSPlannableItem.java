package core.models;

import core.sprint.Sprint;
import exceptions.FieldNotFoundException;

import java.util.Date;
import java.util.Map;

public class MKSPlannableItem extends SprintPlannableItem {

    public MKSPlannableItem(Map<String, Object> fields) throws FieldNotFoundException {
        super(fields);

        Object fieldValue = fields.get("PCD");
        if (fieldValue == null) {
            throw new FieldNotFoundException("PCD");
        }
        if (!(fieldValue instanceof Date)) {
            throw new IllegalArgumentException("ItemTypeA has PCD field but it is not a Date.");
        }
    }

    @Override
    public boolean itemIsPlannedForSprint(Sprint sprint) {
        Date plannedDate = (Date) this.fields.get("PCD");
        return (plannedDate.after(sprint.getStartDate()) || plannedDate.compareTo(sprint.getStartDate()) == 0)
                && (plannedDate.before(sprint.getEndDate()) || plannedDate.compareTo(sprint.getEndDate()) == 0);
    }
}
