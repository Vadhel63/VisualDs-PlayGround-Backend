package DsPlateform.Visualization.service;

import DsPlateform.Visualization.entity.Code;
import DsPlateform.Visualization.entity.UserInfo;
import DsPlateform.Visualization.repository.CodeRepository;
import DsPlateform.Visualization.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Code AddCodeByEmail(Code code, String email) {
        Optional<UserInfo> userOptional = userInfoRepository.findByEmail(email); // Find user by email
        if (userOptional.isPresent()) {
            code.setUser(userOptional.get());
            if (code.getDate() == null) {
                code.setDate(new java.util.Date()); // Set current date and time
            }            _codeRepository.save(code);
            return code;
        }
        return null;

    }


    @Override
    public Code UpdateCode(int id, Code UpdatedCode) {
        Optional<Code> codeOptional = _codeRepository.findById(id);
        if (codeOptional.isPresent()) {
            Code code = codeOptional.get();
            code.setCodeDescription(UpdatedCode.getCodeDescription());
            if (UpdatedCode.getDate() == null) {
                code.setDate(new java.util.Date()); // Set current date and time
            }            code.setCodeTitle(UpdatedCode.getCodeTitle());
            code.setWrittenCode(UpdatedCode.getWrittenCode());
            _codeRepository.save(code);
            return code;
        }
        return null;
    }

    @Override
    public Code GetCodeById(int id) {
        return _codeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Code> GetAllCode() {
        return _codeRepository.findAll();
    }


    public List<Code> getCodesByUsername(String usernameOrEmail) {
        Optional<UserInfo> user = userInfoRepository.findByEmail(usernameOrEmail); // or findByUsername()
        if (user == null) return new ArrayList<>();
        return _codeRepository.findAllByUser(user); // or codeRepository.findByUserId(user.getId())
    }

}
