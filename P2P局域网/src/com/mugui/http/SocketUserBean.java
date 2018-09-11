package com.mugui.http;

import com.mugui.DataClassLoaderInterface;
import com.mugui.DataSaveInterface;
import com.mugui.handle.UdpHandle;
import com.mugui.http.pack.UdpBag;
import com.mugui.http.udp.UDPSocket;

public class SocketUserBean {

	private UDPSocket socket = null;
	private DataClassLoaderInterface loader = null;
	private DataSaveInterface dataSave = null;

	public SocketUserBean(DataClassLoaderInterface loader) {
		System.getProperties().put("SocketUserBean", this);
		this.loader = loader;
		socket = new UDPSocket(-1, new UdpHandle());
		dataSave = (DataSaveInterface) loader.loadClassToObject("com.mugui.ui.DataSave");
		dataSave.init();
	}

	public void start() {
		dataSave.start();
		
	}

	public DataClassLoaderInterface getClassLoader() {
		return loader;
	}

	public boolean isSocketRun() {
		return socket.isClose();
	}

	public void quit() {
		socket.close();
		dataSave.quit();
	}

	public void sendByteArray(UdpBag bag) {
		socket.Send(bag);
	}

}
