package core.sprint_planner_module;

import core.models.UserDatePlannableItem;
import core.sprint.Sprint;
import core.sprint_planner_module.models.SprintPlan;
import core.models.User;

import java.util.List;

public interface SprintPlanner {
    SprintPlan getNextSprintPlann(List<User> users, List<UserDatePlannableItem> plannableItems);

    SprintPlan getCurrentSprintPlann(List<User> users, List<UserDatePlannableItem> plannableItems);

    SprintPlan getSprintPlann(List<User> users, List<UserDatePlannableItem> plannableItems, Sprint sprint);
}
