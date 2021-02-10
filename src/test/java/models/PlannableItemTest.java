package models;

import exceptions.FieldNotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PlannableItemTest")
class PlannableItemTest {

    final Date testDate = Date.valueOf("2021-02-20");

    @Test
    @SneakyThrows
    @DisplayName("PlannableItem constructor should create a PlannableItem")
    public void createPlannableItem_shouldCreate() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", testDate);
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        PlannableItem plannableItem = new PlannableItem(fields, "PCD");
        assertEquals(testDate, plannableItem.getPlannedDate());
        assertEquals(plannableItem.getFields().size(), 3);
        assertEquals(plannableItem.getFields().get("Assigned User"), "Dummy Username");
    }

    @Test
    @SneakyThrows
    @DisplayName("createPlannableItem should throw FieldNotFoundException when field not found")
    public void createPlannableItem_shouldThrowException() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", "2021-02-20");
        assertThrows(Exception.class, () -> new PlannableItem(fields, "PCD"));
    }

    @Test
    @SneakyThrows
    @DisplayName("createPlannableItem should throw FieldNotFoundException when field not found")
    public void createPlannableItemShouldThrowExceptionWhenGivenFieldNotFound() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("Due Date", testDate);
        assertThrows(FieldNotFoundException.class, () -> new PlannableItem(fields, "PCD"));
    }

}
