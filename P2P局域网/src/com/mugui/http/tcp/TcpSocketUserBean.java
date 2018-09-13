package com.mugui.http.tcp;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.mugui.http.pack.Bag;
import com.mugui.tool.DataClassLoader;

public class TcpSocketUserBean {

	// 一个用户可以增加，删除，修改的集合
	private ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<String, Object>();
	private TcpSocketClient socketChannel = null;

	private TcpSocketHandleDataThread thread = null;
	private String code = null;
	private long codeTime = 0;
	private long codeActiveTime = TcpSocketPrivateMap.CODE_ACTIVE_TIME;
	private TcpHandle handle = null;
	private DataClassLoader loader = null;

	public TcpSocketUserBean(TcpHandle handle, DataClassLoader loader) {
		this.handle = handle;
		this.loader = loader;
	}

	public TcpHandle getHandle() {
		return handle;
	}

	@Override
	public String toString() {
		String string = "";
		string += code + ":" + getServerHost() + "\r\n";
		Iterator<Entry<String, Object>> it = hashMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			string += "key: " + entry.getKey() + "value: " + entry.getValue() + "\r\n";
		}
		return string;
	}

	protected long getCodeActiveTime() {
		return codeActiveTime;
	}

	protected void setCodeActiveTime(long codeActiveTime) {
		this.codeActiveTime = codeActiveTime;
	}

	protected ConcurrentHashMap<String, Object> getHashMap() {
		return hashMap;
	}

	protected void setHashMap(ConcurrentHashMap<String, Object> hashMap) {
		this.hashMap = hashMap;
	}

	public TcpSocketClient getSocketChannel() {
		if (!isSocketRun()) {
			socketChannel.close();
			return null;
		}
		return socketChannel;
	}

	public void setSocketChannel(TcpSocketClient socketChannel) {
		if (this.socketChannel != null && this.socketChannel.socketChannel != socketChannel.socketChannel) {
			this.socketChannel.close();
		}
		this.socketChannel = socketChannel;

	}

	protected String getCode() {
		return code;
	}

	protected void setCode(String code) {
		this.code = code;
	}

	public long getCodeTime() {
		return codeTime;
	}

	public void setCodeTime(long codeTime) {
		this.codeTime = codeTime;
	}

	public void addUserData(String key, Object value) {
		hashMap.put(key, value);
	}

	public Object getUserData(String key) {
		return hashMap.get(key);
	}

	public Object delUserData(String key) {
		return hashMap.remove(key);
	}

	public Set<Entry<String, Object>> getUserAllDate() {
		return hashMap.entrySet();
	}

	public void clearUserAllData() {
	
		codeTime = 0;
		hashMap.clear();
		if (socketChannel != null)
			socketChannel.close();
	}

	public void send(Bag bag) {
		TcpSocketClient socketClient = getSocketChannel();
		try {
			if (socketClient != null)
				socketClient.send(bag.toByteArrays(), false, code);
		} catch (Exception e) {
			e.printStackTrace();
			if (socketClient != null)
				socketClient.close();
		}
	}

	public void reCodeTime() {
		setCodeTime(System.currentTimeMillis());
	}

	// 处理数据
	public void handleData(byte[] data) {
		if (thread == null)
			thread = new TcpSocketHandleDataThread(data, this);
		else
			thread.init(data, this);
		thread.Run();
	}

	public boolean isSocketRun() {

		return socketChannel != null && socketChannel.isRun();
	}

	public boolean isOutTimeLimit() {
		return System.currentTimeMillis() - codeTime > codeActiveTime;
	}

	public String getServerHost() {
		if (isSocketRun())
			return socketChannel.getServerHost();
		return null;
	}
}
