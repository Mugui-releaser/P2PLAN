/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.mugui.http.pack;

import net.sf.json.JSONObject;

public abstract interface Bag {
	public static final String String = "<String>";
	public static final String ByteArrays = "<ByteArrays>";
	public static final String KEY_INFO = "key_info";
	public static final String MOUSE_INFO = "mouse_info";

	public abstract void setPort(int paramInt);

	public abstract void setHost(String paramString);

	public abstract void setJsonObject(JSONObject paramJSONObject);

	public abstract byte[] toByteArrays();

	public abstract String toString();

	public abstract void setByteArrays(String paramString, byte[] paramArrayOfByte);

	public abstract String getHost();

	public abstract int getPort();
}