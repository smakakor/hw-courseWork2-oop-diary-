package Diary.TaskRepeatability;

import Diary.Diary;
import Diary.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Yearly extends Diary {
    public Yearly(String header, String description, TaskType taskType, LocalDateTime taskDateTime) {
        super(header, description, taskType, taskDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate date = this.getTaskDateTime().toLocalDate();
        return localDate.equals(date) || (localDate.isAfter(date) && localDate.getDayOfYear() == date.getDayOfYear());
    }

    @Override
    public TaskRepeatability getTaskRepeatability() {
        return TaskRepeatability.YEARLY;
    }
}
