package com.mugui.http.tcp;

import com.mugui.http.pack.Bag;

public interface TcpHandle {

	void manage(Bag accpet, TcpSocketUserBean udpSocket);

}
