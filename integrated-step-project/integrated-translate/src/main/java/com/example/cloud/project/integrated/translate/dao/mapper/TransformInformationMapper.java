package com.example.cloud.project.integrated.translate.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloud.project.integrated.common.domain.TranslateResponse;
import com.example.cloud.project.integrated.translate.dao.entity.TransformInformation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 14:30
 */
public interface TransformInformationMapper extends BaseMapper<TransformInformation> {
    @Select("select word_length as wordLength,target_value as target from "+TransformInformation.TABLE_NAME+" where del_flag = 0 and source_value =#{source} ")
    TranslateResponse selectBySource(@Param("source")String source);
}
