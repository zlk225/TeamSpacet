package personspace;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertSelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeySelectiveElementGenerator;
import org.mybatis.generator.config.GeneratedKey;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0.0
 */
public class PaginationPlugin extends PluginAdapter {
    /**
     * 生成dao
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseDao<" + introspectedTable.getBaseRecordType()	+ ">");
        FullyQualifiedJavaType imp = new FullyQualifiedJavaType("cn.moling.spacet.core.BaseDao");
        interfaze.addSuperInterface(fqjt);// 添加 extends BaseDao<User>
        interfaze.addImportedType(imp);// 添加import common.BaseDao;
        interfaze.getMethods().clear();
        return true;
    }

    /**
     * 生成实体中每个属性
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return true;
    }

    /**
     * 生成实体
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addSerialVersionUID(topLevelClass, introspectedTable);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    /**
     * 生成mapping
     */
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        return super.sqlMapGenerated(sqlMap, introspectedTable);
    }


    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {

        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();// 数据库表名
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();


        return super.clientInsertSelectiveMethodGenerated(method, interfaze, introspectedTable);
    }
    /**
     * 获取columnNames
     * col1,col2
     * */
    public String getColunNames(IntrospectedTable introspectedTable) {
        String clounNames = introspectedTable.getPrimaryKeyColumns().stream().map(introspectedColumn -> {
            return MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn);

        }).collect(Collectors.joining(", "));
        ;
        return clounNames + "," + introspectedTable.getNonPrimaryKeyColumns().stream().map(introspectedColumn -> {
            return MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn);

        }).collect(Collectors.joining(", "));
   }
    /**
     * 生成mapping 添加自定义sql
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();// 数据库表名
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        XmlElement parentElement = document.getRootElement();

        // 添加sql——where
        XmlElement sql = new XmlElement("sql");
        sql.addAttribute(new Attribute("id", "sql_where"));
        XmlElement where = new XmlElement("where");
        StringBuilder sb = new StringBuilder();
        for (IntrospectedColumn introspectedColumn : introspectedTable.getNonPrimaryKeyColumns()) {
            XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
            sb.setLength(0);
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null"); //$NON-NLS-1$
            isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
            where.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append(" and ");
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = "); //$NON-NLS-1$
            sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
            isNotNullElement.addElement(new TextElement(sb.toString()));
        }
        sql.addElement(where);
        parentElement.addElement(sql);

        //添加getList
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "getList"));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        select.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        String cloumnNames = getColunNames(introspectedTable);

        select.addElement(new TextElement(" select "+cloumnNames+" from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));

        XmlElement include = new XmlElement("include");
        include.addAttribute(new Attribute("refid", "sql_where"));

        select.addElement(include);
        parentElement.addElement(select);

//        /* insertSelect */
        InsertSelectiveElementGenerator insertSelect = new InsertSelectiveElementGenerator();
        insertSelect.setContext(context);
        insertSelect.setIntrospectedTable(introspectedTable);
        insertSelect.addElements(parentElement);
        /*UpdateByPrimaryKeySelectiveElementGenerator */
        UpdateByPrimaryKeySelectiveElementGenerator updateByPrimaryKeySelectiveElementGenerator = new UpdateByPrimaryKeySelectiveElementGenerator();
        updateByPrimaryKeySelectiveElementGenerator.setContext(context);
        updateByPrimaryKeySelectiveElementGenerator.setIntrospectedTable(introspectedTable);
        updateByPrimaryKeySelectiveElementGenerator.addElements(parentElement);

        /*批量插入*/
        addBatchInsert(parentElement, document, introspectedTable, true);

        addGetListByIds(parentElement, document, introspectedTable);
        /*selectByLogicIds*/
        addSelectByLogicIds(parentElement, document, introspectedTable);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private void addGetListByIds(XmlElement parentElement, Document document, IntrospectedTable introspectedTable) {
        //添加getList
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "getListByIds"));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        select.addAttribute(new Attribute("parameterType", "java.util.List"));
                ;
        String cloumnNames = getColunNames(introspectedTable);

        select.addElement(new TextElement(" select " + cloumnNames + " from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));
        String prikey = MyBatis3FormattingUtilities.getEscapedColumnName(introspectedTable.getPrimaryKeyColumns().get(0)) ;
        TextElement where  = new TextElement("where  " + prikey + " in ");
        XmlElement foreach = new XmlElement("foreach ");
        foreach.addAttribute(new Attribute("open ", "("));
        foreach.addAttribute(new Attribute("close ", ")"));

        foreach.addAttribute(new Attribute("collection ", "list"));
        foreach.addAttribute(new Attribute("item ", "item"));
        foreach.addAttribute(new Attribute("index ", "index"));
        foreach.addAttribute(new Attribute("separator  ", ","));
        foreach.addElement( new TextElement("#{item}"));

        select.addElement(where);
        select.addElement(foreach);

        parentElement.addElement(select);

    }

    private void addSelectByLogicIds(XmlElement parentElement, Document document, IntrospectedTable introspectedTable) {


    }

    public void addBatchInsert(XmlElement parentElement, Document document, IntrospectedTable introspectedTable, boolean isSimple) {
        XmlElement answer = new XmlElement("insert"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", "insertBatch")); //$NON-NLS-1$

        FullyQualifiedJavaType parameterType;
        if (isSimple) {
            parameterType = new FullyQualifiedJavaType(
                    introspectedTable.getBaseRecordType());
        } else {
            parameterType = introspectedTable.getRules()
                    .calculateAllFieldsClass();
        }

        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
                "java.util.List"));

        context.getCommentGenerator().addComment(answer);

        GeneratedKey gk = introspectedTable.getGeneratedKey();
        if (gk != null) {
            IntrospectedColumn introspectedColumn = introspectedTable
                    .getColumn(gk.getColumn());
            // if the column is null, then it's a configuration error. The
            // warning has already been reported
            if (introspectedColumn != null) {
                if (gk.isJdbcStandard()) {
                    answer.addAttribute(new Attribute(
                            "useGeneratedKeys", "true")); //$NON-NLS-1$ //$NON-NLS-2$
                    answer.addAttribute(new Attribute(
                            "keyProperty", introspectedColumn.getJavaProperty())); //$NON-NLS-1$
                    answer.addAttribute(new Attribute(
                            "keyColumn", introspectedColumn.getActualColumnName())); //$NON-NLS-1$
                } else {
                 //   answer.addElement(getSelectKey(introspectedColumn, gk));
                }
            }
        }

        StringBuilder insertClause = new StringBuilder();
        StringBuilder valuesClause = new StringBuilder();

        insertClause.append("insert into "); //$NON-NLS-1$
        insertClause.append(introspectedTable
                .getFullyQualifiedTableNameAtRuntime());
        insertClause.append(" ("); //$NON-NLS-1$

        valuesClause.append(" ("); //$NON-NLS-1$

        List<String> valuesClauses = new ArrayList<String>();
        List<IntrospectedColumn> columns = ListUtilities.removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getAllColumns());
        for (int i = 0; i < columns.size(); i++) {
            IntrospectedColumn introspectedColumn = columns.get(i);

            insertClause.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn));
            introspectedColumn.setJavaProperty("item."+ introspectedColumn.getJavaProperty());
            valuesClause.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn));
            if (i + 1 < columns.size()) {
                insertClause.append(", "); //$NON-NLS-1$
                valuesClause.append(", "); //$NON-NLS-1$
            }

            if (valuesClause.length() > 80) {
                answer.addElement(new TextElement(insertClause.toString()));
                insertClause.setLength(0);
                OutputUtilities.xmlIndent(insertClause, 1);

                valuesClauses.add( valuesClause.toString());
                valuesClause.setLength(0);
                OutputUtilities.xmlIndent(valuesClause, 1);
            }
        }

        insertClause.append(')');
        answer.addElement(new TextElement(insertClause.toString()));

        valuesClause.append(')');


        valuesClauses.add(valuesClause.toString());

        for (String clause : valuesClauses) {
           // answer.addElement(new TextElement(clause));
        }
        //forEach
        XmlElement foreach = new XmlElement("foreach ");
        foreach.addAttribute(new Attribute("collection ", "list"));
        foreach.addAttribute(new Attribute("item ", "item"));
        foreach.addAttribute(new Attribute("index ", "index"));
        foreach.addAttribute(new Attribute("separator  ", ","));
        for (String clause : valuesClauses) {
            foreach.addElement(new TextElement(clause));
        }
        answer.addElement(new TextElement("values "));
        answer.addElement(foreach);
        //if (context.getPlugins().sqlMapInsertElementGenerated(answer,
        //        introspectedTable)) {
            parentElement.addElement(answer);
       // }
    }
    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
                                                                        IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {
        // LIMIT5,10; // 检索记录行 6-15
        //		XmlElement isNotNullElement = new XmlElement("if");//$NON-NLS-1$
        //		isNotNullElement.addAttribute(new Attribute("test", "limitStart != null and limitStart >=0"));//$NON-NLS-1$ //$NON-NLS-2$
        // isNotNullElement.addElement(new
        // TextElement("limit ${limitStart} , ${limitEnd}"));
        // element.addElement(isNotNullElement);
        // LIMIT 5;//检索前 5个记录行
        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /**
     * mapping中添加方法
     */
    // @Override
    public boolean sqlMapDocumentGenerated2(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();// 数据库表名
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        // 添加sql
        XmlElement sql = new XmlElement("select");

        XmlElement parentElement = document.getRootElement();
        XmlElement deleteLogicByIdsElement = new XmlElement("update");
        deleteLogicByIdsElement.addAttribute(new Attribute("id", "deleteLogicByIds"));
        deleteLogicByIdsElement
                .addElement(new TextElement(
                        "update "
                                + tableName
                                + " set deleteFlag = #{deleteFlag,jdbcType=INTEGER} where id in "
                                + " <foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach> "));

        parentElement.addElement(deleteLogicByIdsElement);
        XmlElement queryPage = new XmlElement("select");
        queryPage.addAttribute(new Attribute("id", "queryPage"));
        queryPage.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        queryPage.addElement(new TextElement("select "));

        XmlElement include = new XmlElement("include");
        include.addAttribute(new Attribute("refid", "Base_Column_List"));

        queryPage.addElement(include);
        queryPage.addElement(new TextElement(" from " + tableName + " ${sql}"));
        parentElement.addElement(queryPage);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private void addSerialVersionUID(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(new FullyQualifiedJavaType("long"));
        field.setStatic(true);
        field.setFinal(true);
        field.setName("serialVersionUID");
        field.setInitializationString("1L");
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
    }

    /*
     * Dao中添加方法
     */
    private Method generateDeleteLogicByIds(Method method, IntrospectedTable introspectedTable) {
        Method m = new Method("deleteLogicByIds");
        m.setVisibility(method.getVisibility());
        m.setReturnType(FullyQualifiedJavaType.getIntInstance());
        m.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "deleteFlag", "@Param(\"deleteFlag\")"));
        m.addParameter(new Parameter(new FullyQualifiedJavaType("Integer[]"), "ids", "@Param(\"ids\")"));
        context.getCommentGenerator().addGeneralMethodComment(m, introspectedTable);
        return m;
    }

    /*
     * 实体中添加属性
     */
    private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(FullyQualifiedJavaType.getIntInstance());
        field.setName(name);
        field.setInitializationString("-1");
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    public boolean validate(List<String> arg0) {
        return true;
    }

    public static void generate() {
        String config = PaginationPlugin.class.getClassLoader().getResource("mybatisConfig.xml").getFile();
        String[] arg = { "-configfile", config, "-overwrite" };
        ShellRunner.main(arg);
    }

    public static void main(String[] args) {
        generate();
    }
}