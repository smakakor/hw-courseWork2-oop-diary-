package Diary.TaskRepeatability;

import Diary.Diary;
import Diary.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Monthly extends Diary {

    public Monthly(String header, String description, TaskType taskType, LocalDateTime taskDateTime) {
        super(header, description, taskType, taskDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate date = this.getTaskDateTime().toLocalDate();
        return localDate.equals(date) ||
                (localDate.isAfter(date) && localDate.getDayOfMonth() == date.getDayOfMonth());
    }

    @Override
    public TaskRepeatability getTaskRepeatability() {
        return TaskRepeatability.MONTHLY;
    }
}
