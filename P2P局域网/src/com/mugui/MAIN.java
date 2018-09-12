package com.mugui;

import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import com.mugui.bean.User;
import com.mugui.http.SocketUserBean;
import com.mugui.model.CmdModel;
import com.mugui.tool.Other;

public class MAIN {
	protected static final String UI_MANAGER[] = null;
	public static String JARFILEPATH = null;

	static {
		JARFILEPATH = MAIN.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		try {
			JARFILEPATH = URLDecoder.decode(new File(JARFILEPATH).getParent(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// System.out.println(args.length);
		openLog(false);
		DataClassLoaderInterface loader = (DataClassLoaderInterface) new DataClassLoaderInterface().loadClassToObject("com.mugui.tool.DataClassLoader");
		User user = new User();
		System.getProperties().put("user", user);
		DataSaveInterface dataSave = (DataSaveInterface) loader.loadClassToObject("com.mugui.ui.DataSave", loader);
		SocketUserBean bean = (SocketUserBean) loader.loadClassToObject("com.mugui.ui.DataSave", loader);
		user.setDataSave(dataSave);
		user.setSocketUserBean(bean);
		user.run();

	}

	private static void openLog(boolean b) {
		File file = new File(JARFILEPATH + "/log");
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String s[] = name.split("_");
				s = s[1].split("\\.");
				try {
					long time = Long.parseLong(s[0]);
					if (System.currentTimeMillis() - time > 3 * 24 * 60 * 60 * 1000) {
						new File(dir.getPath() + "\\" + name).delete();
					}
				} catch (Exception e) {
					new File(dir.getPath() + "\\" + name).delete();
				}
				return false;
			}
		});
		if (b) {
			init();
			System.setOut(new PrintStream(outputStream));
			System.setErr(new PrintStream(outputStream));
		}
		System.setOut(new PrintStream(System.out) {
			@SuppressWarnings("deprecation")
			@Override
			public void println(String x) {
				super.println(new Date(System.currentTimeMillis()).toLocaleString() + ":" + x);
			}

			@SuppressWarnings("deprecation")
			@Override
			public void println(Object x) {
				super.println(new Date(System.currentTimeMillis()).toLocaleString() + ":" + x);
			}

			@SuppressWarnings("deprecation")
			@Override
			public void println(int x) {
				super.println(new Date(System.currentTimeMillis()).toLocaleString() + ":" + x);
			}

			@SuppressWarnings("deprecation")
			@Override
			public void println(char x) {
				super.println(new Date(System.currentTimeMillis()).toLocaleString() + ":" + x);
			}

		});
	}

	public static BufferedImage image = null;

	static DataOutputStream outputStream = null;
	static Object object = new Object();
	static boolean bool = false;

	private static void init() {
		try {
			if (outputStream == null) {
				synchronized (object) {
					if (!bool) {
						bool = true;
						outputStream = new DataOutputStream(new FileOutputStream(new File(JARFILEPATH + "/log/log_" + System.currentTimeMillis() + ".txt")));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	static boolean exit_bool = false;

	public static void exit() {
		long time = System.currentTimeMillis();
		new Thread(new Runnable() {
			@Override
			public void run() {
				int pid = Other.getDPID();
				CmdModel.关闭应用(pid);
				CmdModel.close();
				exit_bool = true;
			}
		}).start();
		while (!exit_bool && System.currentTimeMillis() - time < 2500) {
			Other.sleep(200);
		}
		try {
			int pid = Other.getDPID();
			CmdModel.关闭应用(pid);
			CmdModel.close();
		} catch (Throwable e) {
			System.exit(0);
		}
	}
}
