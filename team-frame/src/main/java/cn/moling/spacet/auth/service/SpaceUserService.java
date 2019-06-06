package cn.moling.spacet.auth.service;

import cn.moling.spacet.core.Service;
import cn.moling.spacet.auth.domain.SpaceUser;

/**
* Created by zlk on 2019/05/24.
*/
public interface SpaceUserService  extends Service<SpaceUser>{
    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    public SpaceUser getSpaceUserByUserName(String userName);
}
