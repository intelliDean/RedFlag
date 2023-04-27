package model;

import lombok.*;

import java.util.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeriodCalculator {
    private List<String> PeriodDays = new ArrayList<>();
    private List<String> fertilityDays = new ArrayList<>();
    private String ovulationDay;
    private String pregnancyDueDate;
    //private List<String> freeDays = new ArrayList<>();


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Next Period Days = ").append(PeriodDays).append("\n");
        builder.append("Fertility Days = ").append(fertilityDays).append("\n");
        builder.append("Ovulation Day = ").append(ovulationDay).append("\n");
        builder.append("If pregnant during ovulation, Pregnancy Due Date= ").append(pregnancyDueDate).append("\n");
        return builder.toString();
    }
}
