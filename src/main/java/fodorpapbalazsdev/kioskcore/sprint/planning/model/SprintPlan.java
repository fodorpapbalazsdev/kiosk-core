package fodorpapbalazsdev.kioskcore.sprint.planning.model;

import fodorpapbalazsdev.kioskcore.sprint.planning.model.plannable.UserPlannedItem;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class SprintPlan {
    @NonNull
    private List<UserPlannedItem> userPlannedItems;
}
