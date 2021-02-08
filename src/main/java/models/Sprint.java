package models;

import lombok.Data;

import java.util.Date;

@Data
public class Sprint {
    final String id;
    final Date startDate;
    final Date endDate;
}
