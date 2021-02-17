package core.models;

import core.models.plannable_item.MKSPlannableItem;
import core.sprint.Sprint;
import exceptions.FieldNotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ItemTypeATest")
class MKSPlannableItemTest {

    final Date testDate = Date.valueOf("2021-02-20");

    @Test
    @SneakyThrows
    public void createItemTypeA_shouldCreate() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", testDate);
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        MKSPlannableItem MKSPlannableItem = new MKSPlannableItem(fields, null);

        assertEquals(3, MKSPlannableItem.getFields().size());
        assertEquals("Dummy Username", MKSPlannableItem.getFields().get("Assigned User"));
        assertEquals(testDate, MKSPlannableItem.getFields().get("PCD"));
    }

    @Test
    @SneakyThrows
    public void createItemTypeA_isPlanned() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", testDate);
        MKSPlannableItem MKSPlannableItem = new MKSPlannableItem(fields, null);

        /* Create test Sprint objects */
        Sprint sprint1 = new Sprint("1", Date.valueOf("2021-02-15"), Date.valueOf("2021-02-28"));
        Sprint sprint2 = new Sprint("2", Date.valueOf("2021-03-1"), Date.valueOf("2021-03-14"));

        assertTrue(MKSPlannableItem.itemIsPlannedForSprint(sprint1));
        assertFalse(MKSPlannableItem.itemIsPlannedForSprint(sprint2));
    }

    @Test
    public void createItemTypeAWithoutPCD_shouldThrowFieldNotfoundException() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        assertThrows(FieldNotFoundException.class, () -> new MKSPlannableItem(fields, null));
    }

    @Test
    public void createItemTypeAWithNotDatePCD_shouldIllegalArgumentException() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", 1);
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        assertThrows(IllegalArgumentException.class, () -> new MKSPlannableItem(fields, null));
    }
}
