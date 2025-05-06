package DsPlateform.Visualization.repository;

import DsPlateform.Visualization.entity.Code;
import DsPlateform.Visualization.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Integer> {

    List<Code> findAllByUser(Optional<UserInfo> user);
}
