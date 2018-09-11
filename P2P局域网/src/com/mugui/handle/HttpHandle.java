package com.mugui.handle;

import com.mugui.http.pack.Bag;
import com.mugui.http.udp.UDPSocket;
import com.mugui.http.udp.UdpHandle;

public class HttpHandle implements UdpHandle {

	@Override
	public void manage(Bag accpet, UDPSocket udpSocket) {
		System.out.println("HttpHandle:" + accpet);
		udpSocket.Send(accpet);
	}

	@Override
	public Bag getValue(String key) {
		return null;
	}

}
