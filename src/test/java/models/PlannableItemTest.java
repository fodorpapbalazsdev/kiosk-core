package models;

import exceptions.FieldNotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PlannableItemTest")
class PlannableItemTest {

    @SneakyThrows
    @Test
    public void createPlannableItem_shouldCreate() {
        Map<String, Object> fields = new HashMap<>();
        Date date = Date.valueOf("2021-02-20");
        fields.put("PCD", date);
        PlannableItem plannableItem = new PlannableItem(fields, "PCD");
        assertTrue(plannableItem.getPlannedDate().equals(date));
    }

    @SneakyThrows
    @Test
    public void createPlannableItem_shouldThrowException() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("PCD", "2021-02-20");
        assertThrows(Exception.class, () -> new PlannableItem(fields, "PCD"));
    }

    @SneakyThrows
    @Test
    public void createPlannableItem_shouldThrowException_2() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("Due Date", Date.valueOf("2021-02-20"));
        assertThrows(FieldNotFoundException.class, () -> new PlannableItem(fields, "PCD"));
    }

}
