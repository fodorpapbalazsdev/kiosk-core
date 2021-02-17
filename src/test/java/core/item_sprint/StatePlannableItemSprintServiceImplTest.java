package core.item_sprint;

import core.OpenStateService;
import core.models.PlannedItem;
import core.models.StatePlannableItem;
import core.sprint.Sprint;
import core.sprint.SprintService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("StatePlannableItemSprintServiceImplTest Test")
class StatePlannableItemSprintServiceImplTest {

    @Mock
    SprintService sprintService;

    @Mock
    OpenStateService openStateService;

    @InjectMocks
    StatePlannableItemSprintServiceImpl statePlannableItemSprintService;

    @SneakyThrows
    @Test
    void getNextSprintItems() {

        when(sprintService.getNextSprint()).thenReturn(new Sprint("1", Date.valueOf("2021-02-15"), Date.valueOf("2021-02-28")));

        Map<String, Object> fields = new HashMap<>();
        fields.put("State", "OPEN");

        List<StatePlannableItem> plannableItems = new ArrayList<>();
        StatePlannableItem statePlannableItem1 = new StatePlannableItem(fields, "State");
        plannableItems.add(statePlannableItem1);

        Map<String, Object> fields2 = new HashMap<>();
        fields2.put("State", "CLOSED");
        StatePlannableItem statePlannableItem2 = new StatePlannableItem(fields2, "State");
        plannableItems.add(statePlannableItem2);


        Map<String, Object> fields3 = new HashMap<>();
        fields3.put("State", "ANALYSIS");
        StatePlannableItem statePlannableItem3 = new StatePlannableItem(fields3, "State");
        plannableItems.add(statePlannableItem3);

        /* Mocking the openStateService dependency */
        when(openStateService.itemIsInOpenState(statePlannableItem1)).thenReturn(true);
        when(openStateService.itemIsInOpenState(statePlannableItem2)).thenReturn(false);
        when(openStateService.itemIsInOpenState(statePlannableItem3)).thenReturn(true);

        List<PlannedItem> result = statePlannableItemSprintService.getNextSprintItems(plannableItems);

        assertTrue(result.get(0).getFields().equals(fields) || result.get(0).getFields().equals(fields3));
        assertTrue(result.get(1).getFields().equals(fields3) || result.get(0).getFields().equals(fields));
        assertEquals(2, result.size());
    }

    @SneakyThrows
    @Test
    void itemIsPlannedForNextSprint() {
        /* Create item1 for input */
        StatePlannableItem item1 = new StatePlannableItem(new HashMap<String, Object>() {{
            put("State", "OPEN");
        }}, "State");

        /* Create item2 for input */
        StatePlannableItem item2 = new StatePlannableItem(new HashMap<String, Object>() {{
            put("State", "CLOSED");
        }}, "State");

        /* Mocking the openStateService dependency */
        when(openStateService.itemIsInOpenState(item1)).thenReturn(true);
        when(openStateService.itemIsInOpenState(item2)).thenReturn(false);

        /* Call and assert*/
        assertTrue(statePlannableItemSprintService.itemIsPlannedForNextSprint(item1));
        assertFalse(statePlannableItemSprintService.itemIsPlannedForNextSprint(item2));
    }

}
