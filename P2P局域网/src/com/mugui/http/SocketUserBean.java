package com.mugui.http;

import com.mugui.DataClassLoaderInterface;
import com.mugui.http.pack.UdpBag;
import com.mugui.http.udp.UDPSocket;

/**
 * 用户的socket管理的类，socket 发包，收包，处理包都统一使用此类
 * 
 * @author zx844
 *
 */
public class SocketUserBean {

	private UDPSocket socket = null;
	private DataClassLoaderInterface loader = null;

	/**
	 * 需要初始化一个sokcet网络流
	 * 
	 * @param loader
	 *            代入一个用于申请新对象的classloader
	 */
	public SocketUserBean(DataClassLoaderInterface loader) {
		this.loader = loader;
		com.mugui.http.udp.UdpHandle udpHandle = (com.mugui.http.udp.UdpHandle) loader.loadClassToObject("com.mugui.http.UdpHandle");
		socket = new UDPSocket(-1, udpHandle);
	}

	public void start() {
		socket.receive();
	}

	public DataClassLoaderInterface getClassLoader() {
		return loader;
	}

	public boolean isSocketRun() {
		return socket.isClose();
	}

	public void quit() {
		socket.close();
	}

	public void sendByteArray(UdpBag bag) {
		socket.Send(bag);
	}

}
