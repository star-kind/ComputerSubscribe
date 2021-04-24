package com.computer.subscribe.service.construct;

import org.springframework.util.StringUtils;

import com.computer.subscribe.pojo.TUser;

/**
 * 实现构建用于修改新用户的接口
 * 
 * @author user
 *
 */
public class BuildUserImpl implements IBuildEntityCommand<TUser> {

	public TUser updateForUser;

	public BuildUserImpl(TUser updateForUser) {
		this.updateForUser = updateForUser;
	}

	@Override
	public TUser buildBean(TUser oldTblData, TUser newSubmitParam) {
		String str = this.getClass().getName() + "__buildBean__\n";
		System.err.println(str + "oldTblData=" + oldTblData.toString()
				+ "\nnewSubmitParam=" + newSubmitParam.toString());

		String oldMailBox = oldTblData.getMailbox();
		String oldPhone = oldTblData.getPhone();
		Integer oldRole = oldTblData.getRole();
		String oldUserName = oldTblData.getUserName();

		String newMailBox = newSubmitParam.getMailbox();
		String newPhone = newSubmitParam.getPhone();
		Integer newRole = newSubmitParam.getRole();
		String newUserName = newSubmitParam.getUserName();

		if (!StringUtils.isEmpty(newMailBox)) {
			if (!newMailBox.equals(oldMailBox)) {
				updateForUser.setMailbox(newMailBox);
			}
		}

		if (!StringUtils.isEmpty(newPhone)) {
			if (!newPhone.equals(oldPhone)) {
				updateForUser.setPhone(newPhone);
			}
		}

		if (newRole != null) {
			if (!newRole.equals(oldRole)) {
				updateForUser.setRole(newRole);
				;
			}
		}

		if (!StringUtils.isEmpty(newUserName)) {
			if (!newUserName.equals(oldUserName)) {
				updateForUser.setUserName(newUserName);
			}
		}

		System.err.println(str + "updateForUser=" + updateForUser);
		return updateForUser;
	}

}
