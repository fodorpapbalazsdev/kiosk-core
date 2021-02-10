package core.models;

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
