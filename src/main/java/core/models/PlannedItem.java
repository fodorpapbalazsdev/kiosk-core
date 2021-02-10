package core.models;

import core.sprint.Sprint;
import lombok.Data;
import lombok.NonNull;

@Data
public class PlannedItem extends PlannedItemBase {

    @NonNull
    final private Sprint sprint;

    public PlannedItem(PlannableItemBase item, Sprint sprint) {
        super(item.fields);
        this.sprint = sprint;
    }

    public PlannedItem(PlannedItem plannedItem) {
        super(plannedItem.getFields());
        this.sprint = plannedItem.getSprint();
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of PlannedItem or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof PlannedItem)) {
            return false;
        }

        // typecast o to PlannedItem so that we can compare data members
        PlannedItem other = (PlannedItem) o;

        // Compare the data members and return accordingly
        return sprint.equals(other.getSprint())
                && fields.equals(other.fields);
    }

}
