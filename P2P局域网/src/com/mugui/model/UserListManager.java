package com.mugui.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.mugui.ModelInterface;
import com.mugui.bean.User;
import com.mugui.http.SocketUserBean;
import com.mugui.http.UdpHandle;
import com.mugui.http.pack.UdpBag;
import com.mugui.tool.Other;

public class UserListManager implements ModelInterface {

	private HashMap<String, ManagerBean> hashmap = null;
	private User user = null;
	private SocketUserBean socketuserbean = null;
	private Object key = new Object();

	private class ManagerBean {
		long time;
		long lasttime;
		UdpBag bag;
	}

	@Override
	public void init() {
		if (user == null) {
			user = (User) System.getProperties().get("user");
		}
		if (hashmap == null)
			hashmap = new HashMap<String, ManagerBean>() {
				@Override
				public ManagerBean put(String key, ManagerBean value) {
					return super.put(key, value);
				}
			};
		UdpBag bag = new UdpBag();
		bag.setBag_id(UdpHandle.DEFAULT);
		bag.setType(SocketUserBean.TYPE_CONSTANT_CONNECT);
		bag.setHost("118.190.26.99");
		bag.setPort(5100);
		bag.setBody(bag.getHost() + ":" + bag.getPort());
		(socketuserbean = user.getSocketUserBean()).send(bag);
	}

	private Thread ListenerThread = null;
	private boolean isTrue = false;

	@Override
	public void start() {
		if (ListenerThread == null || !ListenerThread.isAlive()) {
			isTrue = true;
			synchronized (key) {
				if (hashmap.isEmpty())
					try {
						key.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			ListenerThread = new Thread(new Runnable() {

				@Override
				public void run() {
					while (isTrue) {
						Iterator<Entry<String, ManagerBean>> managerBeans = hashmap.entrySet().iterator();
						long time = System.currentTimeMillis();
						for (Entry<String, ManagerBean> managerBean = managerBeans.next(); managerBeans.hasNext();) {

							if (time - managerBean.getValue().lasttime > 5 * 60 * 1000) {
								managerBeans.remove();
							} else if (time - managerBean.getValue().time > 30 * 1000) {
								UdpBag bag = new UdpBag();
								bag.setBag_id(UdpHandle.DEFAULT);
								bag.setHost(managerBean.getValue().bag.getHost());
								bag.setPort(managerBean.getValue().bag.getPort());
								bag.setBody(bag.getHost() + ":" + bag.getPort());
								bag.setUser_to(managerBean.getValue().bag.getUser_id());
								socketuserbean.send(bag);
							}
						}
						if (hashmap.isEmpty()) {
							synchronized (key) {
								try {
									key.wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
						Other.sleep(5000);
					}
				}
			});
			ListenerThread.start();
		}
	}

	@Override
	public boolean isrun() {

		return isTrue;
	}

	@Override
	public void stop() {
		synchronized (key) {
			isTrue = false;
			key.notifyAll();
		}
	}

	public void Default(UdpBag bag) {
		System.out.println(bag.getHost() + ":" + bag.getPort() + "->" + bag);
		ManagerBean managerBean = hashmap.get(bag.getUser_id());
		if (managerBean != null) {
			UdpBag temp = managerBean.bag;
			if (System.currentTimeMillis() - managerBean.time < 30 * 1000 && temp.getHost() == bag.getHost() && temp.getPort() == bag.getPort()) {
				return;
			}
		} else {
			managerBean = new ManagerBean();
		}
		managerBean.time = System.currentTimeMillis();
		managerBean.lasttime = managerBean.time;
		managerBean.bag = bag;
		hashmap.put(bag.getUser_id(), managerBean);
		UdpBag bag2 = new UdpBag();
		bag2.setUser_to(bag.getUser_id());
		bag2.setUser_id(UdpBag.USER_CODE);
		bag2.setPort(bag.getPort());
		bag2.setHost(bag.getHost());
		bag2.setBody(bag.getHost() + ":" + bag.getPort());
		socketuserbean.send(bag2);
		synchronized (key) {
			key.notifyAll();
		}
	}

}
