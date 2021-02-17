package fodorpapbalazsdev.kioskcore.sprint.planning.model.plannable;

import fodorpapbalazsdev.kioskcore.model.User;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserPlannedItem extends PlannedItem {

    @NonNull
    private final User plannedUser;

    public UserPlannedItem(PlannedItem plannedItem, User plannedUser) {
        super(plannedItem);
        this.plannedUser = plannedUser;
    }
}
