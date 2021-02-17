package core.models.plannable_item;

import core.models.User;
import core.sprint.Sprint;
import core.util.mks_util.MKSItemStates;
import core.util.mks_util.MKSPlannableItemUtil;
import core.util.SprintUtil;
import core.exceptions.FieldNotFoundException;
import core.exceptions.MKSFieldStateValueNotSupportedException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

@Data
public class MKSPlannableItem extends SprintPlannableItem {

    private static final String STATE_FIELD_NAME = "State";

    private final String state;

    @Autowired
    private SprintUtil sprintUtil;

    public MKSPlannableItem(Map<String, Object> fields, User user) throws FieldNotFoundException, MKSFieldStateValueNotSupportedException {
        super(fields, user);

        Object fieldValue = fields.get("PCD");
        if (fieldValue == null) {
            throw new FieldNotFoundException("PCD");
        }
        if (!(fieldValue instanceof Date)) {
            throw new IllegalArgumentException("MKSPlannableItem has PCD field but it is not a Date.");
        }

        this.state = this.initializeState(fields);
    }

    @Override
    public boolean itemIsPlannedForSprint(Sprint sprint) {
        Date plannedDate = (Date) this.getFields().get("PCD");
        return MKSPlannableItemUtil.itemIsInOpenState(this) &&
                sprintUtil.dateIsInSprint(plannedDate, sprint);
    }

    private String initializeState(Map<String, Object> fields) throws FieldNotFoundException, MKSFieldStateValueNotSupportedException {
        Object fieldValue = fields.get(STATE_FIELD_NAME);
        if (fieldValue == null) {
            throw new FieldNotFoundException(STATE_FIELD_NAME);
        }
        if (!(fieldValue instanceof String)) {
            throw new IllegalArgumentException("MKSPlannableItem has " + STATE_FIELD_NAME + " field but it is not a String.");
        } else {
            if (!MKSItemStates.ALLOWED_STATES.contains((String) fieldValue)) {
                throw new MKSFieldStateValueNotSupportedException("State of this MKS item is a not supported state: " + fieldValue + "!");
            } else {
                return (String) fieldValue;
            }
        }
    }

}
