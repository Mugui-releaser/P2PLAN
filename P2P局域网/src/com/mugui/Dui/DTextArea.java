package com.mugui.Dui;

import java.awt.Font;

import javax.swing.*;

public class DTextArea extends JTextArea{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4065828707219596672L;
	public DTextArea(int len) {
		super();
		this.setDocument(new DDocument(len));
		jibenSet();
	}
	public DTextArea()
	{
		super();
		this.setDocument(new DDocument(5000));
		jibenSet();
	}
	private void jibenSet() {
		this.setLineWrap(true);
		this.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,16));
	}
}
