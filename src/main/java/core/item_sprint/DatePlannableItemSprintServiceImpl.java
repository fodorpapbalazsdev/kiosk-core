package core.item_sprint;

import core.models.DatePlannableItem;
import core.models.PlannedItem;
import core.sprint.Sprint;
import core.sprint.SprintService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatePlannableItemSprintServiceImpl implements DatePlannableItemSprintService {

    private final SprintService sprintService;

    public DatePlannableItemSprintServiceImpl(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @Override
    public List<PlannedItem> getNextSprintItems(List<DatePlannableItem> items) {
        Sprint nextSprint = this.sprintService.getNextSprint();
        return items.stream().filter(this::itemIsPlannedForNextSprint).map(item -> new PlannedItem(item, nextSprint)).collect(Collectors.toList());
    }

    @Override
    public boolean itemIsPlannedForNextSprint(DatePlannableItem item) {
        Sprint nextSprint = this.sprintService.getNextSprint();
        Date plannedDate = item.getPlanBaseFieldVale();
        return (plannedDate.after(nextSprint.getStartDate()) || plannedDate.compareTo(nextSprint.getStartDate()) == 0)
                && (plannedDate.before(nextSprint.getEndDate()) || plannedDate.compareTo(nextSprint.getEndDate()) == 0);
    }
}
