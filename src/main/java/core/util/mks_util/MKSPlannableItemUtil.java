package core.util.mks_util;

import core.models.plannable_item.MKSPlannableItem;

public interface MKSPlannableItemUtil {
    static boolean itemIsInOpenState(MKSPlannableItem item) {
        return MKSItemStates.SPRINT_OPEN_STATES.contains(item.getState());
    }

    static boolean itemIsInCloseState(MKSPlannableItem item) {
        return !itemIsInOpenState(item);
    }
}
