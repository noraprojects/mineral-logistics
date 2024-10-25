package be.kdg.prog6.parkManagement.adapters.out.db;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class HolidayDto {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private List<Name> name;
    private boolean nationwide;

}

