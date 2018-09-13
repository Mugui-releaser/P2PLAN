package com.mugui.bean;

import com.mugui.DataSaveInterface;
import com.mugui.http.SocketUserBean;

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
		
		
		
		
	}

}
