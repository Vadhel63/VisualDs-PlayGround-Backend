package DsPlateform.Visualization.service;

import DsPlateform.Visualization.entity.Code;
import DsPlateform.Visualization.entity.UserInfo;
import DsPlateform.Visualization.repository.CodeRepository;
import DsPlateform.Visualization.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeRepository _codeRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public boolean AddCode(Code code, int userId) {
        Optional<UserInfo> userOptional = userInfoRepository.findById(userId);
        if (userOptional.isPresent()) {
            code.setUser(userOptional.get());
            _codeRepository.save(code);
            return true;
        }
        return false;
    }

    @Override
    public boolean UpdateCode(int id, Code UpdatedCode) {
        Optional<Code> codeOptional = _codeRepository.findById(id);
        if (codeOptional.isPresent()) {
            Code code = codeOptional.get();
            code.setCodeDescription(UpdatedCode.getCodeDescription());
            code.setDate(UpdatedCode.getDate());
            code.setCodeTitle(UpdatedCode.getCodeTitle());
            code.setWrittenCode(UpdatedCode.getWrittenCode());
            _codeRepository.save(code);
            return true;
        }
        return false;
    }

    @Override
    public Code GetCodeById(int id) {
        return _codeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Code> GetAllCode() {
        return _codeRepository.findAll();
    }
}
