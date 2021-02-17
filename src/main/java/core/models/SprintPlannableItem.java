package core.models;

import core.sprint.Sprint;
import exceptions.ItemIsPlannedForSprintNotSupportedException;

import java.util.Map;

public abstract class SprintPlannableItem extends Item {

    public SprintPlannableItem(Map<String, Object> fields) {
        super(fields);
    }

    public boolean itemIsPlannedForSprint(Sprint sprint) throws ItemIsPlannedForSprintNotSupportedException {
        throw new ItemIsPlannedForSprintNotSupportedException();
    }
}
