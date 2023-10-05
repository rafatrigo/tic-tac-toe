package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JSlider;
import java.awt.Cursor;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Screen extends JFrame {

	private JPanel contentPane;
	private JTextField textNsubjects;
	private JTextField textDnaSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screen frame = new Screen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//-----------METHODS----------------
	
	private void sliderUpdateState(JSlider source, JLabel target)
    {
		String value = Integer.toString(source.getValue());
				
		target.setText(value);
	}
	
	private void btnPlayActionPerformed()
	{
		//TODO
	}
	
	private void btnTrainActionPerformed()
	{
		//TODO
	}
	

	/**
	 * Create the frame.
	 */
	public Screen() {
		setTitle("Jogo da Velha");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel header = new JPanel();
		header.setPreferredSize(new Dimension(500, 50));
		contentPane.add(header, BorderLayout.NORTH);
		header.setLayout(null);
		
		JLabel lblTitle = new JLabel("Jogo da Velha");
		
		//x= ContentPaneSizeX/2 - lblTitleX/2
		//y = ContentPaneSizeY/2 - lblTitleY/2
		lblTitle.setBounds(300 - 55, 11, 110, 30);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setLabelFor(header);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(lblTitle);
		
		JPanel configurationPanel = new JPanel();
		configurationPanel.setPreferredSize(new Dimension(200, 445));
		contentPane.add(configurationPanel, BorderLayout.WEST);
		configurationPanel.setLayout(null);
		
		JLabel lblConfiguration = new JLabel("Configurações");
		lblConfiguration.setBounds(56, 5, 87, 17);
		lblConfiguration.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfiguration.setLabelFor(configurationPanel);
		configurationPanel.add(lblConfiguration);
		
		JLabel lblNSubjects = new JLabel("População:");
		lblNSubjects.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNSubjects.setBounds(10, 40, 69, 14);
		configurationPanel.add(lblNSubjects);
		
		textNsubjects = new JTextField();
		lblNSubjects.setLabelFor(textNsubjects);
		textNsubjects.setBounds(78, 40, 86, 20);
		configurationPanel.add(textNsubjects);
		textNsubjects.setColumns(10);
		
		JLabel lblDnaSize = new JLabel("Tam. DNA:");
		lblDnaSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDnaSize.setBounds(10, 75, 69, 14);
		configurationPanel.add(lblDnaSize);
		
		textDnaSize = new JTextField();
		lblDnaSize.setLabelFor(textDnaSize);
		textDnaSize.setColumns(10);
		textDnaSize.setBounds(78, 71, 86, 20);
		configurationPanel.add(textDnaSize);
		
		JLabel lblMutation = new JLabel("Mutação %:");
		lblMutation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMutation.setBounds(10, 112, 69, 14);
		configurationPanel.add(lblMutation);
		
		JLabel lblMutationValue = new JLabel("50");
		lblMutation.setLabelFor(lblMutationValue);
		lblMutationValue.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMutationValue.setBounds(88, 113, 69, 14);
		configurationPanel.add(lblMutationValue);
		
		JSlider sliderMutation = new JSlider();
		sliderMutation.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				sliderUpdateState(sliderMutation, lblMutationValue);
			}
		});
		lblMutationValue.setLabelFor(sliderMutation);
		sliderMutation.setBounds(10, 137, 180, 26);
		configurationPanel.add(sliderMutation);
		
		JLabel lblSaveSubjects = new JLabel("Manter %:");
		lblSaveSubjects.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSaveSubjects.setBounds(10, 174, 69, 14);
		configurationPanel.add(lblSaveSubjects);
		
		JLabel lblSaveSubjectsValue = new JLabel("50");
		lblSaveSubjects.setLabelFor(lblSaveSubjectsValue);
		lblSaveSubjectsValue.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSaveSubjectsValue.setBounds(88, 175, 69, 14);
		configurationPanel.add(lblSaveSubjectsValue);
		
		JSlider sliderSaveSubjects = new JSlider();
		sliderSaveSubjects.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				sliderUpdateState(sliderSaveSubjects, lblSaveSubjectsValue);
			}
		});
		lblSaveSubjectsValue.setLabelFor(sliderSaveSubjects);
		sliderSaveSubjects.setBounds(10, 199, 180, 26);
		configurationPanel.add(sliderSaveSubjects);
		
		JButton btnTrain = new JButton("Treinar");
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPlayActionPerformed();
			}
		});
		btnTrain.setBounds(10, 272, 180, 23);
		configurationPanel.add(btnTrain);
		
		JButton btnPlay = new JButton("Jogar");
		btnPlay.setEnabled(false);
		btnPlay.setBounds(10, 306, 180, 23);
		configurationPanel.add(btnPlay);
		
		JPanel boardPanel = new JPanel();
		contentPane.add(boardPanel, BorderLayout.CENTER);
		boardPanel.setLayout(null);
		
		JPanel board = new JPanel();
		board.setBackground(Color.BLACK);
		board.setBounds(75, 72, 250, 250);
		boardPanel.add(board);
		board.setLayout(new GridLayout(3, 3, 5, 5));
		
		JPanel field_0 = new JPanel();
		field_0.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_0);
		GridBagLayout gbl_field_0 = new GridBagLayout();
		gbl_field_0.columnWidths = new int[] {0};
		gbl_field_0.rowHeights = new int[] {0};
		gbl_field_0.columnWeights = new double[]{0.0};
		gbl_field_0.rowWeights = new double[]{0.0};
		field_0.setLayout(gbl_field_0);
		
		JLabel playerMove = new JLabel("");
		playerMove.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		GridBagConstraints gbc_playerMove = new GridBagConstraints();
		gbc_playerMove.anchor = GridBagConstraints.NORTHWEST;
		gbc_playerMove.gridx = 0;
		gbc_playerMove.gridy = 0;
		field_0.add(playerMove, gbc_playerMove);
		
		JPanel field_1 = new JPanel();
		field_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_1);
		
		JLabel playerMove1 = new JLabel("");
		playerMove1.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove1.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove1.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_1.add(playerMove1);
		
		JPanel field_2 = new JPanel();
		field_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_2);
		
		JLabel playerMove2 = new JLabel("");
		playerMove2.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove2.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove2.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_2.add(playerMove2);
		
		JPanel field_3 = new JPanel();
		field_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_3);
		
		JLabel playerMove3 = new JLabel("");
		playerMove3.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove3.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove3.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_3.add(playerMove3);
		
		JPanel field_4 = new JPanel();
		field_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_4);
		
		JLabel playerMove4 = new JLabel("");
		playerMove4.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove4.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove4.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_4.add(playerMove4);
		
		JPanel field_5 = new JPanel();
		field_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_5);
		
		JLabel playerMove5 = new JLabel("");
		playerMove5.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove5.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove5.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_5.add(playerMove5);
		
		JPanel field_6 = new JPanel();
		field_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_6);
		
		JLabel playerMove6 = new JLabel("");
		playerMove6.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove6.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove6.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_6.add(playerMove6);
		
		JPanel field_7 = new JPanel();
		field_7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_7);
		
		JLabel playerMove7 = new JLabel("");
		playerMove7.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove7.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove7.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_7.add(playerMove7);
		
		JPanel field_8 = new JPanel();
		field_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_8);
		
		JLabel playerMove8 = new JLabel("");
		playerMove8.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove8.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove8.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_8.add(playerMove8);
		
	}
}
