package core.models;

import exceptions.FieldNotFoundException;

import java.util.Date;
import java.util.Map;

public class DatePlannableItem extends PlannableItemBase<Date> {

    public DatePlannableItem(Map<String, Object> fields, String planBaseFieldName) throws Exception {
        super(fields);

        Object fieldValue = fields.get(planBaseFieldName);
        if (fieldValue == null) {
            throw new FieldNotFoundException(planBaseFieldName);
        }
        if (!(fieldValue instanceof Date)) {
            throw new Exception("The given field with name: " + planBaseFieldName + " is not a Date.");
        } else {
            this.planBaseFieldVale = (Date) fields.get(planBaseFieldName);
        }
    }

    public DatePlannableItem(DatePlannableItem datePlannableItem) {
        super(datePlannableItem.getFields());
        this.planBaseFieldVale = datePlannableItem.getPlanBaseFieldVale();
    }
}
