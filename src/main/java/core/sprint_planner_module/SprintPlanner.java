package core.sprint_planner_module;

import core.models.User;
import core.models.plannable_item.SprintPlannableItem;
import core.sprint.Sprint;
import core.sprint_planner_module.models.SprintPlan;

import java.util.List;

public interface SprintPlanner {
    SprintPlan getNextSprintPlann(List<User> users, List<SprintPlannableItem> plannableItems);

    SprintPlan getCurrentSprintPlann(List<User> users, List<SprintPlannableItem> plannableItems);

    SprintPlan getSprintPlann(List<User> users, List<SprintPlannableItem> plannableItems, Sprint sprint);
}
