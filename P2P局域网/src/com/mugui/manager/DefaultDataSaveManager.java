package com.mugui.manager;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.mugui.DataSaveInterface;
import com.mugui.ManagerInterface;
import com.mugui.ui.DataSave;
public class DefaultDataSaveManager implements ManagerInterface {

	private ConcurrentHashMap<String, DataSaveInterface> modelMap = null;

	@Override
	public synchronized DataSaveInterface get(String name) {
		DataSaveInterface modelInterface = modelMap.get(name);
		if (modelInterface != null) {
			return modelInterface;
		}
		modelInterface = (DataSaveInterface) DataSave.loader.loadClassToObject(name);
		if (modelInterface == null)
			throw new NullPointerException("not find " + name);
		add(name, modelInterface);
		return modelInterface;
	}

	@Override
	public synchronized DataSaveInterface del(String name) {
		return modelMap.remove(name);
	}

	@Override
	public synchronized void clearAll() {
		Iterator<Entry<String, DataSaveInterface>> iterator = modelMap.entrySet().iterator();
		while (iterator.hasNext()) {
			iterator.next().getValue().quit();
			iterator.remove();
		}
		modelMap.clear();
	}

	@Override
	public void init() {
		if (modelMap == null)
			modelMap = new ConcurrentHashMap<>();
	}

	@Override
	public void add(String name, Object object) {
		modelMap.put(name, (DataSaveInterface) object);
		return;
	}

}
