package core.item_sprint;

import models.PlannableItem;

import java.util.List;

public interface ItemSprintService {
    List<PlannableItem> getNextSprintItems(List<PlannableItem> items);

    boolean itemIsPlannedForNextSprint(PlannableItem item);
}
