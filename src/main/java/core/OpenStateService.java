package core;

import core.models.StatePlannableItem;

public interface OpenStateService {
    boolean itemIsInOpenState(StatePlannableItem statePlannableItem);
}
