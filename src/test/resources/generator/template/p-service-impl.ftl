package ${basePackage}.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${basePackage}.mapper.${modelNameUpperCamel}Mapper;
import ${basePackage}.input.${modelNameUpperCamel}Input;
import ${basePackage}.model.${modelNameUpperCamel};
import org.springframework.beans.factory.annotation.Autowired;
import ${basePackage}.service.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


/**
* Created by ${author} on ${date}.
*/
@Service
@Transactional(readOnly = true)
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {

    @Autowired
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Transactional
    @Override
    public int add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        return ${modelNameLowerCamel}Mapper.insert(${modelNameLowerCamel});
    }

    @Transactional
    @Override
    public int deleteById(Integer ${modelNameLowerCamel}Id) {
        return ${modelNameLowerCamel}Mapper.deleteByPrimaryKey(${modelNameLowerCamel}Id);
    }

    @Transactional
    @Override
    public int update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        return ${modelNameLowerCamel}Mapper.updateByPrimaryKey(${modelNameLowerCamel});
    }

    @Override
    public ${modelNameUpperCamel} selectById(Integer ${modelNameLowerCamel}Id) {
        return ${modelNameLowerCamel}Mapper.selectByPrimaryKey(${modelNameLowerCamel}Id);
    }

   <#-- @Override
    public PageInfo<${modelNameUpperCamel}> selectByInput(${modelNameUpperCamel}Input ${modelNameLowerCamel}Input) {
        ${modelNameUpperCamel}Example example=new ${modelNameUpperCamel}Example();

        ${modelNameUpperCamel}Example.Criteria criteria=example.createCriteria();

        //条件查询
        if(${modelNameLowerCamel}Input.getKeywords()!=null&&!${modelNameLowerCamel}Input.getKeywords().equals("")){

        }

        PageHelper.startPage(${modelNameLowerCamel}Input.getPageNum(),${modelNameLowerCamel}Input.getPageSize());

        List<${modelNameUpperCamel}> list=${modelNameLowerCamel}Mapper.selectByExample(example);

        return new PageInfo<>(list);
    }-->
}
