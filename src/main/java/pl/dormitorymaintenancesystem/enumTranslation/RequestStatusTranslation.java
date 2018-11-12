package pl.dormitorymaintenancesystem.enumTranslation;

import pl.dormitorymaintenancesystem.enums.RequestStatusEnum;

public class RequestStatusTranslation {

    public static String translateRequestStatus(RequestStatusEnum requestStatusEnum) {
        if(requestStatusEnum.equals(RequestStatusEnum.REQUEST_WAITING))
            return "Oczekujące";
        else if(requestStatusEnum.equals(RequestStatusEnum.REQUEST_FINISHED))
            return "Zakończone";
        else if(requestStatusEnum.equals(RequestStatusEnum.REQUEST_IN_PROGRESS))
            return "W toku";
        else if(requestStatusEnum.equals(RequestStatusEnum.REQUEST_REJECTED))
            return "Odrzucone";
        else
            return "Stan nieznany";
    }
}
