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
			Constructor<?>[] constructors = class1.getConstructors();
			for (Constructor<?> constructor : constructors) {
				if (arrayContentsEq(te, constructor.getParameterTypes())) {
					return constructor.newInstance(loader);
				}
			}
			throw new NoSuchMethodException(class1.getName() + ".<init> is Error");

		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			return null;
		}
	}

	private boolean arrayContentsEq(Class<?>[] loader, Class<?>[] parameterTypes) {
		if (loader == null && parameterTypes == null)
			return false;
		if (loader.length != parameterTypes.length)
			return false;
		Class<?> temp = null;
		for (int i = 0; i < loader.length; i++) {
			temp = loader[i];
			while (temp != parameterTypes[i]) {
				if (temp == Object.class)
					return false;
				temp = temp.getSuperclass();
			}
		}
		return true;
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
