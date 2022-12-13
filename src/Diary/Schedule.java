package Diary;

import java.time.LocalDate;
import java.util.*;

public class Schedule {
    private final Map<Integer, Diary> task = new HashMap<>();


    public void addTask(Diary diary) {
        task.put(diary.getId(),diary);
    }

    public void deleteTask(int id) {
        this.task.remove(id);

    }

    public Collection<Diary> getTasksForDate(LocalDate date) {
        List<Diary> tasksForDate = new ArrayList<>();
        for (Diary diary :
                task.values()) {
            if (diary.appearsIn(date)) {
                tasksForDate.add(diary);
            }
            
        }

        return tasksForDate;

    }

}
