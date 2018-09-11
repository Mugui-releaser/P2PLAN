package com.mugui.ui;

import com.mugui.DataSaveInterface;
import com.mugui.MAIN;
import com.mugui.http.Bean.UserBean;
import com.mugui.tool.DataClassLoader;

public class DataSave implements DataSaveInterface {

	public static final String JARFILEPATH = MAIN.JARFILEPATH;
	public static DataClassLoader loader;
	public static boolean 兼容 = false;
	public static boolean 鼠标修正 = false;
	public static UserBean userBean = null;

	@Override
	public Object init() {
		return null;
	}

	@Override
	public Object quit() {
		return null;
	}

	@Override
	public Object start() {
		return null;
	}

}
