package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;
import pl.dormitorymaintenancesystem.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByStatusEquals(TaskStatusEnum taskStatusEnum);

    List<Task> findAllByWorkerIsNullOrderByTimeStampDesc();
}
