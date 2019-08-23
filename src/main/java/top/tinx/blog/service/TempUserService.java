package top.tinx.blog.service;

/**
 * 创建人: Wills
 * 创建时间：2019/8/22 22:18
 * 描述:
 */
public interface TempUserService {

    public void insertTempUser(String email,String activationCode);

    public String getRegActivationCode(String email);
}
