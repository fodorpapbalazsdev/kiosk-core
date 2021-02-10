package core.item_sprint;

import java.util.List;

public interface ItemSprintServiceBase<T, K> {
    List<K> getNextSprintItems(List<T> items);

    boolean itemIsPlannedForNextSprint(T item);
}
