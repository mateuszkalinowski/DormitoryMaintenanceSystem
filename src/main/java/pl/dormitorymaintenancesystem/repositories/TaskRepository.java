package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.enums.RequestStatusEnum;
import pl.dormitorymaintenancesystem.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByStatusEquals(RequestStatusEnum requestStatusEnum);

    List<Task> findAllByWorkerIsNullOrderByTimeStampDesc();
}
