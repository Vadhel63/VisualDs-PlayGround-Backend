package DsPlateform.Visualization.service;

import DsPlateform.Visualization.entity.Code;
import java.util.List;

public interface CodeService {
    boolean AddCode(Code code, int userId);
    Code UpdateCode(int id, Code UpdatedCode);
    Code GetCodeById(int id);
    List<Code> GetAllCode();

    List<Code> getCodesByUsername(String usernameOrEmail);

    boolean AddCodeByEmail(Code code, String usernameOrEmail);
}
