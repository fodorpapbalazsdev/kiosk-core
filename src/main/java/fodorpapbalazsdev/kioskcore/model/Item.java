package fodorpapbalazsdev.kioskcore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Item {
    private final Map<String, Object> fields;
}
