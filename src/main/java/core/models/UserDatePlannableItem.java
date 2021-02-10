package core.models;

import exceptions.FieldNotFoundException;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserDatePlannableItem extends DatePlannableItem {

    @NonNull
    private Long userId;

    public UserDatePlannableItem(DatePlannableItem datePlannableItem, String userFieldName) throws Exception {
        super(datePlannableItem);
        Object fieldValue = fields.get(userFieldName);
        if (fieldValue == null) {
            throw new FieldNotFoundException(userFieldName);
        }
        if (!(fieldValue instanceof Long)) {
            throw new Exception("The given field with name: " + userFieldName + " is not a Long (id).");
        } else {
            this.userId = (Long) fields.get(userFieldName);
        }
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Sprint or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof UserDatePlannableItem)) {
            return false;
        }

        // typecast o to Sprint so that we can compare data members
        UserDatePlannableItem other = (UserDatePlannableItem) o;

        // Compare the data members and return accordingly
        return this.userId.equals(other.userId)
                && this.fields.equals(other.getFields())
                && this.planBaseFieldVale.equals(other.getPlanBaseFieldVale());
    }

}
