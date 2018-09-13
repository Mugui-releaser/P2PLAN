package com.mugui.bean;

import com.mugui.DataSaveInterface;
import com.mugui.http.SocketUserBean;
import com.mugui.http.pack.UdpBag;

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
		dataSave.init();
		socketUserBean.receive();
		UdpBag bag = new UdpBag();
		bag.setBody("holle world");
		bag.setPort(5100);
		bag.setHost("127.0.0.1");
		socketUserBean.send(bag);
		System.out.println("发送->"+bag);
	}

}
