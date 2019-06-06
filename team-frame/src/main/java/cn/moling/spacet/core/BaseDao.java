package cn.moling.spacet.core;

import java.util.List;

public interface BaseDao<T> {

    public T selectByPrimaryKey(Integer id);

    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(T t);

    public int updateByPrimaryKeySelective(T t);

    public List<T> getList(T t);

    public int insertBatch(List<T> list);

}
