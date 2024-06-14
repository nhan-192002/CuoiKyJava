package chatclient;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MainScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static int chattingRoom = -1;

	JList<String> connectedServerInfoJList;

	JList<String> onlineUserJList;
	JList<String> groupJList;

	JTabbedPane roomTabbedPane;
	List<RoomMessagesPanel> roomMessagesPanels;
	JList<String> roomUsersJList;

	JPanel enterMessagePanel;
	JTextArea messageArea;

	public MainScreen() {
		
		JScrollPane groupListScrollPane = new JScrollPane(groupJList);
		groupListScrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách group"));

		JButton createGroupButton = new JButton("Tạo group");
		createGroupButton.setActionCommand("group");
		createGroupButton.addActionListener(this);

		JPanel groupPanel = new JPanel(new GridBagLayout());

		JPanel chatPanel = new JPanel(new GridBagLayout());
		enterMessagePanel = new JPanel(new GridBagLayout());
		enterMessagePanel.setBackground(Color.white);
		JButton sendButton, fileButton, emojiButton ;

		sendButton = new JButton("Gửi");
		sendButton.setActionCommand("send");
		sendButton.addActionListener(this);

		emojiButton = new JButton(new String(Character.toChars(0x1F601)));
		emojiButton.setActionCommand("emoji");
		emojiButton.addActionListener(this);


//		audioButton = new AudioButton();

		messageArea = new JTextArea();
		messageArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		messageArea.setMinimumSize(new Dimension(100, 20));
		InputMap input = messageArea.getInputMap();
		input.put(KeyStroke.getKeyStroke("shift ENTER"), "insert-break");
		input.put(KeyStroke.getKeyStroke("ENTER"), "text-submit");
		messageArea.getActionMap().put("text-submit", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				sendButton.doClick();
			}
		});

//		enterMessagePanel.add(audioButton, gbc.setGrid(5, 1));

		roomTabbedPane = new JTabbedPane();
		roomTabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JScrollPane selectedTab = (JScrollPane) roomTabbedPane.getSelectedComponent();
				if (selectedTab != null) {
					RoomMessagesPanel selectedMessagePanel = (RoomMessagesPanel) selectedTab.getViewport().getView();
					updateRoomUsersJList();
				}
			}
		});
		roomMessagesPanels = new ArrayList<RoomMessagesPanel>();
		roomUsersJList = new JList<String>();
		roomUsersJList.setBorder(BorderFactory.createTitledBorder("User trong room hiện tại"));

		

		this.setPreferredSize(new Dimension(800, 500));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void updateServerData() {
	}




	public void updateRoomUsersJList() {
	}

	public void updateGroupJList() {
	}

	// ************** ROOM MESSAGES ***************
	public void addNewMessage(int roomID, String type, String whoSend, String content) {
		
	}

	private void addNewMessageGUI() {

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "group": {
			JDialog chooseUserDialog = new JDialog();
			JPanel chooseUserContent = new JPanel(new GridBagLayout());

			
			JScrollPane onlineUserScrollPanel = new JScrollPane(onlineUserJList);
			onlineUserScrollPanel.setBorder(BorderFactory.createTitledBorder("Chọn user để thêm vào nhóm"));

			JLabel groupNameLabel = new JLabel("Tên group: ");
			JTextField groupNameField = new JTextField();
			JButton createButton = new JButton("Tạo group");
			createButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String groupName = groupNameField.getText();
					if (groupName.isEmpty()) {
						JOptionPane.showMessageDialog(chooseUserDialog, "Tên group không được trống", "Lỗi tạo group",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					List<String> chosenUsers = onlineUserJList.getSelectedValuesList();
					if (chosenUsers.size() < 2) {
						JOptionPane.showMessageDialog(chooseUserDialog,
								"Group phải có từ 3 người trở lên (chọn 2 người trở lên)", "Lỗi tạo group",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					chooseUserDialog.setVisible(false);
					chooseUserDialog.dispose();
				}
			});


			chooseUserDialog.setMinimumSize(new Dimension(300, 150));
			chooseUserDialog.setContentPane(chooseUserContent);
			chooseUserDialog.setTitle("Tạo group mới");
			chooseUserDialog.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
			chooseUserDialog.pack();
			chooseUserDialog.getRootPane().setDefaultButton(createButton);
			chooseUserDialog.setLocationRelativeTo(null);
			chooseUserDialog.setVisible(true);
			break;
		}

		case "send": {
			String content = messageArea.getText();
			if (content.isEmpty())
				break;
			if (chattingRoom != -1)
			messageArea.setText("");
			break;
		}

		case "emoji": {
			JDialog emojiDialog = new JDialog();
			Object[][] emojiMatrix = new Object[6][6];
			int emojiCode = 0x1F601;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++)
					emojiMatrix[i][j] = new String(Character.toChars(emojiCode++));
			}

			JTable emojiTable = new JTable();
			emojiTable.setModel(new DefaultTableModel(emojiMatrix, new String[] { "", "", "", "", "", "" }) {
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
			emojiTable.setFont(new Font("Dialog", Font.PLAIN, 20));
			emojiTable.setShowGrid(false);
			emojiTable.setIntercellSpacing(new Dimension(0, 0));
			emojiTable.setRowHeight(30);
			emojiTable.getTableHeader().setVisible(false);

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < emojiTable.getColumnCount(); i++) {
				emojiTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				emojiTable.getColumnModel().getColumn(i).setMaxWidth(30);
			}
			emojiTable.setCellSelectionEnabled(true);
			emojiTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			emojiTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					messageArea.setText(messageArea.getText() + emojiTable
							.getValueAt(emojiTable.rowAtPoint(e.getPoint()), emojiTable.columnAtPoint(e.getPoint())));
				}
			});

			emojiDialog.setContentPane(emojiTable);
			emojiDialog.setTitle("Chọn emoji");
			emojiDialog.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
			emojiDialog.pack();
			emojiDialog.setLocationRelativeTo(this);
			emojiDialog.setVisible(true);
			break;
		}

		case "file": {
			if (chattingRoom == -1)
				break;
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("Chọn file để gửi");
			int result = jfc.showDialog(null, "Chọn file");
			jfc.setVisible(true);

			if (result == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getName();
				String filePath = jfc.getSelectedFile().getAbsolutePath();

			}
		}
		}
	}

//	public static class AudioButton extends JButton implements ActionListener {
//		private static final long serialVersionUID = 1L;
//
//		public boolean isRecording;
//		ImageIcon microphoneImage;
//		ImageIcon stopImage;
//
//		public AudioButton() {
//			microphoneImage = Main.getScaledImage("/microphone.png", 16, 16);
//			stopImage = Main.getScaledImage("/stop.png", 16, 16);
//
//			this.setIcon(microphoneImage);
//			this.addActionListener(this);
//		}
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			if (chattingRoom == -1)
//				return;
//
//			isRecording = !isRecording;
//			if (isRecording) {
//				this.setIcon(stopImage);
//				AudioController.startRecord();
//
//			} else {
//				this.setIcon(microphoneImage);
//				byte[] audioBytes = AudioController.stopRecord();
//
//				String[] options = { "Gửi", "Huỷ" };
//				int choice = JOptionPane.showOptionDialog(Main.mainScreen, "Bạn muốn gửi đoạn âm thanh vừa ghi không?",
//						"Câu hỏi", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
//
//				if (choice == 0) {
//					Main.socketController.sendAudioToRoom(chattingRoom, audioBytes);
//				}
//			}
//		}
//	}

	public static class RoomMessagesPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		

		public static RoomMessagesPanel findRoomMessagesPanel(List<RoomMessagesPanel> roomMessagesPanelList, int id) {
			for (RoomMessagesPanel roomMessagesPanel : roomMessagesPanelList) {
			}
			return null;
		}
	}

	public static class TabComponent extends JPanel {

		private static final long serialVersionUID = 1L;

		public TabComponent(String tabTitle, ActionListener closeButtonListener) {
			JLabel titleLabel = new JLabel(tabTitle);
			JButton closeButton = new JButton(UIManager.getIcon("InternalFrame.closeIcon"));
			closeButton.addActionListener(closeButtonListener);
			closeButton.setPreferredSize(new Dimension(16, 16));

			this.setLayout(new FlowLayout());
			this.add(titleLabel);
			this.add(closeButton);
			this.setOpaque(false);
		}

	}

}
