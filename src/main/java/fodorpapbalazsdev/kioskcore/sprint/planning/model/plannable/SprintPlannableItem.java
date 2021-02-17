package fodorpapbalazsdev.kioskcore.sprint.planning.model.plannable;

import fodorpapbalazsdev.kioskcore.model.Item;
import fodorpapbalazsdev.kioskcore.model.User;
import fodorpapbalazsdev.kioskcore.sprint.model.Sprint;
import fodorpapbalazsdev.kioskcore.sprint.exception.ItemIsPlannedForSprintNotSupportedException;
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
