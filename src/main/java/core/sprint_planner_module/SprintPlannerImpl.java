package core.sprint_planner_module;

import core.UserService;
import core.item_sprint.ItemSprintServiceBase;
import core.models.PlannedItem;
import core.models.User;
import core.models.UserDatePlannableItem;
import core.models.UserPlannedItem;
import core.sprint.Sprint;
import core.sprint.SprintService;
import core.sprint_planner_module.models.SprintPlan;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SprintPlannerImpl implements SprintPlanner {

    @NonNull
    @Autowired
    private final ItemSprintServiceBase itemSprintServiceBase;

    @NonNull
    @Autowired
    private final SprintService sprintService;

    @NonNull
    @Autowired
    private final UserService userService;

    @Override
    public SprintPlan getNextSprintPlann(List<User> users, List<UserDatePlannableItem> userPlannableItems) {
        Sprint sprint = sprintService.getNextSprint();
        List<UserPlannedItem> userPlannedItems = userPlannableItems
                .stream()
                .filter(i -> itemSprintServiceBase.itemIsPlannedForNextSprint(i))
                .map(it -> getUserPlannedItem(it, sprint))
                .collect(Collectors.toList());
        return new SprintPlan(userPlannedItems);
    }

    @Override
    public SprintPlan getCurrentSprintPlann(List<User> users, List<UserDatePlannableItem> userPlannableItems) {
        Sprint sprint = sprintService.getCurrent();
        List<UserPlannedItem> userPlannedItems = new ArrayList<>();
        userPlannableItems
                .stream()
                .filter(i -> itemSprintServiceBase.itemIsPlannedForNextSprint(i))
                .map(i -> getUserPlannedItem(i, sprint))
                .collect(Collectors.toList());
        return new SprintPlan(userPlannedItems);
    }

    @Override
    public SprintPlan getSprintPlann(List<User> users, List<UserDatePlannableItem> userPlannableItems, Sprint sprint) {
        List<UserPlannedItem> userPlannedItems = userPlannableItems
                .stream()
                .filter(i -> itemSprintServiceBase.itemIsPlannedForNextSprint(i))
                .map(i -> getUserPlannedItem(i, sprint))
                .collect(Collectors.toList());
        return new SprintPlan(userPlannedItems);
    }

    private UserPlannedItem getUserPlannedItem(UserDatePlannableItem i, Sprint sprint) {
        PlannedItem plannedItem = new PlannedItem(i, sprint);
        return new UserPlannedItem(plannedItem, userService.getUser(i.getUserId()));
    }
}
