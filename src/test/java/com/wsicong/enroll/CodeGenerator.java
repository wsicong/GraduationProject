package com.wsicong.enroll;


import com.google.common.base.CaseFormat;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.wsicong.enroll.core.ProjectConstant.*;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator {
    //JDBC配置，请修改为你项目的实际配置
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "123456";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    //项目在硬盘上的基础路径
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    //模板位置
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";

    //java文件路径
    private static final String JAVA_PATH = "/src/main/java";
    //资源文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";

    //生成的Service存放路径
    private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);
    //生成的Service实现存放路径
    private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);
    //生成的Controller存放路径
    private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);
    //生成的Input存放路径
    private static final String PACKAGE_PATH_INPUT = packageConvertPath(INPUT_PACKAGE);

    //@author
    private static final String AUTHOR = "Wsicong";
    //@date
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    public static void main(String[] args) {
        /*genCode("admin_user");//管理员用户表*/
        //genCode("user");
        genCode("role");//角色表
        /*genCode("menu");//菜单表*/
/*        genCode("role_menu");//角色—菜单表
        genCode("sys_dict");//字典表*/
        //genCodeByCustomModelName("输入表名","输入自定义Model名称");
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     * @param tableNames 数据表名称...
     */
    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            String modelName=tableNameConvertUpperCamel(tableName);
            genCodeByCustomModelName(tableName, modelName);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCodeByCustomModelName(String tableName,String modelName){
        //genModelAndMapper(tableName, modelName);
        genService(tableName, modelName);
        genController(tableName, modelName);
        genInput(tableName,modelName);
        //genModelAndMapper();
    }

    /**
     * 自动生成model和mapper
     * @param tableName
     * @param modelName
     */
    public static void genModelAndMapper(String tableName,String modelName){

        //设置context
        Context context=new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER,"`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER,"`");

        //设置数据库连接
        JDBCConnectionConfiguration jdbcConnectionConfiguration=new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        //设置generator插件
        PluginConfiguration pluginConfiguration=new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers",MAPPER_INTERFACE_REFERENCE);
        pluginConfiguration.setConfigurationType("");
        context.addPluginConfiguration(pluginConfiguration);

        //设置生成model的位置
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration=new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH+JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(MODEL_PACKAGE);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        //设置生成mapper.xml的位置
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration=new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH+RESOURCES_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage("mapper");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        //设置生成mapper接口的位置
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration=new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH+JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration=new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        if (StringUtils.isNotEmpty(modelName)){
            tableConfiguration.setDomainObjectName(modelName);
        }
        tableConfiguration.setGeneratedKey(new GeneratedKey("id","Mysql",true,null));
        //通过example计数
        tableConfiguration.setCountByExampleStatementEnabled(false);
        //通过example修改
        tableConfiguration.setUpdateByExampleStatementEnabled(false);
        //通过example删除
        tableConfiguration.setDeleteByExampleStatementEnabled(false);
        //通过example查找
        tableConfiguration.setSelectByExampleStatementEnabled(false);
        context.addTableConfiguration(tableConfiguration);


            List<String> warnings;
            MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<String>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        }catch (Exception e){
            throw new RuntimeException("生成Model和Mapper失败",e);
        }

        /*if (generator.getGeneratedJavaFiles().isEmpty()||generator.getGeneratedXmlFiles().isEmpty()){
            throw new RuntimeException("生成Model和Mapper失败："+warnings);
        }*/
        if (StringUtils.isEmpty(modelName)){
            modelName=tableNameConvertUpperCamel(tableName);
        }

        System.out.println(modelName+".java 生成成功");
        System.out.println(modelName+"Mapper.java 生成成功");
        System.out.println(modelName+"Mapper.xml 生成成功");
    }

    /**
     * 自动生成service
     * @param tableName
     * @param modelName
     */
    public static void genService(String tableName,String modelName){
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date" , DATE);
            data.put("author" , AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel" , modelNameUpperCamel);
            data.put("modelNameLowerCamel" , tableNameConvertLowerCamel(tableName));
            data.put("basePackage" , BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("p-service.ftl").process(data, new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("p-service-impl.ftl").process(data, new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        }catch (Exception e){
            throw new RuntimeException("生成Service失败", e);
        }

    }

    /**
     * 自动生成controller
     * @param tableName
     * @param modelName
     */
    public static void genController(String tableName, String modelName){
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("baseRequestMapping",modelNameConvertMappingPath(modelNameUpperCamel));
            data.put("modelNameUpperCamel",modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL,modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);

            File file=new File(PROJECT_PATH+JAVA_PATH+PACKAGE_PATH_CONTROLLER+ modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("p-controller.ftl").process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        }catch (Exception e){
            throw new RuntimeException("生成Controller失败", e);
        }
    }

    /**
     * 自动生成input对象
     * @param tableName
     * @param modelName
     */
    public static void genInput(String tableName, String modelName){
        try{
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_INPUT + modelNameUpperCamel + "Input.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("input.ftl").process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Input.java 生成成功");
        }catch (Exception e){
            throw new RuntimeException("生成Input失败", e);
        }
    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg=new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    //解释：LOWER_UNDERSCORE 常量命名法 不同单词见使用下划线分割的书写方式。
    // LOWER_CAMEL：驼峰式，小写开头，UPPER_CAMEL：大写开头
    private static String tableNameConvertLowerCamel(String tableName){
         return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertMappingPath(String tableName){
        //兼容使用大写的表名
        tableName=tableName.toLowerCase();
        //return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
        return "/"+tableName;
    }

    private static String modelNameConvertMappingPath(String modelName){
        String tableName=CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String packageConvertPath(String packageName){
        return String.format("/%s/",packageName.contains(".") ? packageName.replaceAll("\\.","/") : packageName);
    }
}
