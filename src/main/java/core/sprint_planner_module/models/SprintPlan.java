package core.sprint_planner_module.models;

import core.models.UserPlannedItem;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class SprintPlan {
    @NonNull
    private List<UserPlannedItem> userPlannedItems;
}
