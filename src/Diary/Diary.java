package Diary;

import Diary.TaskRepeatability.TaskRepeatability;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Diary {
    private final String header; // заголовок
    private final String description; // описание
    private final TaskType taskType; // тип задачи (рабочая, личная)
    private final LocalDateTime taskDateTime; // время
    private final int id; // id задачи
    private static int counter = 0; // счетчик

    public Diary(String header, String description, TaskType taskType, LocalDateTime taskDateTime) {
        this.header = header;
        this.description = description;
        this.taskType = taskType;
        this.taskDateTime = taskDateTime;
        this.id = counter++;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public int getId() {
        return id;
    }


    public abstract boolean appearsIn(LocalDate localDate);

    public abstract TaskRepeatability getTaskRepeatability();
}
