package core.models;

import core.models.Item;
import lombok.Getter;

import java.util.Map;

public abstract class PlannableItemBase<T> extends Item {

    @Getter
    T planBaseFieldVale;

    public PlannableItemBase(Map<String, Object> fields) {
        super(fields);
    }
}
