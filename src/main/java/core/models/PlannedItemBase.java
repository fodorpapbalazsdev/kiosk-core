package core.models;

import core.models.Item;

import java.util.Map;

public abstract class PlannedItemBase extends Item {
    public PlannedItemBase(Map<String, Object> fields) {
        super(fields);
    }
}
