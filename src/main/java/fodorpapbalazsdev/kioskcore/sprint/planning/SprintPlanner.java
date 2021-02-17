package fodorpapbalazsdev.kioskcore.sprint.planning;

import fodorpapbalazsdev.kioskcore.model.User;
import fodorpapbalazsdev.kioskcore.sprint.planning.model.plannable.SprintPlannableItem;
import fodorpapbalazsdev.kioskcore.sprint.model.Sprint;
import fodorpapbalazsdev.kioskcore.sprint.planning.model.SprintPlan;

import java.util.List;

public interface SprintPlanner {
    SprintPlan getNextSprintPlann(List<User> users, List<SprintPlannableItem> plannableItems);

    SprintPlan getCurrentSprintPlann(List<User> users, List<SprintPlannableItem> plannableItems);

    SprintPlan getSprintPlann(List<User> users, List<SprintPlannableItem> plannableItems, Sprint sprint);
}
