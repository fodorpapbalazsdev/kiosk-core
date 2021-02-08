package models;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class PlannableDateField {
    @NonNull
    final Date plannedDate;
}
