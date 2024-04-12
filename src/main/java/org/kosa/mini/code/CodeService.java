package org.kosa.mini.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kosa.mini.entity.CodeVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CodeService {
    private final CodeMapper codeMapper;

    public List<CodeVO> getList() {
        return codeMapper.getList();
    }
}
