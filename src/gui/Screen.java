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
import javax.swing.JOptionPane;
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

import controllers.Controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Screen extends JFrame {

	private JPanel contentPane;
	private JTextField textNSubjects;
	private JTextField textDnaSize;
	
	private Controller controller;
	private boolean playable = false;

	JLabel playerMove = new JLabel("");
	JLabel playerMove1 = new JLabel("");
	JLabel playerMove2 = new JLabel("");
	JLabel playerMove3 = new JLabel("");
	JLabel playerMove4 = new JLabel("");
	JLabel playerMove5 = new JLabel("");
	JLabel playerMove6 = new JLabel("");
	JLabel playerMove7 = new JLabel("");
	JLabel playerMove8 = new JLabel("");
	
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
		clearBoard();
        controller.resetUserStrategy();

        int movePosition = controller.startGame();
        
        marksSubjectPlay(movePosition);
        
        playable = true;
	}
	
	private void btnTrainActionPerformed(JTextField textNSubjects, JTextField textDnaSize, JSlider sliderMutation, JSlider sliderSaveSubjects, JButton btnPlay)
	{
		String n_Subjects = textNSubjects.getText();
        String dna_Size = textDnaSize.getText();
        
        try{
            
            int nSubjects = Integer.parseInt(n_Subjects);
            int dnaSize = Integer.parseInt(dna_Size);
            int mutation = sliderMutation.getValue();
            int saveSubjects = sliderSaveSubjects.getValue();
            
            this.controller = new Controller(nSubjects, dnaSize, mutation, saveSubjects);
            
        }catch(RuntimeException e){
            JOptionPane.showMessageDialog(this, "Erro nos campos de texto.");
        }
        
        
        this.controller.trainSubjects();
        
        btnPlay.setEnabled(true);
	}
	
	private void clearBoard()
    {
        playerMove.setText("");
        playerMove1.setText("");
        playerMove2.setText("");
        playerMove3.setText("");
        playerMove4.setText("");
        playerMove5.setText("");
        playerMove6.setText("");
        playerMove7.setText("");
        playerMove8.setText("");
        
    }
	
	private boolean checkAndPlay(JLabel field, int position)
    {
        if(playable && field.getText().equalsIgnoreCase(""))
        {
            field.setText("O");
            controller.updateBoardP2(position);
            controller.addUserLastMove(position);
            
            return true;
        }
        
        System.out.println("CheckAndPlay");
        
        return false;
    }
	
	private void marksSubjectPlay(int position)
    {
        switch(position)
        {
            case 0 -> playerMove.setText("X");
            case 1 -> playerMove1.setText("X");
            case 2 -> playerMove2.setText("X");
            case 3 -> playerMove3.setText("X");
            case 4 -> playerMove4.setText("X");
            case 5 -> playerMove5.setText("X");
            case 6 -> playerMove6.setText("X");
            case 7 -> playerMove7.setText("X");
            case 8 -> playerMove8.setText("X");
            default -> {
            }
        }
    }
	
	private void gameOver(int result)
    {
        switch(result)
        {
            case 0:
                System.out.println("Empatou");
                playable = false;
                break;
            case 1:
                System.out.println("Voce perdeu");
                playable = false;
                break;
            case 2:
                System.out.println("Voce ganhou");
                controller.trainSubjectsAgainstStrategy();
                playable = false;
            default:
                break;
        }
    }
	
	private void subjectNextMove()
    {
        int move = controller.subjectPlay();
            
        this.marksSubjectPlay(move);
            
        int result = controller.checkEnd();
            
        if(result != 3)
        {
            playable = false;
        }
            
        gameOver(result);
    }
	
	private void play(JLabel playerMove, int p){
        if(playable)
        {
            if(checkAndPlay(playerMove, p)){
                int result = controller.checkEnd();
        
                if(result == 3){
                    subjectNextMove();
                }else{
                    gameOver(result);
                }
            }
            
            System.out.println("Play");
        }
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
		
		textNSubjects = new JTextField();
		lblNSubjects.setLabelFor(textNSubjects);
		textNSubjects.setBounds(78, 40, 86, 20);
		configurationPanel.add(textNSubjects);
		textNSubjects.setColumns(10);
		
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
		
		JButton btnPlay = new JButton("Jogar");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPlayActionPerformed();
			}
		});
		btnPlay.setEnabled(false);
		btnPlay.setBounds(10, 306, 180, 23);
		configurationPanel.add(btnPlay);
		
		JButton btnTrain = new JButton("Treinar");
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTrainActionPerformed(textNSubjects, textDnaSize, sliderMutation, sliderSaveSubjects, btnPlay);
			}
		});
		btnTrain.setBounds(10, 272, 180, 23);
		configurationPanel.add(btnTrain);
		
		JPanel boardPanel = new JPanel();
		contentPane.add(boardPanel, BorderLayout.CENTER);
		boardPanel.setLayout(null);
		
		JPanel board = new JPanel();
		board.setBackground(Color.BLACK);
		board.setBounds(75, 72, 250, 250);
		boardPanel.add(board);
		board.setLayout(new GridLayout(3, 3, 5, 5));
		
		JPanel field_0 = new JPanel();
		field_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				play(playerMove, 0);
				System.out.println("Play000");
			}
		});
		field_0.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_0);
		GridBagLayout gbl_field_0 = new GridBagLayout();
		gbl_field_0.columnWidths = new int[] {0};
		gbl_field_0.rowHeights = new int[] {0};
		gbl_field_0.columnWeights = new double[]{0.0};
		gbl_field_0.rowWeights = new double[]{0.0};
		field_0.setLayout(gbl_field_0);
		
		
		playerMove.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		GridBagConstraints gbc_playerMove = new GridBagConstraints();
		gbc_playerMove.anchor = GridBagConstraints.NORTHWEST;
		gbc_playerMove.gridx = 0;
		gbc_playerMove.gridy = 0;
		field_0.add(playerMove, gbc_playerMove);
		
		JPanel field_1 = new JPanel();
		field_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				play(playerMove1, 1);
			}
		});
		field_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_1);
		
		
		playerMove1.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove1.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove1.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_1.add(playerMove1);
		
		JPanel field_2 = new JPanel();
		field_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				play(playerMove2, 2);
			}
		});
		field_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_2);
		
		
		playerMove2.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove2.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove2.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_2.add(playerMove2);
		
		JPanel field_3 = new JPanel();
		field_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				play(playerMove3, 3);
			}
		});
		field_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_3);
		
		
		playerMove3.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove3.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove3.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_3.add(playerMove3);
		
		JPanel field_4 = new JPanel();
		field_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				play(playerMove4, 4);
			}
		});
		field_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_4);
		
		
		playerMove4.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove4.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove4.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_4.add(playerMove4);
		
		JPanel field_5 = new JPanel();
		field_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				play(playerMove5, 5);
			}
		});
		field_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_5);
		
		
		playerMove5.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove5.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove5.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_5.add(playerMove5);
		
		JPanel field_6 = new JPanel();
		field_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				play(playerMove6, 6);
			}
		});
		field_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_6);
		
		
		playerMove6.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove6.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove6.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_6.add(playerMove6);
		
		JPanel field_7 = new JPanel();
		field_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				play(playerMove7, 7);
			}
		});
		field_7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_7);
		
		
		playerMove7.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove7.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove7.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_7.add(playerMove7);
		
		JPanel field_8 = new JPanel();
		field_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				play(playerMove8, 8);
			}
		});
		field_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		board.add(field_8);
		
		
		playerMove8.setHorizontalTextPosition(SwingConstants.CENTER);
		playerMove8.setHorizontalAlignment(SwingConstants.CENTER);
		playerMove8.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
		field_8.add(playerMove8);
		
	}
}
