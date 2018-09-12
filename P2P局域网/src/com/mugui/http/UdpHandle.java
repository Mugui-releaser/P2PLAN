package com.mugui.http;

import com.mugui.http.pack.Bag;
import com.mugui.http.pack.UdpBag;
import com.mugui.http.udp.UDPSocket;

/**
 * 用户udp包的初步处理，包的分发
 * 
 * @author zx844
 *
 */
public class UdpHandle implements com.mugui.http.udp.UdpHandle {

	// 这里声明bag id;
	/**
	 * @DEFAULT 默认类型的空包 ，用于保持udp长期连接，不同的dns服务器对udp连接管控不同，
	 *          长时间未产生的数据发送接收的udp连接将被dns服务器舍弃（一般dns服务器没5分钟舍弃一次）
	 *          ，所以建立连接的两端用此bag_id保持udp连接不被舍弃
	 */
	public static final int DEFAULT = 0;

	@Override
	public void manage(Bag accpet, UDPSocket udpSocket) {
		UdpBag bag = (UdpBag) accpet;
		System.out.println(bag.getBody());
	}

}
