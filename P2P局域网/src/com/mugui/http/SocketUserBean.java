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

	// 用户的socket类型
	// udp彻底断绝的，无法使用udp连接外网
	public static final int TYPE_UN_CONNECT = 0;
	// post和host 连接一直改变的udpSocket，只可通过类型为TYPE_CONSTANT_CONNECT进行沟通的;
	public static final int TYPE_VARIETY_CONNECT = 1;
	// post和host都不发生改变的，可直接作为服务器使用udpsocket;
	public static final int TYPE_CONSTANT_CONNECT = 2;
	// ip地址不变,但是端口发生变化的，可通过TYPE_CONSTANT_CONNECT沟通，同种类型可尝试p2p沟通，大部分用户网络都属于此类型
	public static final int TYPE_POST_VARIETY_CONNECT = 3;
	private int socket_type = TYPE_UN_CONNECT;
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
