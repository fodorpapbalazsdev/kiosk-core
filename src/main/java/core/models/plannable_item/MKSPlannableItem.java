package core.models.plannable_item;

import core.models.User;
import core.sprint.Sprint;
import exceptions.FieldNotFoundException;

import java.util.Date;
import java.util.Map;

public class MKSPlannableItem extends SprintPlannableItem {

    public MKSPlannableItem(Map<String, Object> fields, User user) throws FieldNotFoundException {
        super(fields, user);

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
        Date plannedDate = (Date) this.getFields().get("PCD");
        return (plannedDate.after(sprint.getStartDate()) || plannedDate.compareTo(sprint.getStartDate()) == 0)
                && (plannedDate.before(sprint.getEndDate()) || plannedDate.compareTo(sprint.getEndDate()) == 0);
    }
}
