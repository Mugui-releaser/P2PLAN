package com.mugui.handle;

import com.mugui.http.pack.Bag;
import com.mugui.http.pack.UdpBag;
import com.mugui.http.udp.UDPSocket;

public class UdpHandle implements com.mugui.http.udp.UdpHandle {

	@Override
	public void manage(Bag accpet, UDPSocket udpSocket) {
		UdpBag bag = (UdpBag) accpet;
		System.out.println(bag.getBody());
	}

}
