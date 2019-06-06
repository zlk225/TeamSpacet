package cn.moling.spacet.core;

import cn.moling.spacet.utils.AddressUtil;
import cn.moling.spacet.utils.StringUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/11/07.
 */
public abstract class AbstractService<T> implements Service<T> {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    protected BaseDao<T> mapper;

    @Override
    public T selectById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    ;

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    ;

    @Override
    public int insertSelective(T t, Integer userId) {
        return mapper.insertSelective(t);
    }

    @Override
    public int updateByIdSelective(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    ;

    @Override
    public List<T> getList(T t) {
        return mapper.getList(t);
    }

    ;

    @Override
    public int insertBatch(List<T> list) {
        if(list.size() > 0) {
            return mapper.insertBatch(list);
        }
        return 0;
    }
    /**
     * @author  zhangwy
     * @param  fieldNames 以,隔开的字段名称
     * @param fieldValues 字段名称对应的查询的条件
     * 反射注入一个bean对象然后进行查询 避免每次都去new一个新的对象
     * */
    public List<T> getList(String fieldNames,  Object... fieldValues) {
        return getListByArray(fieldNames, fieldValues);
    }
//    private List<T> getListByArray(String fieldNames,  Object[] fieldValues) {
//        Class clazz = this.getClazz();
//        try {
//            T instance = (T)clazz.newInstance();
//            String []  fieldNamesList = fieldNames.split(",");
//            if(fieldNamesList.length != fieldValues.length) {
//                throw new IllegalArgumentException("参数不对");
//            }
//            for (int index = 0 ; index < fieldNamesList.length; index++) {
//
//                String fieldName = fieldNamesList[index];
//                String field = StringUtil.ConvertUpperCamel(fieldName.trim());
//
//
//                Method method = BeanUtils.findDeclaredMethodWithMinimalParameters(clazz, "set" + field) ;
//                method.invoke(instance, fieldValues[index]);
//            }
//            return getList(instance);
//        } catch(InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
private List<T> getListByArray(String fieldNames,  Object[] fieldValues) {
    Class clazz = this.getClazz();
    try {
        T instance = (T)clazz.newInstance();
        String []  fieldNamesList = fieldNames.split(",");
        if(fieldNamesList.length != fieldValues.length) {
            logger.error("参数错误!");
            throw new IllegalArgumentException("参数不对");
        }
        for (int index = 0 ; index < fieldNamesList.length; index++) {

            String fieldName = fieldNamesList[index];
            String field = StringUtil.ConvertLowerCamel(fieldName.trim());
            long address = AddressUtil.getAddress(clazz, field);
            AddressUtil.putObject(instance, address, fieldValues[index]);
        }
        return getList(instance);
    }
    catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
    return null;
}
    public List<T> getLogicalList(String fieldNames,  Object... filedValues ){
        List<Object> list = new ArrayList<>();
        Arrays.stream(filedValues).forEach(field ->{
            list.add(field);
        });
        list.add((byte)0);
        return getListByArray(fieldNames + ", del" ,  list.toArray());
    }

    @Override
    public T getLogicalSingle(String fieldNames,  Object... filedValues ) {
        List<T> list = getLogicalList(fieldNames, filedValues);
        if(list.size() >= 1) {
            return list.get(0);
        }
        return null;
    }
}
