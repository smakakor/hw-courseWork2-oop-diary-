import Diary.Diary;
import Diary.Schedule;
import Diary.TaskRepeatability.Daily;
import Diary.TaskRepeatability.Yearly;
import Diary.TaskType;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;

public class Main {

        private static final Schedule SCHEDULE = new Schedule();
        private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d.M.yyyy");
        private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SCHEDULE.addTask(new Daily("test", "test test test test", TaskType.PERSONAL, LocalDateTime.now()));
        SCHEDULE.addTask(new Yearly("test", "test test test test", TaskType.WORK, LocalDateTime.now()));
        printTaskForDate(scanner);
        SCHEDULE.deleteTask(1);
        printTaskForDate(scanner);
    }


    private static void printTaskForDate(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        Collection<Diary> taskForDate = SCHEDULE.getTasksForDate(localDate);
        System.out.println("Задачи на " + localDate.format(DATE_FORMAT));
        for (Diary diary :
                taskForDate) {
            System.out.printf("[%s]%s: %s (%s)%n",localizeType(diary.getTaskType()),diary.getHeader(),diary.getTaskDateTime().format(TIME_FORMAT),diary.getDescription() );
        }
    }

    private static LocalDate readDate(Scanner scanner) {
        while (true) {
            try {

                System.out.println("Дата задчи(dd.MM.yyyy): ");
                String dateLine = scanner.nextLine();
                return LocalDate.parse(dateLine, DATE_FORMAT);
            } catch (DateTimeException e) {
                System.out.println("Дата введена не корректно");
            }
        }
    }


    public static String localizeType (TaskType taskType) {
        String result = null;
         switch (taskType) {
            case WORK:
                result = "Рабочая задача";
                break;
            case PERSONAL:
                result = "Персональная задача";
                break;
        }
         return result;
    }

}