package pl.dormitorymaintenancesystem.enumTranslation;

import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;
import pl.dormitorymaintenancesystem.enums.UserStatusEnum;

public class UserStatusTranslation {

    public static String translateRequestStatus(UserStatusEnum userStatusEnum) {
        if(userStatusEnum.equals(UserStatusEnum.NEW))
            return "Nowe";
        else if(userStatusEnum.equals(UserStatusEnum.ACCEPTED))
            return "Zaakceptowane";
        else if(userStatusEnum.equals(UserStatusEnum.SUSPENDED))
            return "Zawieszone";
        else if(userStatusEnum.equals(UserStatusEnum.DELETED))
            return "Usunięte";

            return "Stan nieznany";
    }
}
