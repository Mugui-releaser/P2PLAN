package com.mugui;


public interface ManagerInterface {
	public void init();

	public Object get(String name);

	public Object del(String name);

	public void add(String name, Object object);

	public void clearAll();
}
