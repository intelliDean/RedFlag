import model.PeriodCalculator;
import service.PeriodService;
import service.PeriodServiceImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    private static final PeriodService service = new PeriodServiceImpl();

    public static void main(String[] args) {
        print("""
                Hello, welcome!
                To track your next period, please fill form below with accurate information""");

        String inputDate = inputIn("Please enter the start date of the last period e.g, 23/4/2023");
        int numberOfDays = Integer.parseInt(inputIn("How many days did it last?"));
        int menstrualCycle = Integer.parseInt(inputIn("What is your menstrual cycle?"));

        LocalDate date = parseDate(inputDate);

        int numberOfPeriods = Integer.parseInt(inputIn("How many months periods do you want to calculate?"));
        while (numberOfPeriods > 0) {

            List<String> periodDays = service.getPeriodDays(date, numberOfDays, menstrualCycle);
            List<String> fertilityDays = service.getFertilityDays(date, menstrualCycle);
            String ovulationDay = service.getOvulationDay(date, menstrualCycle);
            String dueDate = service.getDueDate(date, menstrualCycle);

            PeriodCalculator period = PeriodCalculator.builder()
                    .fertilityDays(fertilityDays)
                    .ovulationDay(ovulationDay)
                    .PeriodDays(periodDays)
                    .pregnancyDueDate(dueDate)
                    .build();
            print(period);

            date = date.plusDays(28);
            numberOfPeriods--;
       }
    }
    private static LocalDate parseDate(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        return LocalDate.parse(inputDate, formatter);
    }
    private static void print(Object prompt) {
        JOptionPane.showMessageDialog(null, prompt);
    }
    private static String inputIn(String prompt) {
        return JOptionPane.showInputDialog(null, prompt);
    }
}