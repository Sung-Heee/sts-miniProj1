package org.kosa.mini.code;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.mini.entity.CodeVO;

import java.util.List;

@Mapper
public interface CodeMapper {
    List<CodeVO> getList();
}
