package Diary.TaskRepeatability;

import Diary.Diary;
import Diary.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Weekly extends Diary {
    public Weekly(String header, String description, TaskType taskType, LocalDateTime taskDateTime) {
        super(header, description, taskType, taskDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate date = this.getTaskDateTime().toLocalDate();
        return localDate.equals(date) ||
                (localDate.isAfter(date) && localDate.getDayOfWeek().equals(date.getDayOfWeek()));
    }

    @Override
    public TaskRepeatability getTaskRepeatability() {
        return TaskRepeatability.WEEKLY;
    }

}
