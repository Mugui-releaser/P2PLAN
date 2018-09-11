package com.mugui.ui;

import com.mugui.DataSaveInterface;
import com.mugui.MAIN;
import com.mugui.http.Bean.UserBean;
import com.mugui.http.pack.Bag;
import com.mugui.http.pack.UdpBag;
import com.mugui.http.udp.UDPSocket;
import com.mugui.http.udp.UdpHandle;
import com.mugui.tool.DataClassLoader;

public class DataSave implements DataSaveInterface {

	public static final String JARFILEPATH = MAIN.JARFILEPATH;
	public static DataClassLoader loader;
	public static boolean 兼容 = false;
	public static boolean 鼠标修正 = false;
	public static UserBean userBean = null;

	@Override
	public Object init() {
		UDPSocket socket = new UDPSocket(8056, new UdpHandle() {

			@Override
			public void manage(Bag accpet, UDPSocket udpSocket) {
				System.out.println("反馈：" + accpet.toString());
			}

			@Override
			public Bag getValue(String key) {
				return null;
			}
		});
		UDPSocket socket2 = new UDPSocket(8057, new UdpHandle() {

			@Override
			public void manage(Bag accpet, UDPSocket udpSocket) {
				System.out.println("接收并反馈：" + accpet);
				udpSocket.Send(accpet);
				;
			}

			@Override
			public Bag getValue(String key) {
				return null;
			}
		});
		System.out.println("同步");
		UdpBag bag = new UdpBag();
		bag.setHost("127.0.0.1");
		bag.setPort(8057);
		bag.setBody("holle world");
		socket.Send(bag);
		return null;
	}

	@Override
	public Object quit() {
		return null;
	}

	@Override
	public Object start() {
		return null;
	}

}
