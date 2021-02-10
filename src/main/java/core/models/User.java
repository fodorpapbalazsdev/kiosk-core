package core.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {

    @NonNull
    private final Long id;

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of User or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof User)) {
            return false;
        }

        // typecast o to User so that we can compare data members
        User other = (User) o;

        // Compare the data members and return accordingly
        return this.id.equals(other.id);
    }
}
