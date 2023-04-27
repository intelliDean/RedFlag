package service;

import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PeriodServiceImpl implements PeriodService {
    private LocalDate localDate;

    @Override
    public List<String> getPeriodDays(LocalDate date, int numberOfDays, int menstrualCycle) {
        getPeriod(date, menstrualCycle);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MMMM/yyyy");
        return Stream.iterate(localDate, dayPlus -> dayPlus.plusDays(1))
                .limit(numberOfDays)
                .map(formatter::format)
                .toList();

        //        for (int i = 1; i <= numberOfDays; i++) {
//            localDate = localDate.plusDays(1);
//            periodDate.add(formatter.format(localDate));
//            System.out.println(formatter.format(localDate));
//        }
        // periodCalculator.getPeriodDays().addAll(periodDate);

        //return periodCalculator.getPeriodDays();

    }

    private void getPeriod(LocalDate date, int menstrualCycle) {
        localDate = date.plusDays(menstrualCycle);
    }

    @Override
    public List<String> getFertilityDays(LocalDate date, int menstrualCycle) {
        getPeriod(date, menstrualCycle);
        localDate = localDate.plusDays(9);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MMMM/yyyy");

        return Stream.iterate(localDate, fertile -> fertile.plusDays(1))
                .limit(7)
                .map(formatter::format)
                .toList();
    }

    @Override
    public String getOvulationDay(LocalDate date, int menstrualCycle) {
        getPeriod(date, menstrualCycle);
        localDate = localDate.plusDays(14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MMMM/yyyy");
        return formatter.format(localDate);
    }

    @Override
    public String getDueDate(LocalDate date, int menstrualCycle) {
        getPeriod(date, menstrualCycle);
        localDate = localDate.plusMonths(9).plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MMMM/yyyy");
        return formatter.format(localDate);
    }


    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2023, 8, 15);
        LocalDate end = LocalDate.of(2024, 5, 21);

        Period period = Period.between(start, end);
        System.out.println(period);
    }
}