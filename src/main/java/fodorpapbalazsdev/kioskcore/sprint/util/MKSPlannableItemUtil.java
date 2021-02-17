package fodorpapbalazsdev.kioskcore.sprint.util;

import fodorpapbalazsdev.kioskcore.model.MKSItemStates;
import fodorpapbalazsdev.kioskcore.sprint.planning.model.plannable.MKSPlannableItem;

public interface MKSPlannableItemUtil {
    static boolean itemIsInOpenState(MKSPlannableItem item) {
        return MKSItemStates.SPRINT_OPEN_STATES.contains(item.getState());
    }

    static boolean itemIsInCloseState(MKSPlannableItem item) {
        return !itemIsInOpenState(item);
    }
}
