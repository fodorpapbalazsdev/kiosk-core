package fodorpapbalazsdev.kioskcore.sprint.service;

import fodorpapbalazsdev.kioskcore.sprint.model.Sprint;

public interface SprintService {
    Sprint getNextSprint();

    Sprint getCurrent();
}
