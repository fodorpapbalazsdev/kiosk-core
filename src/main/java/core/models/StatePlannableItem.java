package core.models;

import exceptions.FieldNotFoundException;

import java.util.Map;

public class StatePlannableItem extends PlannableItemBase<String>{

    public StatePlannableItem(Map<String, Object> fields, String planBaseFieldName) throws Exception {
        super(fields);

        Object fieldValue = fields.get(planBaseFieldName);
        if (fieldValue == null) {
            throw new FieldNotFoundException(planBaseFieldName);
        }
        if (!(fieldValue instanceof String)) {
            throw new Exception("The given field with name: " + planBaseFieldName + " is not a String.");
        } else {
            this.planBaseFieldVale = (String) fields.get(planBaseFieldName);
        }
    }

    public StatePlannableItem(StatePlannableItem statePlannableItem) {
        super(statePlannableItem.getFields());
        this.planBaseFieldVale = statePlannableItem.getPlanBaseFieldVale();
    }
}
