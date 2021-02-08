package core.item_sprint;

import core.sprint.SprintService;
import lombok.Data;
import lombok.NonNull;
import models.PlannableItem;
import models.Sprint;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ItemSprintServiceImpl implements ItemSprintService {

    @NonNull
    final SprintService sprintService;

    @Override
    public List<PlannableItem> getNextSprintItems(List<PlannableItem> items) {
        return items.stream().filter(this::itemIsPlannedForNextSprint).collect(Collectors.toList());
    }

    @Override
    public boolean itemIsPlannedForNextSprint(PlannableItem item) {
        Sprint nextSprint = sprintService.getNextSprint();
        Date plannedDate = item.getPlannedDate();
        return (plannedDate.after(nextSprint.getStartDate()) || plannedDate.compareTo(nextSprint.getStartDate()) == 0)
                && (plannedDate.before(nextSprint.getEndDate()) || plannedDate.compareTo(nextSprint.getEndDate()) == 0);
    }
}
