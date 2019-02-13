package com.faceghost.elasticbg.base.utils;

import com.faceghost.elasticbg.base.exception.RootException;
import com.faceghost.elasticbg.base.model.SystemUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;


@Component
public class PasswordUtil {
		
    public static final  String algorithName = "md5";
	
	public static  int hashIterations = 2;
	
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	

	public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	
	public void systemUserEncryptPassword(SystemUser user) throws RootException{
		if(ValidateUtil.validateBlank(user.getId())){
			throw new RootException("设置密码前，请先设置ID");
		}
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(
									algorithName,
									user.getPassword(),
									ByteSource.Util.bytes(PasswordUtil.getCredentialsSalt(user)),
									hashIterations).toHex();
		user.setPassword(newPassword);
	}
	
	public static  String getCredentialsSalt(SystemUser user){
		return user.getId() + user.getSalt();
	}

	
	public static String encryptPassword(String password,String id ,String salt){
		String newPassword = new SimpleHash(
				algorithName,
				password,
				ByteSource.Util.bytes(id+salt),
				hashIterations).toHex();
		return newPassword;
	}
	
	
	
	
	/**
	 * @param args
	 * @throws RootException
	 */
	public static void main(String[] args) throws RootException {
		PasswordUtil util = new PasswordUtil();
		SystemUser user = new SystemUser();
		user.setId("1");
		user.setName("admin");
		user.setPassword("admin");
		util.systemUserEncryptPassword(user);
		System.out.println(user.getSalt());
		System.out.println(user.getPassword());
		
	}

}
