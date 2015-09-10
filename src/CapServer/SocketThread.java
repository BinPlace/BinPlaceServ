package CapServer;

public class SocketThread extends Thread {

	private ConnectServerSocket servSock;
	private static String[] confirmdata = new String[20];
	
	final int SIZE = 100;
	
	public SocketThread(ConnectServerSocket sock) {
		this.servSock = sock;
	}

	public void run() {
		while (servSock.__isconnented()) {
			DBAccess_Table_info tempDBAccess_Table_info = new DBAccess_Table_info();
			DBAccess_Menu_info tempDBAccess_Menu_info = new DBAccess_Menu_info();
			DBAccess_Shop tempDBAccess_Shop = new DBAccess_Shop();
			DBAccess_Waiting tempDBAccess_Waiting = new DBAccess_Waiting();
			DBAccess_Table tempDBAccess_Table =  new DBAccess_Table();
			
			confirmdata = servSock.receiveData(); // ���� �޾Ƽ� id �´��� ó���ؾ���
			if(servSock.getClientSocket()==null){	//������ null�̸� loop break
				break;
			}
			if (confirmdata[0].equals("login")) { // �α��� �� ��
				servSock.verifyData(tempDBAccess_Shop.checkDB(confirmdata[1], confirmdata[2]));
				
			}
			else if (confirmdata[0].equals("signup")) { // ������ ��
				servSock.verifyData(tempDBAccess_Shop.checkidDB(confirmdata[1]));
			}
			else if (confirmdata[0].equals("editCustomerInfo")) { // ȸ������������ ��
				tempDBAccess_Shop.alterDB_to_Shop(confirmdata[3], confirmdata[4], confirmdata[5], confirmdata[1], confirmdata[2], Integer.parseInt(confirmdata[6]));
								
			}
			else if (confirmdata[0].equals("requestMenuInfo")) { // �޴����� �ѷ��ֱ�
				
				tempDBAccess_Menu_info.getDB_from_Menu_info(confirmdata[1]);
				
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuname_arr());
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuprice_arr());
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuinfo_arr());
			}
			else if (confirmdata[0].equals("requestTableInfo")) { // ���̺�����
				servSock.sendData(tempDBAccess_Table_info.getDB_from_Table_info(confirmdata[1]));
			}
			
			else if(confirmdata[0].equals("addMenuInfo")){
				//�޴��� �߰��ϱ�
				tempDBAccess_Menu_info.addDB_to_Menu_info(confirmdata[1], confirmdata[2], Integer.parseInt(confirmdata[3]), confirmdata[4]);
				
			}
			
			else if(confirmdata[0].equals("deleteMenuInfo")){
				//�޴��� �����ϱ�
				tempDBAccess_Menu_info.deleteDB_to_Menu_info(confirmdata[1], confirmdata[2]);
				
			}
			
			else if(confirmdata[0].equals("editMenuInfo")){
				//�޴��� ������ �ٲٱ�
				tempDBAccess_Menu_info.alterDB_to_Menu_info(confirmdata[1], confirmdata[2], Integer.parseInt(confirmdata[3]), confirmdata[4], confirmdata[5]);	
			}
			else if(confirmdata[0].equals("checkMenuName")){
				//������ �޴� �̸��� �����ϴ��� Ȯ��
				servSock.verifyData(tempDBAccess_Menu_info.checkMenuNameDB(confirmdata[1], confirmdata[2]));
			}
			
			else if(confirmdata[0].equals("requestWaitingInfo")){
				//�� �������� ���ǥ�� ��������
				tempDBAccess_Waiting.getDB_from_Waiting(confirmdata[1]);
				servSock.sendData(tempDBAccess_Waiting.getWaiting_Arr());
				servSock.sendData(tempDBAccess_Waiting.getWaiting_NumberArr());
				
				
			}
			else if(confirmdata[0].equals("deleteWaitingInfo")){
				//�� �������� ���ǥ�� ����
				tempDBAccess_Waiting.deleteDB_to_Waiting(confirmdata[1], confirmdata[2]);
			}
			
			else if(confirmdata[0].equals("updateTableInfo")){
				
				tempDBAccess_Table_info.alterDB_to_Table_info(confirmdata[1], Integer.parseInt(confirmdata[2]));
			}
			else if(confirmdata[0].equals("updateTableInfoNew")){
				
				
				int[] tempGetTableNo = new int[SIZE];
				int[] tempGetTableState = new int[SIZE];
				double[] tempGetX = new double[SIZE];
				double[] tempGetY = new double[SIZE];
				int[] tempGetTableSize = new int[SIZE];
				
				tempGetTableNo = servSock.receiveIntData();
				tempGetTableState = servSock.receiveIntData();
				tempGetX = servSock.receivedoubleData();
				
				tempGetY = servSock.receivedoubleData();
				tempGetTableSize = servSock.receiveIntData();
				
				tempDBAccess_Table.updateDB_to_Table_Location(confirmdata[1], tempGetTableNo, tempGetTableState
						, tempGetX, tempGetY, tempGetTableSize);
				
			}
			else if(confirmdata[0].equals("getTableInfoNew")){
				
				
				tempDBAccess_Table.getDB_from_Table_Location(confirmdata[1]);	//shop_id
				servSock.sendData(tempDBAccess_Table.getTable_no());			//int arr
				servSock.sendData(tempDBAccess_Table.getTable_state()); 		//int arr
				servSock.sendData(tempDBAccess_Table.getX_coordinate());		//double arr
				servSock.sendData(tempDBAccess_Table.getY_coordinate());		//double arr
				servSock.sendData(tempDBAccess_Table.getTable_size());			//int arr
			}
			
			else if(confirmdata[0].equals("updateTableState")){
				
				tempDBAccess_Table.updateTableState(confirmdata[1], Integer.parseInt(confirmdata[2]), Integer.parseInt(confirmdata[3]));
			}
			
			
			
			

			
			else if(confirmdata[0].equals("requestMenuInfoApp")){
				//�ȵ���̵� �޴� ���� �ѷ��ֱ�
				tempDBAccess_Menu_info.getDB_from_Menu_info(confirmdata[1]);
				
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuname_arr());
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuprice_arr());
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuinfo_arr());
			}
			
			else if(confirmdata[0].equals("requestTableInfoApp")){
				int[] numberOfTable = new int[1];
				int[] temp;
				
				tempDBAccess_Shop.getOneDB_from_Shop(confirmdata[1]);
				numberOfTable[0] = tempDBAccess_Shop.getShop_numberOfTableAlone();
				
				temp = tempDBAccess_Table_info.getDB_from_Table_info(confirmdata[1]);

				servSock.sendData(numberOfTable);
				servSock.sendData(temp);
			}
			
			else if(confirmdata[0].equals("requestTableInfoAppNew")){
				
				
				tempDBAccess_Table.getDB_from_Table_Location(confirmdata[1]);
				
				servSock.sendData(tempDBAccess_Table.getTable_no());
				servSock.sendData(tempDBAccess_Table.getTable_state());
				servSock.sendData(tempDBAccess_Table.getX_coordinate());
				servSock.sendData(tempDBAccess_Table.getY_coordinate());
				servSock.sendData(tempDBAccess_Table.getTable_size());
				
				
				
			}
			
			else if(confirmdata[0].equals("sendingWaitingNameApp")){	
				//�� �������� ���ǥ�� �߰�����
				tempDBAccess_Waiting.addDB_to_Waiting(confirmdata[1], confirmdata[2]);
				tempDBAccess_Waiting.getDB_from_Waiting(confirmdata[1]);
				servSock.sendData(tempDBAccess_Waiting.getWaiting_Arr());
				servSock.sendData(tempDBAccess_Waiting.getWaiting_NumberArr());
			}
			else if(confirmdata[0].equals("requestWaitingNumberApp")){
				//�ش� ����� ��� ��ȣ�� �տ� ��� ���Ҵ����� �˷���
				tempDBAccess_Waiting.getDB_from_Waiting(confirmdata[1]);
				servSock.sendData(tempDBAccess_Waiting.getWaiting_Arr());
				servSock.sendData(tempDBAccess_Waiting.getWaiting_NumberArr());
			}
			
			
			else if(confirmdata[0].equals("requestLocationInfoApp")){
				//��� �������� ���� ������ ���� ��
				
				tempDBAccess_Shop.calculateDouble();
				
				servSock.sendData(tempDBAccess_Shop.getLatitude_arr());
				servSock.sendData(tempDBAccess_Shop.getLongdidude_arr());
				servSock.sendData(tempDBAccess_Shop.getShop_name());
				servSock.sendData(tempDBAccess_Shop.getShop_address());
				servSock.sendData(tempDBAccess_Shop.getShop_id());
			}
			/*
			else if(confirmdata[0].equals("localSearchListViewApp")){
				//�������˻��ϰ� ���� �� ����� ��� �� ������ ��������
				
				tempDBAccess_Seoul.get_from_Seoul();
				servSock.sendData(tempDBAccess_Seoul.getGu());
				servSock.sendData(tempDBAccess_Seoul.getDong());	
			}
			*/
			
			else if(confirmdata[0].equals("localSearchRestaurantApp")){
				//�������˻��ϰ� ���� �� �ش� ������ ���������� ��ȯ����
				
				tempDBAccess_Shop.searchLocal(confirmdata[1]);
				
				servSock.sendData(tempDBAccess_Shop.getShop_id());
				servSock.sendData(tempDBAccess_Shop.getShop_name());
				servSock.sendData(tempDBAccess_Shop.getShop_address());	
				servSock.sendData(tempDBAccess_Shop.getShop_telnumber());	
				servSock.sendData(tempDBAccess_Shop.getShop_numberOfTable());	
				
			}
			
		}
		if(servSock.getClientSocket()!=null){
			servSock.closeInputStream();
			servSock.closeOutputstream();
			servSock.closeDataOutputStream();
			servSock.closeSocket();
		}
	}
}
