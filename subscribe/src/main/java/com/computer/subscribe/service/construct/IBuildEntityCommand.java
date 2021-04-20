package com.computer.subscribe.service.construct;

/**
 * 命令模式,消除过多的if..else..if分支语句
 * 
 * @author user
 * @param <E>
 *
 */
public interface IBuildEntityCommand<E> {
	/**
	 * 构建用于修改的实体对象
	 * 
	 * @param <E>
	 * @param oldTblData
	 * @param newSubmitParam
	 * @return
	 */
	E buildBean(E oldTblData, E newSubmitParam);
}
