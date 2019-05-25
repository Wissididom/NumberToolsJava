package tools.number.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import org.apache.axis2.databinding.types.UnsignedLong;

import tools.number.classes.API;
import tools.number.classes.API.Language;

public class NumberToolsTestFrame {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame("NumberToolsTestFrame");
				frame.setBounds(100, 100, 500, 75);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				JSpinner js = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
				frame.add(js, BorderLayout.WEST);
				JLabel lbl = new JLabel("");
				frame.add(lbl, BorderLayout.SOUTH);
				JButton btn = new JButton("get");
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						lbl.setText(API.getNumber(Language.BINARY, new UnsignedLong((Integer) js.getValue())));
					}
				});
				frame.add(btn, BorderLayout.EAST);
				frame.setVisible(true);
			}
		});
	}
}
