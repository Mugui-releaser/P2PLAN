package com.mugui.http.udp;

import com.mugui.http.pack.Bag;

public interface UdpHandle {
	void manage(Bag accpet, UDPSocket udpSocket);

	Bag getValue(String key);
}
