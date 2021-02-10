package core.item_sprint;

import core.models.FlagPlannableItem;
import core.models.PlannedItem;
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
@DisplayName("FlagPlannableItemSprintServiceImplTest Test")
class FlagPlannableItemSprintServiceImplTest {

    @Mock
    SprintService sprintService;

    @InjectMocks
    FlagPlannableItemSprintServiceImpl itemSprintService;

    @SneakyThrows
    @Test
    void getNextSprintItems() {

        when(sprintService.getNextSprint()).thenReturn(new Sprint("1", Date.valueOf("2021-02-15"), Date.valueOf("2021-02-28")));

        Map<String, Object> fields = new HashMap<>();
        fields.put("Planned", true);

        List<FlagPlannableItem> plannableItems = new ArrayList<>();
        FlagPlannableItem flagPlannableItem1 = new FlagPlannableItem(fields, "Planned");
        plannableItems.add(flagPlannableItem1);

        Map<String, Object> fields2 = new HashMap<>();
        fields2.put("Planned", false);
        FlagPlannableItem flagPlannableItem2 = new FlagPlannableItem(fields2, "Planned");
        plannableItems.add(flagPlannableItem2);

        Map<String, Object> fields3 = new HashMap<>();
        fields3.put("Planned", true);
        FlagPlannableItem flagPlannableItem3 = new FlagPlannableItem(fields3, "Planned");
        plannableItems.add(flagPlannableItem3);

        List<PlannedItem> result = itemSprintService.getNextSprintItems(plannableItems);

        assertTrue(result.get(0).getFields().equals(fields) || result.get(0).getFields().equals(fields3));
        assertTrue(result.get(1).getFields().equals(fields3) || result.get(0).getFields().equals(fields));
        assertEquals(result.size(), 2);

    }

    @SneakyThrows
    @Test
    void itemIsPlannedForNextSprint() {

        /* between */
        FlagPlannableItem item = new FlagPlannableItem(new HashMap<String, Object>() {{
            put("Planned", true);
        }}, "Planned");
        assertTrue(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on startDate */
        item = new FlagPlannableItem(new HashMap<String, Object>() {{
            put("Planned", false);
        }}, "Planned");
        assertFalse(itemSprintService.itemIsPlannedForNextSprint(item));
    }
}
