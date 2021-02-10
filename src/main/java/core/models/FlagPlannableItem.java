package core.models;

import exceptions.FieldNotFoundException;

import java.util.Map;

public class FlagPlannableItem extends PlannableItemBase<Boolean> {

    public FlagPlannableItem(Map<String, Object> fields, String planBaseFieldName) throws Exception {
        super(fields);

        Object fieldValue = fields.get(planBaseFieldName);
        if (fieldValue == null) {
            throw new FieldNotFoundException(planBaseFieldName);
        }
        if (!(fieldValue instanceof Boolean)) {
            throw new Exception("The given field with name: " + planBaseFieldName + " is not a Boolean.");
        } else {
            this.planBaseFieldVale = (Boolean) fields.get(planBaseFieldName);
        }
    }

    public FlagPlannableItem(FlagPlannableItem flagPlannableItem) {
        super(flagPlannableItem.getFields());
        this.planBaseFieldVale = flagPlannableItem.getPlanBaseFieldVale();
    }
}
