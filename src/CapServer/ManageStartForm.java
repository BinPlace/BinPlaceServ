package CapServer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


public class ManageStartForm extends JPanel{
	
	private ServerControlForm F;
	
	JPanel ManageStartButtonPanel;
	JPanel ManageStartStringPanel;
	
	JButton MenuManagementButton;
	JButton CurrentMenuButton;
	
	JLabel MenuStartString;
	
	
	public ManageStartForm(ServerControlForm f){
		
		F = f;
		
		setSize(800, 600);

		ManageStartButtonPanel = new JPanel();
		ManageStartStringPanel = new JPanel();
		
		
		MenuManagementButton = new JButton();
		MenuManagementButton.addActionListener(new ActionListener(){		//메뉴관리 ACTION
            public void actionPerformed(ActionEvent e) {
            	F.adjustPanel(F,"MenuManageForm",ServerControlForm.menuDelete,ServerControlForm.menuManageForm);
            }
        });
		CurrentMenuButton = new JButton();
		CurrentMenuButton.addActionListener(new ActionListener(){		//주문상황 ACTION
            public void actionPerformed(ActionEvent e) {
            	F.adjustPanel(F, "CurrentMenuForm",ServerControlForm.currentMenuForm);
            }
        });
		
		ManageStartButtonPanel.add(MenuManagementButton);
		ManageStartButtonPanel.add(CurrentMenuButton);
		
		MenuStartString = new JLabel("<html>빈 PLACE POS<html>");
		
		ManageStartStringPanel.add(MenuStartString);
		
		//-----------------------------GUI 수정
		setLayout(null);
		
		ManageStartStringPanel.setBounds(0, 0, 800, 300);
		ManageStartStringPanel.setLayout(null);
		
		ManageStartButtonPanel.setBounds(0, 300, 800, 400);
		ManageStartButtonPanel.setLayout(null);
		ManageStartStringPanel.setBackground(new Color(255, 110, 87));
		ManageStartButtonPanel.setBackground(new Color(255, 110, 87));
		
		MenuStartString.setBounds(220, 50, 500, 200);
		MenuStartString.setFont(new Font("배달의민족 한나",Font.ITALIC | Font.PLAIN,50));
		MenuStartString.setForeground(Color.white);
		
		Border border = BorderFactory.createLineBorder(new Color(255, 110, 87));
		
		ImageIcon MenuManagementIcon = new ImageIcon("메뉴관리.png");
		Image MenuManagementImage = MenuManagementIcon.getImage();
		Image realMenuManagementImage = MenuManagementImage.getScaledInstance(120, 40, Image.SCALE_SMOOTH);
		ImageIcon realMenuManagementIcon = new ImageIcon(realMenuManagementImage);
		MenuManagementButton.setBackground(new Color(255, 110, 87));
		MenuManagementButton.setIcon(realMenuManagementIcon);
		MenuManagementButton.setBorder(border);
		MenuManagementButton.setBounds(200, 0, 120, 40);
		
		ImageIcon CurrentMenuIcon = new ImageIcon("주문상황.png");
		Image CurrentMenuImage = CurrentMenuIcon.getImage();
		Image realCurrentMenuImage = CurrentMenuImage.getScaledInstance(120, 40, Image.SCALE_SMOOTH);
		ImageIcon realCurrentMenuIcon = new ImageIcon(realCurrentMenuImage);
		CurrentMenuButton.setBackground(new Color(255, 110, 87));
		CurrentMenuButton.setIcon(realCurrentMenuIcon);
		CurrentMenuButton.setBorder(border);
		CurrentMenuButton.setBounds(450, 0, 120, 40);
		
		add(ManageStartStringPanel);
		add(ManageStartButtonPanel);
		
		setVisible(true);
		
		
		
	}
}
