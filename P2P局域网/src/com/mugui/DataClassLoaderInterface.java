package com.mugui;


public class DataClassLoaderInterface extends ClassLoader {
	public Object loadClassToObject(String type) {
		try {

			return loadClass(type).newInstance(); 
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	@Override
	public  Class<?> loadClass(String name) {
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
