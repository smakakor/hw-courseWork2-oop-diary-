package Diary.TaskRepeatability;

import Diary.Diary;
import Diary.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Daily extends Diary {
    public Daily(String header, String description, TaskType taskType, LocalDateTime taskDateTime) {
        super(header, description, taskType, taskDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.equals(this.getTaskDateTime().toLocalDate()) || localDate.isAfter(this.getTaskDateTime().toLocalDate());
    }

    @Override
    public TaskRepeatability getTaskRepeatability() {
        return TaskRepeatability.DAILY;
    }
}
