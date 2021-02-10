package core.item_sprint;

import core.models.FlagPlannableItem;
import core.models.PlannedItem;
import core.sprint.Sprint;
import core.sprint.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlagPlannableItemSprintServiceImpl implements FlagPlannableItemSprintService {

    private final SprintService sprintService;

    public FlagPlannableItemSprintServiceImpl(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @Override
    public List<PlannedItem> getNextSprintItems(List<FlagPlannableItem> items) {
        Sprint nextSprint = this.sprintService.getNextSprint();
        return items.stream().filter(this::itemIsPlannedForNextSprint).map(item -> new PlannedItem(item, nextSprint)).collect(Collectors.toList());
    }

    @Override
    public boolean itemIsPlannedForNextSprint(FlagPlannableItem item) {
        return item.getPlanBaseFieldVale();
    }
}
