package core.models.plannable_item;

import core.models.Item;
import core.sprint.Sprint;
import lombok.Data;
import lombok.NonNull;

@Data
public class PlannedItem extends Item {

    @NonNull
    final private Sprint sprint;

    public PlannedItem(Item item, Sprint sprint) {
        super(item.getFields());
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
                && this.getFields().equals(other.getFields());
    }

}
