package com.mugui;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DataClassLoaderInterface extends ClassLoader {
	public Object loadClassToObject(String type, Object... loader) {
		try {
			Class<?> class1 = loadClass(type);
			if (class1 == null)
				return null;

			if (loader == null || loader.length == 0) {
				return class1.newInstance();
			}
			Class<?> te[] = new Class<?>[loader.length];
			for (int i = 0; i < te.length; i++) {
				te[i] = loader[i].getClass();
			}
			Constructor constructor = class1.getConstructor(te);
			if (constructor == null)
				return null;
			return constructor.newInstance(loader);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			return null;
		}
	}

	@Override
	public Class<?> loadClass(String name) {
		try {
			Class<?> class1 = null;
			while ((class1 = super.loadClass(name)) == null) {

			}
			return class1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String dataclasspath = "com/mugui/ui/data/";

	public void setDataClassPath(String datapath) {
		dataclasspath = datapath;
	}

	protected String getDataClassPath() {
		return dataclasspath;
	}
}
