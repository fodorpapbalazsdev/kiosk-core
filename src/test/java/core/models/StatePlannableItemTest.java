package core.models;

import exceptions.FieldNotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("StatePlannableItemTest")
class StatePlannableItemTest {

    @Test
    @SneakyThrows
    @DisplayName("StatePlannable constructor should create a StatePlannable")
    public void createStatePlannable_shouldCreate() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("State", "OPEN");
        fields.put("Summary", "Lorem ipsum");
        fields.put("Assigned User", "Dummy Username");
        StatePlannableItem statePlannableItem = new StatePlannableItem(fields, "State");
        assertEquals("OPEN", statePlannableItem.getPlanBaseFieldVale());
        assertEquals(statePlannableItem.getFields().size(), 3);
        assertEquals(statePlannableItem.getFields().get("Assigned User"), "Dummy Username");
    }

    @Test
    @SneakyThrows
    @DisplayName("createStatePlannableshould throw Exception when field type is not correct")
    public void createStatePlannable_shouldThrowException() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("State", 1); // Note that the given field value is int, not String
        assertThrows(Exception.class, () -> new StatePlannableItem(fields, "State"));
    }

    @Test
    @SneakyThrows
    @DisplayName("createStatePlannable should throw FieldNotFoundException when field not found")
    public void createStatePlannableShouldThrowExceptionWhenGivenFieldNotFound() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("Summary", "Lorem ipsum");
        assertThrows(FieldNotFoundException.class, () -> new StatePlannableItem(fields, "State"));
    }

}
