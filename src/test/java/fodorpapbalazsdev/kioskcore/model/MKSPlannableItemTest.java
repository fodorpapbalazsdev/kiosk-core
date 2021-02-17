package fodorpapbalazsdev.kioskcore.model;

import fodorpapbalazsdev.kioskcore.sprint.planning.model.plannable.MKSPlannableItem;
import fodorpapbalazsdev.kioskcore.sprint.model.Sprint;
import fodorpapbalazsdev.kioskcore.sprint.util.SprintUtilImpl;
import fodorpapbalazsdev.kioskcore.exception.FieldNotFoundException;
import fodorpapbalazsdev.kioskcore.exception.mks.MKSFieldStateValueNotSupportedException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MKSPlannableItemTest")
class MKSPlannableItemTest {

    private static final String STATE_FIELD_NAME = "State";
    final Date testDate = Date.valueOf("2021-02-20");

    @Test
    @SneakyThrows
    public void createMKSPlannableItem_shouldCreate() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", testDate);
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        fields.put(STATE_FIELD_NAME, "OPEN");
        MKSPlannableItem mksPlannableItem = new MKSPlannableItem(fields, null);
        mksPlannableItem.setSprintUtil(new SprintUtilImpl());
        assertEquals(4, mksPlannableItem.getFields().size());
        assertEquals("Dummy Username", mksPlannableItem.getFields().get("Assigned User"));
        assertEquals(testDate, mksPlannableItem.getFields().get("PCD"));
    }

    @Test
    @SneakyThrows
    public void createMKSPlannableItem_isPlanned() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", testDate);
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        fields.put(STATE_FIELD_NAME, "OPEN");
        MKSPlannableItem mksPlannableItem = new MKSPlannableItem(fields, null);
        mksPlannableItem.setSprintUtil(new SprintUtilImpl());

        /* Create test Sprint objects */
        Sprint sprint1 = new Sprint("1", Date.valueOf("2021-02-15"), Date.valueOf("2021-02-28"));
        Sprint sprint2 = new Sprint("2", Date.valueOf("2021-03-1"), Date.valueOf("2021-03-14"));

        assertTrue(mksPlannableItem.itemIsPlannedForSprint(sprint1), "Item with State: " + mksPlannableItem.getState() + " and with PCD: " + mksPlannableItem.getFields().get("PCD") + " is planned for sprint: " + sprint1);
        assertFalse(mksPlannableItem.itemIsPlannedForSprint(sprint2), "Item with State: " + mksPlannableItem.getState() + " and with PCD: " + mksPlannableItem.getFields().get("PCD") + " is not planned for sprint: " + sprint1);
    }

    @Test
    @SneakyThrows
    public void createMKSPlannableItem_isNotPlanned() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", testDate);
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        fields.put(STATE_FIELD_NAME, "CLOSED");
        MKSPlannableItem mksPlannableItem = new MKSPlannableItem(fields, null);
        mksPlannableItem.setSprintUtil(new SprintUtilImpl());

        /* Create test Sprint objects */
        Sprint sprint1 = new Sprint("1", Date.valueOf("2021-02-15"), Date.valueOf("2021-02-28"));
        Sprint sprint2 = new Sprint("2", Date.valueOf("2021-03-1"), Date.valueOf("2021-03-14"));

        assertFalse(mksPlannableItem.itemIsPlannedForSprint(sprint1), "Item with State: " + mksPlannableItem.getState() + " and with PCD: " + mksPlannableItem.getFields().get("PCD") + " is not planned for sprint: " + sprint1);
        assertFalse(mksPlannableItem.itemIsPlannedForSprint(sprint2), "Item with State: " + mksPlannableItem.getState() + " and with PCD: " + mksPlannableItem.getFields().get("PCD") + " is not planned for sprint: " + sprint2);
    }

    @Test
    public void createMKSPlannableItemWithoutPCD_shouldThrowFieldNotfoundException() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        fields.put(STATE_FIELD_NAME, "OPEN");
        assertThrows(FieldNotFoundException.class, () -> new MKSPlannableItem(fields, null));
    }

    @Test
    public void createMKSPlannableItemWithNotDatePCD_shouldIllegalArgumentException() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", 1);
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        fields.put(STATE_FIELD_NAME, "OPEN");
        assertThrows(IllegalArgumentException.class, () -> new MKSPlannableItem(fields, null));
    }

    @Test
    public void createMKSPlannableItemWithNotStringState_shouldThrowIllegalArgumentException() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", testDate);
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        fields.put(STATE_FIELD_NAME, 1);
        assertThrows(IllegalArgumentException.class, () -> new MKSPlannableItem(fields, null));
    }

    @Test
    public void createMKSPlannableItemWithNotSupportedState_shouldThrowMKSFieldStateValueNotSupportedException() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", testDate);
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        fields.put(STATE_FIELD_NAME, "ASDASD");
        assertThrows(MKSFieldStateValueNotSupportedException.class, () -> new MKSPlannableItem(fields, null));
    }
}
