package ${basePackage}.controller;
import ${basePackage}.core.Result;
import ${basePackage}.util.ResultUtil;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.input.${modelNameUpperCamel}Input;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
* Created by ${author} on ${date}.
*/
@Controller
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    /**
    * 增加
    * @param ${modelNameLowerCamel}
    * @return
    */
    @PostMapping("/add")
    @ResponseBody
    public Result add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.add(${modelNameLowerCamel});
        return ResultUtil.success();
    }

    /**
    * 删除
    * @param id
    * @return
    */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultUtil.success();
    }

    /**
    * 修改
    * @param ${modelNameLowerCamel}
    * @return
    */
    @PostMapping("/update")
    @ResponseBody
    public Result update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultUtil.success();
    }

    /**
    * 通过id查找
    * @param id
    * @return
    */
    @PostMapping("/detail")
    @ResponseBody
    public Result detail(Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectById(id);
        return ResultUtil.success(${modelNameLowerCamel});
    }

    /**
    * 分页查找
    * @param input 输入对象
    * @return
    */
    <#--@PostMapping("/list")
    @ResponseBody
    public Result list(${modelNameUpperCamel}Input input) {
        PageInfo<${modelNameUpperCamel}> pageInfo = ${modelNameLowerCamel}Service.selectByInput(input);
        return ResultUtil.success(pageInfo);
    }-->
}
