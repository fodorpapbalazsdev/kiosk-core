package core.sprint_planner_module;

import core.UserService;
import core.item_sprint.ItemSprintServiceBase;
import core.models.DatePlannableItem;
import core.models.User;
import core.models.UserDatePlannableItem;
import core.sprint.Sprint;
import core.sprint.SprintService;
import core.sprint_planner_module.models.SprintPlan;
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
    ItemSprintServiceBase itemSprintServiceBase;

    @Mock
    SprintService sprintService;

    @Mock
    UserService userService;

    @InjectMocks
    SprintPlannerImpl sprintPlanner;

    @Test
    @SneakyThrows
    public void getNextSprintPlann() {

        when(sprintService.getNextSprint()).thenReturn(new Sprint("1", Date.valueOf("2021-02-15"), Date.valueOf("2021-02-28")));
        when(userService.getUser(1L)).thenReturn(new User(1L));
        when(userService.getUser(2L)).thenReturn(new User(2L));

        /* Construct the users */
        List<User> users = new ArrayList<>();
        users.add(new User(1L));
        users.add(new User(2L));
        users.add(new User(3L));

        /* Construct the items */
        List<UserDatePlannableItem> userPlannableItems = new ArrayList<>();
        Map<String, Object> fields1 = new HashMap<>();
        fields1.put("PCD", Date.valueOf("2021-02-20"));  // Planned
        fields1.put("UserID", 1L);
        DatePlannableItem datePlannableItem1 = new DatePlannableItem(fields1, "PCD");
        UserDatePlannableItem userDatePlannableItem1 = new UserDatePlannableItem(datePlannableItem1, "UserID");

        Map<String, Object> fields2 = new HashMap<>();
        fields2.put("PCD", Date.valueOf("2021-02-20"));  // Planned
        fields2.put("UserID", 1L);
        DatePlannableItem datePlannableItem2 = new DatePlannableItem(fields2, "PCD");
        UserDatePlannableItem userDatePlannableItem2 = new UserDatePlannableItem(datePlannableItem2, "UserID");

        Map<String, Object> fields3 = new HashMap<>();
        fields3.put("PCD", Date.valueOf("2021-02-20"));  // Planned
        fields3.put("UserID", 2L);
        DatePlannableItem datePlannableItem3 = new DatePlannableItem(fields3, "PCD");
        UserDatePlannableItem userDatePlannableItem3 = new UserDatePlannableItem(datePlannableItem3, "UserID");

        Map<String, Object> fields4 = new HashMap<>();
        fields4.put("PCD", Date.valueOf("2021-03-01"));  // NOT Planned
        fields4.put("UserID", 1L);
        DatePlannableItem datePlannableItem4 = new DatePlannableItem(fields4, "PCD");
        UserDatePlannableItem userDatePlannableItem4 = new UserDatePlannableItem(datePlannableItem4, "UserID");

        when(itemSprintServiceBase.itemIsPlannedForNextSprint(userDatePlannableItem1)).thenReturn(true);
        when(itemSprintServiceBase.itemIsPlannedForNextSprint(userDatePlannableItem2)).thenReturn(true);
        when(itemSprintServiceBase.itemIsPlannedForNextSprint(userDatePlannableItem3)).thenReturn(true);
        when(itemSprintServiceBase.itemIsPlannedForNextSprint(userDatePlannableItem4)).thenReturn(false);

        userPlannableItems.add(userDatePlannableItem1);
        userPlannableItems.add(userDatePlannableItem2);
        userPlannableItems.add(userDatePlannableItem3);
        userPlannableItems.add(userDatePlannableItem4);

        SprintPlan sprintPlanResult = sprintPlanner.getNextSprintPlann(users, userPlannableItems);

        assertEquals(3, sprintPlanResult.getUserPlannedItems().size());
        assertEquals(1L, sprintPlanResult.getUserPlannedItems().get(0).getPlannedUser().getId());
        assertEquals(1L, sprintPlanResult.getUserPlannedItems().get(1).getPlannedUser().getId());
        assertEquals(2L, sprintPlanResult.getUserPlannedItems().get(2).getPlannedUser().getId());
    }

}
