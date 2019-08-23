package top.tinx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tinx.blog.maaper.TempUserMapper;
import top.tinx.blog.service.TempUserService;

/**
 * 创建人: Wills
 * 创建时间：2019/8/22 22:19
 * 描述:
 *
 */
@Service
public class TempServiceImpl implements TempUserService {

    @Autowired
    private TempUserMapper tempUserMapper;

    @Override
    public void insertTempUser(String email, String activationCode) {
        tempUserMapper.insertTempUser(email,activationCode);
    }

    @Override
    public String getRegActivationCode(String email) {
        return tempUserMapper.getRegActivationCode(email);
    }
}
