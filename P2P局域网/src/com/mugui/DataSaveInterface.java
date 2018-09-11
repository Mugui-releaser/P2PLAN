package com.mugui;

public interface DataSaveInterface {
	/**
	 * 得到UI管理器
	 * 
	 * @return
	 */
	public ManagerInterface getUIManager();

	/**
	 * 得到某种处理器
	 * 
	 * @return
	 */
	public ManagerInterface getModelManager();

	/**
	 * 初始化
	 */
	public Object init();

	/**
	 * 結束
	 */
	public Object quit();

	/**
	 * 开始
	 */
	public Object start();
}
