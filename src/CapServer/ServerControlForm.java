package CapServer;

import java.awt.CardLayout;
import javax.swing.JFrame;


public class ServerControlForm extends JFrame {
	private CardLayout cards = new CardLayout();

	public static int menuDelete[] = new int[50];
	public static int menuSelect = 0;
	public static int add = 0;

	public static final int manageStartForm = 1;
	public static final int menuManageForm = 2;
	public static final int currentMenuForm = 3;
	public static final int detailMenuForm = 4;

	public ServerControlForm() {
		setSize(800, 600);
		getContentPane().setLayout(cards);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		for (int i = 0; i < 50; i++) {
			menuDelete[i] = 0;

		}
		getContentPane().add("ManageStartForm", new ManageStartForm(this));

		setVisible(true);
	}

	public void changePanel(String s) {
		cards.show(this.getContentPane(), s);
	}

	public void adjustPanel(ServerControlForm f, String s, int select,
			int formNumber) {

		switch (formNumber) {
		
		case currentMenuForm:
			//getContentPane().add(s, new CurrentMenuForm(f,select));
			changePanel(s);
			break;
		
		case detailMenuForm:
			//getContentPane().add(s,new DetailMenuForm(f, select));
			changePanel(s);
			break;
		}
	}

	public void adjustPanel(ServerControlForm f, String s, int[] menuNumber,
			int formNumber) {

		switch (formNumber) {
		case menuManageForm:
			//getContentPane().add(s,new MenuManageForm(f, menuNumber));
			changePanel(s);
			break;
		}

	}

	public void adjustPanel(ServerControlForm f, String s, int[] menuNumber, int add,
			int formNumber) {

		switch (formNumber) {
		case menuManageForm:
			//getContentPane().add(s,new MenuManageForm(f, menuNumber,add));
			changePanel(s);
			break;
		}

	}
	public void adjustPanel(ServerControlForm f, String s, int formNumber) {
		switch (formNumber) {
		case manageStartForm:
			//getContentPane().add(s, new ManageStartForm(f));
			changePanel(s);
			break;

		case currentMenuForm:
			//getContentPane().add(s, new CurrentMenuForm(f));
			changePanel(s);
			break;

		}
	}
}