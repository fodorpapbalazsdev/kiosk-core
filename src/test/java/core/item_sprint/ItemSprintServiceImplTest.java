package core.item_sprint;

import core.sprint.SprintService;
import lombok.SneakyThrows;
import models.*;
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
@DisplayName("ItemSprintServiceImplTest")
class ItemSprintServiceImplTest {

    @Mock
    SprintService sprintService;

    @InjectMocks
    ItemSprintServiceImpl itemSprintService;

    @BeforeEach
    void setUp() {
        when(sprintService.getNextSprint()).thenReturn(new Sprint("1", Date.valueOf("2021-02-15"), Date.valueOf("2021-02-28")));
    }

    @SneakyThrows
    @Test
    void getNextSprintItems() {

        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", Date.valueOf("2021-02-20"));

        List<PlannableItem> plannableItems = new ArrayList<>();
        PlannableItem plannableItem1 = new PlannableItem(fields, "PCD");
        plannableItems.add(plannableItem1);

        Map<String, Object> fields2 = new HashMap<>();
        fields2.put("PCD", Date.valueOf("2021-02-08"));
        PlannableItem plannableItem2 = new PlannableItem(fields2, "PCD");
        plannableItems.add(plannableItem2);

        Map<String, Object> fields3 = new HashMap<>();
        fields3.put("PCD", Date.valueOf("2021-02-25"));
        PlannableItem plannableItem3 = new PlannableItem(fields3, "PCD");
        plannableItems.add(plannableItem3);

        List<PlannableItem> result = itemSprintService.getNextSprintItems(plannableItems);

        assertTrue(result.contains(plannableItem1));
        assertTrue(result.contains(plannableItem3));

        assertFalse(result.contains(plannableItem2));
    }

    @SneakyThrows
    @Test
    void itemIsPlannedForNextSprint() {

        /* between */
        PlannableItem item = new PlannableItem( new HashMap<String, Object>() {{
            put("PCD", Date.valueOf("2021-02-20"));
        }}, "PCD");
        assertTrue(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on startDate */
        item = new PlannableItem( new HashMap<String, Object>() {{
            put("PCD", Date.valueOf("2021-02-15"));
        }}, "PCD");
        assertTrue(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on endDate */
        item = new PlannableItem( new HashMap<String, Object>() {{
            put("PCD", Date.valueOf("2021-02-28"));
        }}, "PCD");
        assertTrue(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on before 1 day */
        item = new PlannableItem( new HashMap<String, Object>() {{
            put("PCD", Date.valueOf("2021-02-14"));
        }}, "PCD");
        assertFalse(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on after 1 day */
        item = new PlannableItem( new HashMap<String, Object>() {{
            put("PCD", Date.valueOf("2021-02-29"));
        }}, "PCD");
        assertFalse(itemSprintService.itemIsPlannedForNextSprint(item));
    }

}
