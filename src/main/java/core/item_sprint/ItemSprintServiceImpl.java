package core.item_sprint;

import core.sprint.SprintService;
import lombok.Data;
import lombok.NonNull;
import models.PlannableDateField;
import models.Sprint;

import java.util.Date;

@Data
public class ItemSprintServiceImpl implements ItemSprintService {

    @NonNull
    final SprintService sprintService;

    @Override
    public boolean itemIsPlannedForNextSprint(PlannableDateField plannableDateField) {
        Sprint nextSprint = sprintService.getNextSprint();
        Date plannedDate = plannableDateField.getPlannedDate();
        return (plannedDate.after(nextSprint.getStartDate()) || plannedDate.compareTo(nextSprint.getStartDate()) == 0)
                && (plannedDate.before(nextSprint.getEndDate()) || plannedDate.compareTo(nextSprint.getEndDate()) == 0);
    }
}
