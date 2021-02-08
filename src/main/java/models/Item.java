package models;

import lombok.Data;

import java.util.Map;

@Data
public class Item {
    final Map<String, Object> fields;
}
