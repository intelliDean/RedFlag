package service;

import java.time.LocalDate;
import java.util.List;

public interface PeriodService {
    List<String> getPeriodDays(LocalDate date, int numberOfDays, int menstrualCycle);
    List<String> getFertilityDays(LocalDate date, int menstrualCycle);
    String getOvulationDay(LocalDate date, int menstrualCycle);
    String getDueDate(LocalDate date, int menstrualCycle);

}
