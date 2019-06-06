package cn.moling.spacet.auth.dao;

import cn.moling.spacet.auth.domain.SpaceUser;
import cn.moling.spacet.core.BaseDao;
import java.util.List;

public interface SpaceUserMapper extends BaseDao<SpaceUser> {
    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    public SpaceUser getSpaceUserByUserName(String userName);
}