package com.mugui.Dui;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.plaf.metal.MetalButtonUI;

public class DButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6655761286238492290L;

	private class DMetalButtonUI extends MetalButtonUI implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -508986144537417765L;

		@Override
		protected void paintButtonPressed(Graphics g, AbstractButton b) {
			if (b.isContentAreaFilled()) {
				Dimension size = b.getSize();
				g.setColor(pressColor);
				g.fillRect(0, 0, size.width, size.height);
			}
		}
	}

	public DButton(String text, Color color) {
		this.setUI(new DMetalButtonUI());
		this.setText(text);
		this.setFocusPainted(false);
		this.setBackground(Color.DARK_GRAY);
		if (color == null)
			color = Color.white;
		this.setForeground(color);
		this.setBorder(new MetalBorders.ButtonBorder());// ��������߿�֮��ľ���Ϊ��
		this.setFont(new Font("华文行魏", Font.CENTER_BASELINE, 17));
	}

	private Color pressColor = DEFAULT_PRESS_COLOR;
	private static final Color DEFAULT_PRESS_COLOR = Color.DARK_GRAY;

	public void setButtonPressColor(Color color) {
		if (color == null) {
			pressColor = DEFAULT_PRESS_COLOR;
			return;
		}
		pressColor = color;
	}
}