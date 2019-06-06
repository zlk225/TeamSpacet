package cn.moling.spacet.auth.service.impl;

import cn.moling.spacet.core.AbstractService;
import cn.moling.spacet.auth.dao.SpaceUserMapper;
import cn.moling.spacet.auth.domain.SpaceUser;
import cn.moling.spacet.auth.service.SpaceUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
* Created by zlk on 2019/05/24.
*/
@Service
public class SpaceUserServiceImpl extends AbstractService<SpaceUser> implements SpaceUserService {
    @Resource
    private SpaceUserMapper spaceUserMapper;

    @Override
    public int delLogical(Integer id, Integer userId) {
        SpaceUser spaceUser = new SpaceUser();
        spaceUser.setUserId(id);
        //设置逻辑删除
        spaceUser.setDel((byte)1);
        //设置修改者id
        spaceUser.setModifyUserId(userId);
        return spaceUserMapper.updateByPrimaryKeySelective(spaceUser);
    }

    @Override
    public List<SpaceUser> getLogicalList(SpaceUser spaceUser) {
        if(spaceUser == null) {
            spaceUser = new SpaceUser();
        }
        spaceUser.setDel((byte)0);
        return spaceUserMapper.getList(spaceUser);
    }
    @Override
    public int insertSelective(SpaceUser spaceUser, Integer userId) {
        spaceUser.setCreateUserId(userId);
        spaceUser.setModifyUserId(userId);
        return spaceUserMapper.insertSelective(spaceUser);
    }
    @Override
    public Class getClazz(){
        return SpaceUser.class;
    }
    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    public SpaceUser getSpaceUserByUserName(String userName){
        return spaceUserMapper.getSpaceUserByUserName(userName);
    }
}