package com.computer.subscribe.service.construct;

import com.computer.subscribe.pojo.TUser;

/**
 * 接口的封装
 * 
 * @author user
 *
 */
public class AssemblyBean {
	/**
	 * 
	 * @param cmd
	 * @param oldTblData
	 * @param newSubmitParam
	 * @return
	 */
	public TUser getUserCommandEntity(IBuildEntityCommand<TUser> cmd,
			TUser oldTblData, TUser newSubmitParam) {
		TUser buildBean = cmd.buildBean(oldTblData, newSubmitParam);
		System.err.println(
				this.getClass() + "--getUserCommandEntity..buildBean=" + buildBean);
		return buildBean;
	}

}
