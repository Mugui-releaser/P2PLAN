package com.mugui.tool;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpTool {
	//得到newSocket
		public synchronized static Socket getSocket(String Host,int Port){
			return getSocket(Host, Port,0); 
		}

		public synchronized static Socket getSocket(String Host, int Port, int time) {
			try {
				Socket s=new Socket(Host,Port);
				s.setSoTimeout(time);
				return s;
			} catch (UnknownHostException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		//得到一个InetAddress
		public static InetAddress getInetAddress(String Host){
			try {
				return InetAddress.getByName(Host);
			} catch (UnknownHostException e) {
				
			}
			return null;
		}
}
