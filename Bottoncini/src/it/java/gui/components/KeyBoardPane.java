package it.java.gui.components;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class KeyBoardPane extends JFrame {
	public KeyBoardPane()
	{
		setTitle("Buttonz");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		currentNumber_value = 0;
		currentNumber_label = new JLabel(""+currentNumber_value);
		head = new JPanel();
		head.add(currentNumber_label);
		//equivalente a frame.add(component,constraint);
		add(currentNumber_label,BorderLayout.CENTER);
		class VariationListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				
				modifyCurrentNumber();
			}
		}
		button_confirm = new JButton("Conferma");
		field_input = new JTextField(12);
		label_input = new JLabel("Ammontare");
		button_confirm.addActionListener(new VariationListener());
		keyboard = new JPanel();
		keyboard.add(label_input);
		keyboard.add(field_input);
		keyboard.add(button_confirm);
		this.setLayout(new GridBagLayout());
		head.setSize(50, 50);
		keyboard.setSize(100,100);
		add(head);
		add(keyboard);
		}
		
	//Action on Event
	private void modifyCurrentNumber()
	{
		String field = field_input.getText().trim();
		int amount = Integer.valueOf(field);
		currentNumber_value+=amount;
		currentNumber_label.setText("" + currentNumber_value);
	}
	
	private JLabel currentNumber_label;
	private int currentNumber_value;
	
	//bottonz
	private JButton button_confirm;
	
	//fields
	private JTextField field_input;
	private JLabel label_input;
	
	//keyboard
	private JPanel head;
	private JPanel keyboard;
}
