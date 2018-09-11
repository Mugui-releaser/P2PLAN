package com.mugui.ui;

import com.mugui.DataSaveInterface;
import com.mugui.MAIN;
import com.mugui.http.SocketUserBean;
import com.mugui.http.Bean.UserBean;
import com.mugui.http.pack.Bag;
import com.mugui.http.pack.UdpBag;
import com.mugui.http.udp.UDPSocket;
import com.mugui.http.udp.UdpHandle;
import com.mugui.model.ModelManagerInterface;
import com.mugui.tool.DataClassLoader;

public class DataSave implements DataSaveInterface {

	public static final String JARFILEPATH = MAIN.JARFILEPATH;
	public static DataClassLoader loader = null;
	public static boolean 兼容 = false;
	public static boolean 鼠标修正 = false;
	public static UserBean userBean = null;

	@Override
	public Object init() {
		if (loader == null)
			loader = (DataClassLoader) ((SocketUserBean) System.getProperties().get("SocketUserBean")).getClassLoader();
		if (uimanager == null)
			uimanager = (UIManagerInterface) loader.loadClassToObject("com.mugui.ui.UIManager");
		if (modelManager == null)
			modelManager = (ModelManagerInterface) loader.loadClassToObject("com.mugui.model.ModelManager");

		return null;
	}

	@Override
	public Object quit() {
		return null;
	}

	/**
	 * 开始
	 */
	@Override
	public Object start() {
		
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
