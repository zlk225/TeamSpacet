package cn.moling.spacet.core;


import java.util.List;


/**
 * Created by CodeGenerator on 2017/11/07.
 */
public interface Service<T> {
    /**
     * 根据唯一主键查询
     */
    public T selectById(Integer id);

    /**
     * 根据唯一主键，逻辑删除
     */
    public int delLogical(Integer id, Integer userId);

    /**
     * 根据唯一主键，物理删除
     */
    public int deleteById(Integer id);

    /**
     * 新增插入
     */
    public int insertSelective(T t, Integer userId);

    /*
    *  修改
    * */
    public int updateByIdSelective(T t);

    /*
    *  物理查询
    * */
    public List<T> getList(T t);

    /*
    *  物理查询
    * */
    public List<T> getList(String fieldNames, Object... filedValues);
    /*
    *  逻辑查询
    * */
    public List<T> getLogicalList(String fieldNames, Object... filedValues);
    public T getLogicalSingle(String fieldNames, Object... filedValues);
    /*
   *  逻辑查询
   * */
    public List<T> getLogicalList(T t);

    /*
    * 批量插入
    * */
    public int insertBatch(List<T> list);

    public Class getClazz();

    }
