package core.models;

import core.models.plannable_item.JAZZPlannableItem;
import exceptions.ItemIsPlannedForSprintNotSupportedException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ItemTypeBTest")
class JAZZPlannableItemTest {

    final Date testDate = Date.valueOf("2021-02-20");

    @Test
    @SneakyThrows
    public void createItemTypeB_shouldCreate() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", testDate);
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        JAZZPlannableItem JAZZPlannableItem = new JAZZPlannableItem(fields, null);

        assertEquals(3, JAZZPlannableItem.getFields().size());
        assertEquals("Dummy Username", JAZZPlannableItem.getFields().get("Assigned User"));
        assertEquals(testDate, JAZZPlannableItem.getFields().get("PCD"));
    }

    @Test
    public void createItemTypeB_shouldThrowItemIsPlannedForSprintNotSupportedException() {
        assertThrows(ItemIsPlannedForSprintNotSupportedException.class, () -> new JAZZPlannableItem(null, null).itemIsPlannedForSprint(null));
    }

}
