package core.item_sprint;

import core.models.DatePlannableItem;
import core.models.PlannedItem;
import core.sprint.Sprint;
import core.sprint.SprintService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
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
@DisplayName("DatePlannableItemSprintServiceImpl Test")
class DatePlannableItemSprintServiceTest {

    @Mock
    SprintService sprintService;

    @InjectMocks
    DatePlannableItemSprintServiceImpl itemSprintService;

    @BeforeEach
    void setUp() {
        when(sprintService.getNextSprint()).thenReturn(new Sprint("1", Date.valueOf("2021-02-15"), Date.valueOf("2021-02-28")));
    }

    @SneakyThrows
    @Test
    void getNextSprintItems() {

        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", Date.valueOf("2021-02-20"));

        List<DatePlannableItem> plannableItems = new ArrayList<>();
        DatePlannableItem datePlannableItem1 = new DatePlannableItem(fields, "PCD");
        plannableItems.add(datePlannableItem1);

        Map<String, Object> fields2 = new HashMap<>();
        fields2.put("PCD", Date.valueOf("2021-02-08"));
        DatePlannableItem datePlannableItem2 = new DatePlannableItem(fields2, "PCD");
        plannableItems.add(datePlannableItem2);

        Map<String, Object> fields3 = new HashMap<>();
        fields3.put("PCD", Date.valueOf("2021-02-25"));
        DatePlannableItem datePlannableItem3 = new DatePlannableItem(fields3, "PCD");
        plannableItems.add(datePlannableItem3);

        List<PlannedItem> result = itemSprintService.getNextSprintItems(plannableItems);

        assertTrue(result.get(0).getFields().equals(fields) || result.get(0).getFields().equals(fields3));
        assertTrue(result.get(1).getFields().equals(fields3) || result.get(0).getFields().equals(fields));
        assertEquals(result.size(), 2);

    }

    @SneakyThrows
    @Test
    void itemIsPlannedForNextSprint() {

        /* between */
        DatePlannableItem item = new DatePlannableItem(new HashMap<String, Object>() {{
            put("PCD", Date.valueOf("2021-02-20"));
        }}, "PCD");
        assertTrue(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on startDate */
        item = new DatePlannableItem(new HashMap<String, Object>() {{
            put("PCD", Date.valueOf("2021-02-15"));
        }}, "PCD");
        assertTrue(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on endDate */
        item = new DatePlannableItem(new HashMap<String, Object>() {{
            put("PCD", Date.valueOf("2021-02-28"));
        }}, "PCD");
        assertTrue(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on before 1 day */
        item = new DatePlannableItem(new HashMap<String, Object>() {{
            put("PCD", Date.valueOf("2021-02-14"));
        }}, "PCD");
        assertFalse(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on after 1 day */
        item = new DatePlannableItem(new HashMap<String, Object>() {{
            put("PCD", Date.valueOf("2021-02-29"));
        }}, "PCD");
        assertFalse(itemSprintService.itemIsPlannedForNextSprint(item));
    }

}
