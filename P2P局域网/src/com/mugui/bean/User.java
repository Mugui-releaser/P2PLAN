package com.mugui.bean;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mugui.DataSaveInterface;
import com.mugui.http.SocketUserBean;
import com.mugui.http.pack.UdpBag;
import com.mugui.tool.Other;

/**
 * 一个用户的统一管理类
 * 
 * @author zx844
 *
 */
public class User {
	private DataSaveInterface dataSave = null;
	private SocketUserBean socketUserBean = null;

	public DataSaveInterface getDataSave() {
		return dataSave;
	}

	public void setDataSave(DataSaveInterface dataSave) {
		this.dataSave = dataSave;
	}

	public SocketUserBean getSocketUserBean() {
		return socketUserBean;
	}

	public void setSocketUserBean(SocketUserBean socketUserBean) {
		this.socketUserBean = socketUserBean;
	}

	public void run() {
		// 数据初始化
		dataSave.init();
		SocketUserBean socketUserBean1 = new SocketUserBean(5101, socketUserBean.getClassLoader());
		socketUserBean1.receive();
		socketUserBean.receive();
		// 开始
		dataSave.start();

		//
		// UdpBag bag = new UdpBag();// host:125.71.194.45 post:11585
		// bag.setBody("holle world");
		// bag.setPort(5100);
		// bag.setHost("118.190.26.99");
		// socketUserBean.send(bag);
		// bag.setPort(5101);
		// // while (true) {
		// // Other.sleep(1000);
		// // socketUserBean.send(bag);
		// // }
		// socketUserBean.send(bag);
		// bag.setPort(5100);
		// bag.setHost("39.106.5.144");
		// socketUserBean.send(bag);
		// bag.setPort(5101);
		//
		// socketUserBean.send(bag);
		// // host:125.71.194.45 post:11969 我host:125.71.194.45 post:11969
		// // System.out.println("启动完成");
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(System.in));
		// try {
		// String host = reader.readLine();
		// int post = Integer.parseInt(reader.readLine().trim());
		// bag.setPort(post);
		// bag.setHost(host);
		// socketUserBean.send(bag);
		// System.out.println("发送");
		// bag.setBody(reader.readLine());
		// socketUserBean.send(bag);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

}
