package core.sprint_planner_module;

import core.models.User;
import core.models.plannable_item.MKSPlannableItem;
import core.models.plannable_item.SprintPlannableItem;
import core.sprint.Sprint;
import core.sprint.SprintService;
import core.sprint_planner_module.models.SprintPlan;
import core.util.SprintUtilImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("SprintPlannerImpl Test")
class SprintPlannerImplTest {

    @Mock
    SprintService sprintService;


    @InjectMocks
    SprintPlannerImpl sprintPlanner;

    @Test
    @SneakyThrows
    public void getNextSprintPlann() {

        when(sprintService.getNextSprint()).thenReturn(new Sprint("1", Date.valueOf("2021-02-15"), Date.valueOf("2021-02-28")));

        /* Construct the users */
        List<User> users = new ArrayList<>();
        users.add(new User(1L));
        users.add(new User(2L));
        users.add(new User(3L));

        /* Construct the items */
        List<SprintPlannableItem> userPlannableItems = new ArrayList<>();
        Map<String, Object> fields1 = new HashMap<>();
        fields1.put("PCD", Date.valueOf("2021-02-20"));  // Planned
        fields1.put("Assigned User", 1L);
        fields1.put("State", "OPEN");
        MKSPlannableItem mksPlannableItem1 = new MKSPlannableItem(fields1, users.get(0));
        mksPlannableItem1.setSprintUtil(new SprintUtilImpl());


        Map<String, Object> fields2 = new HashMap<>();
        fields2.put("PCD", Date.valueOf("2021-02-20"));  // Planned
        fields2.put("Assigned User", 1L);
        fields2.put("State", "OPEN");

        MKSPlannableItem mksPlannableItem2 = new MKSPlannableItem(fields2, users.get(0));
        mksPlannableItem2.setSprintUtil(new SprintUtilImpl());


        Map<String, Object> fields3 = new HashMap<>();
        fields3.put("PCD", Date.valueOf("2021-02-20"));  // Planned
        fields3.put("Assigned User", 2L);
        fields3.put("State", "OPEN");
        MKSPlannableItem mksPlannableItem3 = new MKSPlannableItem(fields3, users.get(1));
        mksPlannableItem3.setSprintUtil(new SprintUtilImpl());


        Map<String, Object> fields4 = new HashMap<>();
        fields4.put("PCD", Date.valueOf("2021-03-01"));  // NOT Planned
        fields4.put("Assigned User", 1L);
        fields4.put("State", "OPEN");
        MKSPlannableItem mksPlannableItem4 = new MKSPlannableItem(fields4, users.get(2));
        mksPlannableItem4.setSprintUtil(new SprintUtilImpl());

        userPlannableItems.add(mksPlannableItem1);
        userPlannableItems.add(mksPlannableItem2);
        userPlannableItems.add(mksPlannableItem3);
        userPlannableItems.add(mksPlannableItem4);

        SprintPlan sprintPlanResult = sprintPlanner.getNextSprintPlann(users, userPlannableItems);

        assertEquals(3, sprintPlanResult.getUserPlannedItems().size());
        assertEquals(1L, sprintPlanResult.getUserPlannedItems().get(0).getPlannedUser().getId());
        assertEquals(1L, sprintPlanResult.getUserPlannedItems().get(1).getPlannedUser().getId());
        assertEquals(2L, sprintPlanResult.getUserPlannedItems().get(2).getPlannedUser().getId());
    }

}
