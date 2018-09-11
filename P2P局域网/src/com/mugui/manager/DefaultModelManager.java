package com.mugui.manager;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.mugui.ModelInterface;
import com.mugui.model.ModelManagerInterface;
import com.mugui.ui.DataSave;

public class DefaultModelManager implements ModelManagerInterface {

	private  ConcurrentHashMap<String, ModelInterface> modelMap = null;

	@Override
	public synchronized ModelInterface get(String name) {
		ModelInterface modelInterface = modelMap.get(name);
		if (modelInterface != null) {
			return modelInterface;
		}
		modelInterface = (ModelInterface) DataSave.loader.loadClassToObject( name);
		if(modelInterface==null) throw new NullPointerException("not find "+name);
		add(name, modelInterface);
		return modelInterface;
	}

	@Override
	public synchronized ModelInterface del(String name) {
		return modelMap.remove(name);
	}

	
	@Override
	public synchronized void clearAll() {
		Iterator<Entry<String, ModelInterface>> iterator = modelMap.entrySet().iterator();
		while (iterator.hasNext()) {
			iterator.next().getValue().stop();
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
		modelMap.put(name, (ModelInterface) object);
		return;
	}

}
