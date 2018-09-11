package com.mugui.tool;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComponent;

import com.mugui.Dui.DButton;

public class UiTool {
	public static void 全体透明(Container container) {
		Component[] components = container.getComponents();
		if (components.length < 1)
			return;
		for (Component c : components) {
			if (c instanceof JComponent && !(c instanceof DButton)) {
				c.setBackground(null);
				((JComponent) c).setOpaque(false);
				全体透明((JComponent) c);
			}
		}
	}
}
