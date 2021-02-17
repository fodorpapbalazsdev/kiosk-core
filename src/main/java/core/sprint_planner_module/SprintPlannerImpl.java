package core.sprint_planner_module;

import core.models.plannable_item.PlannedItem;
import core.models.User;
import core.models.UserPlannedItem;
import core.models.plannable_item.SprintPlannableItem;
import core.sprint.Sprint;
import core.sprint.SprintService;
import core.sprint_planner_module.models.SprintPlan;
import exceptions.ItemIsPlannedForSprintNotSupportedException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SprintPlannerImpl implements SprintPlanner {

    @NonNull
    @Autowired
    private final SprintService sprintService;

    @Override
    public SprintPlan getNextSprintPlann(List<User> users, List<SprintPlannableItem> userPlannableItems) {
        Sprint nextSprint = sprintService.getNextSprint();
        return getSprintPlan(userPlannableItems, nextSprint);
    }

    @Override
    public SprintPlan getCurrentSprintPlann(List<User> users, List<SprintPlannableItem> userPlannableItems) {
        Sprint currentSprint = sprintService.getCurrent();
        return getSprintPlan(userPlannableItems, currentSprint);
    }

    @Override
    public SprintPlan getSprintPlann(List<User> users, List<SprintPlannableItem> userPlannableItems, Sprint sprint) {
        return getSprintPlan(userPlannableItems, sprint);
    }

    private SprintPlan getSprintPlan(List<SprintPlannableItem> userPlannableItems, Sprint nextSprint) {
        List<UserPlannedItem> userPlannedItems = userPlannableItems
                .stream()
                .filter(i -> {
                    try {
                        return i.itemIsPlannedForSprint(nextSprint);
                    } catch (ItemIsPlannedForSprintNotSupportedException e) {
                        e.printStackTrace();
                        return false; // TODO: Exception handling needed
                    }
                })
                .map(it -> getUserPlannedItem(it, nextSprint))
                .collect(Collectors.toList());
        return new SprintPlan(userPlannedItems);
    }

    private UserPlannedItem getUserPlannedItem(SprintPlannableItem i, Sprint sprint) {
        PlannedItem plannedItem = new PlannedItem(i, sprint);
        return new UserPlannedItem(plannedItem, i.getUser());
    }
}
