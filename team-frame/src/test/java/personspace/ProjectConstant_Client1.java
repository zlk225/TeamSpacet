package personspace;

/**
 * @Auther: zhanglk
 * @Date: 2018/11/15 14:17
 * @Description:
 */
public class ProjectConstant_Client1 {
    public static final String MOUDLE = "auth";
    public static final String BASE_PACKAGE = "cn.moling.spacet.auth";//项目基础包名称，根据自己公司的项目修改

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".domain";//Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao" ;//Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service" ;//Service所在包
    public static final String SERVICE_IMPL_PACKAGE = BASE_PACKAGE + ".service" + ".impl" ;//ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller" ;//Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";//Mapper插件基础接口的完全限定名
}
