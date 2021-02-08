package core.item_sprint;

import models.PlannableDateField;

public interface ItemSprintService {
    boolean itemIsPlannedForNextSprint(PlannableDateField item);
}
