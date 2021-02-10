package models;

import exceptions.FieldNotFoundException;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;
import java.util.Map;

public class PlannableItem extends Item {

    @NonNull
    @Getter
    final Date plannedDate;

    public PlannableItem(Map<String, Object> fields, String plannableFieldName) throws Exception {
        super(fields);

        Object fieldValue = fields.get(plannableFieldName);
        if (fieldValue == null) {
            throw new FieldNotFoundException(plannableFieldName);
        }
        if (!(fieldValue instanceof Date)) {
            throw new Exception("The given field with name: " + plannableFieldName + " is not a Date.");
        } else {
            this.plannedDate = (Date) fields.get(plannableFieldName);;
        }
    }
}
