package core.sprint;

import lombok.Data;

import java.util.Date;

@Data
public class Sprint {
    final String id;
    final Date startDate;
    final Date endDate;

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Sprint or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Sprint)) {
            return false;
        }

        // typecast o to Sprint so that we can compare data members
        Sprint other = (Sprint) o;

        // Compare the data members and return accordingly
        return startDate.equals(other.getStartDate())
                && endDate.equals(other.getEndDate());
    }
}
