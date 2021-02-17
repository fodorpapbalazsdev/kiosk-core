package core.models.plannable_item;

import core.models.Item;
import core.models.User;
import core.sprint.Sprint;
import core.exceptions.ItemIsPlannedForSprintNotSupportedException;
import lombok.Data;

import java.util.Map;

@Data
public abstract class SprintPlannableItem extends Item {

    private final User user;

    public SprintPlannableItem(Map<String, Object> fields, User user) {
        super(fields);
        this.user = user;
    }

    public boolean itemIsPlannedForSprint(Sprint sprint) throws ItemIsPlannedForSprintNotSupportedException {
        throw new ItemIsPlannedForSprintNotSupportedException();
    }
}
