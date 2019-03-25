package ${basePackage}.service;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.input.${modelNameUpperCamel}Input;
import com.github.pagehelper.PageInfo;

/**
* Created by ${author} on ${date}.
*/
public interface ${modelNameUpperCamel}Service {

    /**
    * 增加
    * @param ${modelNameLowerCamel}
    */
    int add(${modelNameUpperCamel} ${modelNameLowerCamel});

    /**
    * 通过id删除
    * @param ${modelNameLowerCamel}Id
    */
    int deleteById(Integer ${modelNameLowerCamel}Id);

    /**
    * 修改
    * @param ${modelNameLowerCamel}
    */
    int update(${modelNameUpperCamel} ${modelNameLowerCamel});

    /**
    * 通过id查找
    * @param ${modelNameLowerCamel}Id
    * @return
    */
    ${modelNameUpperCamel} selectById(Integer ${modelNameLowerCamel}Id);

    /**
    * 通过输入参数分页查找
    * @param ${modelNameLowerCamel}Input
    * @return
    */
    <#--PageInfo<${modelNameUpperCamel}> selectByInput(${modelNameUpperCamel}Input ${modelNameLowerCamel}Input);-->

}
