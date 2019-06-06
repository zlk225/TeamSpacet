package ${basePackage}.controller.${module};

import ${basePackage}.domain.${module}.${modelNameUpperCamel};
import cn.moling.spacet.global.CommandResult;
import ${basePackage}.service.${module}.${modelNameUpperCamel}Service;
import ${basePackage}.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
/**
*
* @author zhanglk @date 2018-11-14
*/
@RestController
@RequestMapping("/${modelNameLowerCamel}")
public class ${modelNameUpperCamel}Controller {
    private final static Logger logger = LoggerFactory.getLogger(${modelNameUpperCamel}Controller.class);

    @Autowired
    ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/add")
    public CommandResult add(${modelNameUpperCamel} ${modelNameLowerCamel}, HttpSession session) {
        int userId = (Integer) session.getAttribute("userId");
        ${modelNameLowerCamel}Service.insertSelective(${modelNameLowerCamel}, userId);
        return new CommandResult(CommandResult.CODEOK, CommandResult.SUCCESSMSG, ${modelNameLowerCamel});
    }

    @PostMapping("/delete")
    public CommandResult delete(@RequestParam Integer id, HttpSession session) {
        int userId = (Integer) session.getAttribute("userId");
        ${modelNameLowerCamel}Service.delLogical(id,userId);
        return new CommandResult(CommandResult.CODEOK, CommandResult.SUCCESSMSG,"");
    }

    @PostMapping("/update")
    public CommandResult update( ${modelNameUpperCamel} ${modelNameLowerCamel}, HttpSession session) {
        int userId = (Integer) session.getAttribute("userId");
        ${modelNameLowerCamel}.setModifyUserId(userId);
        ${modelNameLowerCamel}Service.updateByIdSelective(${modelNameLowerCamel});
        return new CommandResult(CommandResult.CODEOK, CommandResult.SUCCESSMSG,"");
    }


    @GetMapping("/detail")
    public CommandResult detail(@RequestParam Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectById(id);
        return new CommandResult(CommandResult.CODEOK, CommandResult.SUCCESSMSG,${modelNameLowerCamel});
    }

    @GetMapping("/list")
    public CommandResult list(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        CommandResult commandResult = NetPage.getPage(() -> {
            return ${modelNameLowerCamel}Service.getLogicalList(${modelNameLowerCamel});
        });
        return commandResult;
    }

}


