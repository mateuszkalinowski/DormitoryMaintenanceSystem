package pl.dormitorymaintenancesystem.enumTranslation;

import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;

public class RequestStatusTranslation {

    public static String translateRequestStatus(TaskStatusEnum taskStatusEnum) {
        if(taskStatusEnum.equals(TaskStatusEnum.REQUEST_WAITING))
            return "Oczekujące";
        else if(taskStatusEnum.equals(TaskStatusEnum.REQUEST_FINISHED))
            return "Zakończone";
        else if(taskStatusEnum.equals(TaskStatusEnum.REQUEST_IN_PROGRESS))
            return "W toku";
        else if(taskStatusEnum.equals(TaskStatusEnum.REQUEST_REJECTED))
            return "Odrzucone";
        else
            return "Stan nieznany";
    }
}
