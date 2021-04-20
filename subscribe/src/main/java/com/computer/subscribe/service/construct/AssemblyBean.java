package com.computer.subscribe.service.construct;

import org.apache.log4j.Logger;

import com.computer.subscribe.pojo.TUser;

/**
 * 接口的封装
 * 
 * @author user
 *
 */
public class AssemblyBean {
	public static Logger logger = Logger.getLogger(AssemblyBean.class);

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

		logger.warn("\n Return.Build.Bean ==" + buildBean);
		return buildBean;
	}

}
