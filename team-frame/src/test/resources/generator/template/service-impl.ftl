package ${basePackage}.service.impl.${module};

import cn.moling.spacet.core.AbstractService;
import ${basePackage}.dao.${module}.${modelNameUpperCamel}Mapper;
import ${basePackage}.domain.${module}.${modelNameUpperCamel};
import ${basePackage}.service.${module}.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
* Created by CodeGenerator on 2017/11/07.
*/
@Service
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Override
    public int delLogical(Integer id, Integer userId) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        ${modelNameLowerCamel}.set${keyName}(id);
        //设置逻辑删除
        ${modelNameLowerCamel}.setDel((byte)1);
        //设置修改者id
        ${modelNameLowerCamel}.setModifyUserId(userId);
        return ${modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(${modelNameLowerCamel});
    }

    @Override
    public List<${modelNameUpperCamel}> getLogicalList(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        if(${modelNameLowerCamel} == null) {
            ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        }
        ${modelNameLowerCamel}.setDel((byte)0);
        return ${modelNameLowerCamel}Mapper.getList(${modelNameLowerCamel});
    }
    @Override
    public int insertSelective(${modelNameUpperCamel} ${modelNameLowerCamel}, Integer userId) {
        ${modelNameLowerCamel}.setCreateUserId(userId);
        ${modelNameLowerCamel}.setModifyUserId(userId);
        return ${modelNameLowerCamel}Mapper.insertSelective(${modelNameLowerCamel});
    }
    @Override
    public Class getClazz(){
        return ${modelNameUpperCamel}.class;
    }
}