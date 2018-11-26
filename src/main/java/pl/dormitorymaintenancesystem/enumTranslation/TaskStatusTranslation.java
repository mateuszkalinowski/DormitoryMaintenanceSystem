package pl.dormitorymaintenancesystem.enumTranslation;

import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;

public class TaskStatusTranslation {

    public static String translateTaskStatus(TaskStatusEnum taskStatusEnum) {
        if(taskStatusEnum.equals(TaskStatusEnum.TASK_WAITING))
            return "Oczekujące";
        else if(taskStatusEnum.equals(TaskStatusEnum.TASK_FINISHED))
            return "Zakończone";
        else if(taskStatusEnum.equals(TaskStatusEnum.TASK_IN_PROGRESS))
            return "W toku";
        else if(taskStatusEnum.equals(TaskStatusEnum.TASK_REJECTED))
            return "Odrzucone";
        else
            return "Stan nieznany";
    }
}
