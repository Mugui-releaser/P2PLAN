package com.mugui.http;

import com.mugui.http.pack.Bag;
import com.mugui.http.tcp.TcpSocketUserBean;

public class TcpHandle implements com.mugui.http.tcp.TcpHandle {

	@Override
	public void manage(Bag accpet, TcpSocketUserBean udpSocket) {
		System.out.println(accpet);
		udpSocket.send(accpet);

	}

}
