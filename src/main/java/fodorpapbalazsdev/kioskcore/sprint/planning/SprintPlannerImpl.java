package fodorpapbalazsdev.kioskcore.sprint.planning;

import fodorpapbalazsdev.kioskcore.sprint.service.SprintService;
import fodorpapbalazsdev.kioskcore.sprint.planning.model.plannable.PlannedItem;
import fodorpapbalazsdev.kioskcore.model.User;
import fodorpapbalazsdev.kioskcore.sprint.planning.model.plannable.UserPlannedItem;
import fodorpapbalazsdev.kioskcore.sprint.planning.model.plannable.SprintPlannableItem;
import fodorpapbalazsdev.kioskcore.sprint.model.Sprint;
import fodorpapbalazsdev.kioskcore.sprint.planning.model.SprintPlan;
import fodorpapbalazsdev.kioskcore.sprint.exception.ItemIsPlannedForSprintNotSupportedException;
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
