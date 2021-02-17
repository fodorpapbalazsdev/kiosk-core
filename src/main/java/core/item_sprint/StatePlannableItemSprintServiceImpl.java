package core.item_sprint;

import core.OpenStateService;
import core.models.PlannedItem;
import core.models.StatePlannableItem;
import core.sprint.Sprint;
import core.sprint.SprintService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatePlannableItemSprintServiceImpl implements StatePlannableItemSprintService {

    private final SprintService sprintService;
    private final OpenStateService openStateService;

    public StatePlannableItemSprintServiceImpl(SprintService sprintService, OpenStateService openStateService) {
        this.sprintService = sprintService;
        this.openStateService = openStateService;
    }

    @Override
    public List<PlannedItem> getNextSprintItems(List<StatePlannableItem> items) {
        Sprint nextSprint = this.sprintService.getNextSprint();
        return items.stream().filter(this::itemIsPlannedForNextSprint).map(item -> new PlannedItem(item, nextSprint)).collect(Collectors.toList());
    }

    @Override
    public boolean itemIsPlannedForNextSprint(StatePlannableItem item) {
        return openStateService.itemIsInOpenState(item);
    }
}
