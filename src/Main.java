import Diary.Diary;
import Diary.Schedule;
import Diary.TaskRepeatability.*;
import Diary.TaskType;

import java.lang.reflect.Constructor;
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
        SCHEDULE.addTask(new Yearly("test", "test test test test", TaskType.WORK, LocalDateTime.now())); // добавляем 2 объекта
        printTaskForDate(scanner); // выводим на тот день который нам надо
        SCHEDULE.deleteTask(1); // удаляем задачу с id 1
        printTaskForDate(scanner); // выводим на тот день который нам надо
        addForConsole(scanner); // Добавляем задачу с помощью консоли
        printTaskForDate(scanner); // выводим на тот день который нам надо
        scanner.nextLine(); // переходим на новую строку
        SCHEDULE.deleteTask(2); // удаляем задачу с id 2
        printTaskForDate(scanner); // выводим на тот день который нам надо
    }


    private static void printTaskForDate(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        Collection<Diary> taskForDate = SCHEDULE.getTasksForDate(localDate);
        System.out.println("Задачи на " + localDate.format(DATE_FORMAT));
        for (Diary diary :
                taskForDate) {
            System.out.printf("[%s]%s: %s (%s)%n", localizeType(diary.getTaskType()), diary.getHeader(), diary.getTaskDateTime().format(TIME_FORMAT), diary.getDescription());
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


    public static String localizeType(TaskType taskType) {
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

    public static void addForConsole(Scanner scanner) {
        System.out.println("Создание новой задачи");
        System.out.println("Введите (цифру) тип задачи (1-WORK, 2-PERSONAL):");
        TaskType taskType = readTaskType(scanner);
        scanner.nextLine();
        System.out.println("Введите заголовок задачи:");
        String scannerHeader = scanner.nextLine();
        System.out.println("Введите описание задачи:");
        String scannerDescription = scanner.nextLine();
        System.out.println("Введите (цифру) повторяемость задачи (1-ONE_TIME, 2-DAILY, 3-WEEKLY, 4-MONTHLY, 5-YEARLY):");

        while (true) {
            int scannerTaskRepeatability = scanner.nextInt();
            switch (scannerTaskRepeatability) {
                case 1:
                    SCHEDULE.addTask(new OneTime(scannerHeader, scannerDescription, taskType, LocalDateTime.now()));
                    return;
                case 2:
                    SCHEDULE.addTask(new Daily(scannerHeader, scannerDescription, taskType, LocalDateTime.now()));
                    return;
                case 3:
                    SCHEDULE.addTask(new Weekly(scannerHeader, scannerDescription, taskType, LocalDateTime.now()));
                    return;
                case 4:
                    SCHEDULE.addTask(new Monthly(scannerHeader, scannerDescription, taskType, LocalDateTime.now()));
                    return;
                case 5:
                    SCHEDULE.addTask(new Yearly(scannerHeader, scannerDescription, taskType, LocalDateTime.now()));
                    return;
                default:
                    System.out.println("Введен не правильная повторяемость задачи \n Выберети однин из типов:");

            }
        }

    }



    public static TaskType readTaskType(Scanner scanner) {
        while (true) {
            int scannerTaskType = scanner.nextInt();
            switch (scannerTaskType) {
                case 1:
                    return TaskType.WORK;
                case 2:
                    return TaskType.PERSONAL;
                default:
                    System.out.println("Введен не правильный тип задачи \n Выберети однин из типов:");

            }
        }
    }



}