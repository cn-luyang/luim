package cn.luim.platform.uac.service;

public interface PasswordService {

	boolean validatePassword(String accountId, String credential);
}
