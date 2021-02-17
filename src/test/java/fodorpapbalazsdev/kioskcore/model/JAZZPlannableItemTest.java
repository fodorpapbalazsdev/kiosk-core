package fodorpapbalazsdev.kioskcore.model;

import fodorpapbalazsdev.kioskcore.sprint.planning.model.plannable.JAZZPlannableItem;
import fodorpapbalazsdev.kioskcore.sprint.exception.ItemIsPlannedForSprintNotSupportedException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JAZZPlannableItemTest")
class JAZZPlannableItemTest {

    final Date testDate = Date.valueOf("2021-02-20");

    @Test
    @SneakyThrows
    public void createJAZZPlannableItem_shouldCreate() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("Field 1", testDate);
        fields.put("Field 2", "Lorem ipsum");
        fields.put("Field 3", "Lorem ipsum");
        JAZZPlannableItem JAZZPlannableItem = new JAZZPlannableItem(fields, null);

        assertEquals(3, JAZZPlannableItem.getFields().size());
        assertEquals("Lorem ipsum", JAZZPlannableItem.getFields().get("Field 2"));
        assertEquals(testDate, JAZZPlannableItem.getFields().get("Field 1"));
    }

    @Test
    public void itemIsPlannedForSprint_shouldThrowItemIsPlannedForSprintNotSupportedException() {
        assertThrows(ItemIsPlannedForSprintNotSupportedException.class, () -> new JAZZPlannableItem(null, null).itemIsPlannedForSprint(null));
    }

}
