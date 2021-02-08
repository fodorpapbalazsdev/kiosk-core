package core.item_sprint;

import core.sprint.SprintService;
import models.PlannableDateField;
import models.Sprint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ItemSprintServiceImplTest")
class ItemSprintServiceImplTest {

    @Mock
    SprintService sprintService;

    @InjectMocks
    ItemSprintServiceImpl itemSprintService;

    @BeforeEach
    void setUp() {
        when(sprintService.getNextSprint()).thenReturn(new Sprint("1", Date.valueOf("2021-02-15"), Date.valueOf("2021-02-28")));
    }

    @Test
    void itemIsPlannedForNextSprint() {

        /* between */
        PlannableDateField item = new PlannableDateField(Date.valueOf("2021-02-20"));
        assertTrue(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on startDate */
        item = new PlannableDateField(Date.valueOf("2021-02-15"));
        assertTrue(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on endDate */
        item = new PlannableDateField(Date.valueOf("2021-02-28"));
        assertTrue(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on before 1 day */
        item = new PlannableDateField(Date.valueOf("2021-02-14"));
        assertFalse(itemSprintService.itemIsPlannedForNextSprint(item));

        /* on after 1 day */
        item = new PlannableDateField(Date.valueOf("2021-02-29"));
        assertFalse(itemSprintService.itemIsPlannedForNextSprint(item));
    }
}
