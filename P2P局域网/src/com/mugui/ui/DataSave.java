package com.mugui.ui;

import com.mugui.DataSaveInterface;
import com.mugui.MAIN;
import com.mugui.http.Bean.UserBean;
import com.mugui.model.ModelManagerInterface;
import com.mugui.tool.DataClassLoader;

public class DataSave implements DataSaveInterface {
	/**
	 * 此处静态变量用于对早期代码的支持
	 */
	public static final String JARFILEPATH = MAIN.JARFILEPATH;
	public static DataClassLoader loader = null;
	public static boolean 兼容 = false;
	public static boolean 鼠标修正 = false;
	public static UserBean userBean = null;

	public DataSave(DataClassLoader loader) {
		DataSave.loader = loader;
	}

	/**
	 * 初始化一个ui管理器，一个model管理器
	 */
	@Override
	public Object init() {
		if (uimanager == null)
			uimanager = (UIManagerInterface) loader.loadClassToObject("com.mugui.ui.UIManager");
		if (modelManager == null)
			modelManager = (ModelManagerInterface) loader.loadClassToObject("com.mugui.model.ModelManager");
		return null;
	}

	/**
	 * 退出应该清理相应的类
	 */
	@Override
	public Object quit() {
		if (uimanager != null)
			uimanager.clearAll();
		if (modelManager != null)
			modelManager.clearAll();
		return null;
	}

	/**
	 * 开始
	 */
	@Override
	public Object start() {
		if (uimanager != null || modelManager == null)
			throw new NullPointerException("请先调用Init进行初始化");
		uimanager.init();
		modelManager.init();
		return null;
	}

	private UIManagerInterface uimanager = null;

	@Override
	public UIManagerInterface getUIManager() {
		return uimanager;
	}

	private ModelManagerInterface modelManager = null;

	@Override
	public ModelManagerInterface getModelManager() {
		return modelManager;
	}

}
