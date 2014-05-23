package game.GUI;

import game.core.MrVago;
import game.data.LOGG;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.SystemColor;

import javax.swing.SwingConstants;

public class GameGUI3 extends JFrame {

	private JPanel contentPane;

	private static String currPlayerName;
	private static String wiseType1;
	private static String wiseType2;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;

	private static String button1String;
	private static String button2String;
	private static String button3String;
	private static String button4String;
	
	private static boolean wasHalf = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */
	public GameGUI3() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem newGame = new JMenuItem("New Game");
		mnMenu.add(newGame);
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				LOGG.logInfo("New game started.");
				
				MrVago.basicInit();

				currPlayerName = JOptionPane.showInputDialog(null,
						"Player Name: ", "Name Input", 3);
				wiseType1 = (String) JOptionPane.showInputDialog(null,
						"Choose your first wise man's mastery!",
						"Wise man mastery menu", JOptionPane.QUESTION_MESSAGE,
						null, MrVago.getTypes(), MrVago.getTypes()[0]);
				wiseType2 = (String) JOptionPane.showInputDialog(null,
						"Choose your first wise man's mastery!",
						"Wise man mastery menu", JOptionPane.QUESTION_MESSAGE,
						null, MrVago.getTypes(), MrVago.getTypes()[0]);

				MrVago.gameInit(currPlayerName, wiseType1, wiseType2, 1000);

				renderMiniB();
				renderUpdate();
			}
		});

		JMenuItem questionOpt = new JMenuItem("Question options");
		mnMenu.add(questionOpt);

		JMenuItem close = new JMenuItem("Close");
		mnMenu.add(close);
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				LOGG.logInfo("----------------- Application closed -----------------");
				
				System.exit(0);
			}
		});

		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 12, 294, 305);
		contentPane.add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(318, 12, 170, 90);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(318, 114, 170, 203);
		contentPane.add(panel_2);
		panel_2.setLayout(null);


	}

	public void renderMiniB() {
		contentPane.remove(panel_1);
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(318, 12, 170, 90);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton stop = new JButton("Stop");
		stop.setBounds(27, 12, 117, 25);
		panel_1.add(stop);
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				renderStop();
			}
		});

		JButton help = new JButton("Help");
		help.setBounds(27, 49, 117, 25);
		panel_1.add(help);
		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String[] s = { "Audience", "Half", "Wise Man" };
				String str = (String) JOptionPane.showInputDialog(null,
						"Choose a help!", "Help menu",
						JOptionPane.QUESTION_MESSAGE, null, s, s[0]);
				if (str.equals("Audience")) {
					renderAudience();
				}
				if (str.equals("Half")) {
					renderHalf();

				}
				if (str.equals("Wise Man")) {
					renderWiseMan();
				}
			}
		});

		validate();
		repaint();

	}
	
	public void renderHalf(){
		if (MrVago.getCurrentPlayer().getHelp(1)) {
			MrVago.getCurrentPlayer().setHelp(1, false);
			
			MrVago.halfingHelp();
			
			wasHalf = true;
			
			renderUpdate();
			
			wasHalf = false;
			
		}else
			JOptionPane.showMessageDialog(null, "You already used this help!", "", 1);
	}
	
	public void renderWiseMan(){
		if(MrVago.getCurrentPlayer().getHelp(2)){
			
			MrVago.getCurrentPlayer().setHelp(2, false);
			MrVago.wiseManHelp();
			
			contentPane.remove(panel_2);
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.setBounds(318, 114, 170, 203);
			contentPane.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel wise1 = new JLabel(MrVago.getWiseMan1().getType() + ":");
			wise1.setBounds(12, 12, 70, 15);
			panel_2.add(wise1);
			
			JLabel wise1a = new JLabel(MrVago.getWiseAnswers().get(0).getAnswer());
			wise1a.setBounds(88, 12, 70, 15);
			panel_2.add(wise1a);
			
			JLabel wise3a = new JLabel(MrVago.getWiseAnswers().get(2).getAnswer());
			wise3a.setBounds(88, 176, 70, 15);
			panel_2.add(wise3a);
			
			JLabel wise3 = new JLabel(MrVago.getPreviousPlayer().getName() + ":");
			wise3.setBounds(12, 176, 70, 15);
			panel_2.add(wise3);
			
			JLabel wise2 = new JLabel(MrVago.getWiseMan2().getType() + ":");
			wise2.setBounds(12, 93, 70, 15);
			panel_2.add(wise2);
			
			JLabel wise2a = new JLabel(MrVago.getWiseAnswers().get(1).getAnswer());
			wise2a.setBounds(88, 93, 70, 15);
			panel_2.add(wise2a);
			
			validate();
			repaint();
		}
		else
			JOptionPane.showMessageDialog(null, "You already used this help!", "", 1);
	}

	public void renderAudience() {
		if (MrVago.getCurrentPlayer().getHelp(0)) {
			
			MrVago.getCurrentPlayer().setHelp(0, false);
			MrVago.audianceHelp();

			contentPane.remove(panel_2);
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.setBounds(318, 114, 170, 203);
			contentPane.add(panel_2);
			panel_2.setLayout(null);

			int d = MrVago.getActQuestion().getDisplayAnswers().get(3)
					.getVote() * 4;
			JLabel dLabel = new JLabel("  D");
			dLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			dLabel.setBackground(SystemColor.desktop);
			dLabel.setForeground(Color.BLACK);
			dLabel.setBounds(133, 176 - d, 25, 15 + d);
			dLabel.setOpaque(true);
			panel_2.add(dLabel);

			int c = MrVago.getActQuestion().getDisplayAnswers().get(2)
					.getVote() * 4;
			JLabel cLabel = new JLabel("  C");
			cLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			cLabel.setForeground(Color.BLACK);
			cLabel.setBackground(SystemColor.desktop);
			cLabel.setBounds(97, 176 - c, 25, 15 + c);
			cLabel.setOpaque(true);
			panel_2.add(cLabel);

			int a = MrVago.getActQuestion().getDisplayAnswers().get(0)
					.getVote() * 4;
			JLabel aLabel = new JLabel("  A");
			aLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			aLabel.setForeground(Color.BLACK);
			aLabel.setBackground(SystemColor.desktop);
			aLabel.setBounds(12, 176 - a, 25, 15 + a);
			aLabel.setOpaque(true);
			panel_2.add(aLabel);

			int b = MrVago.getActQuestion().getDisplayAnswers().get(1)
					.getVote() * 4;
			JLabel bLabel = new JLabel("  B");
			bLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			bLabel.setForeground(Color.BLACK);
			bLabel.setBackground(SystemColor.desktop);
			bLabel.setBounds(47, 176 - b, 25, 15 + b);
			bLabel.setOpaque(true);
			panel_2.add(bLabel);

			validate();
			repaint();
		}
		else
			JOptionPane.showMessageDialog(null, "You already used this help!", "", 1);
	}

	public void renderFail() {
		contentPane.remove(panel);
		contentPane.remove(panel_1);
		contentPane.remove(panel_2);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 12, 294, 305);
		contentPane.add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(318, 12, 170, 90);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(318, 114, 170, 203);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		MrVago.fail();

		JOptionPane.showMessageDialog(null, "You looose nigga", "", 1);

		JLabel prizeLabel = new JLabel("Prize:");
		prizeLabel.setBounds(30, 120, 70, 15);
		panel.add(prizeLabel);

		JLabel prizeNumber = new JLabel(Integer.toString(MrVago
				.getCurrentPlayer().getScore()));
		prizeNumber.setBounds(120, 120, 70, 15);
		panel.add(prizeNumber);

		validate();
		repaint();
	}

	public void renderStop() {
		contentPane.remove(panel);
		contentPane.remove(panel_1);
		contentPane.remove(panel_2);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 12, 294, 305);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(318, 12, 170, 90);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(318, 114, 170, 203);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JOptionPane.showMessageDialog(null, "coward", "", 1);

		JLabel prizeLabel = new JLabel("Prize:");
		prizeLabel.setBounds(30, 120, 70, 15);
		panel.add(prizeLabel);

		JLabel prizeNumber = new JLabel(Integer.toString(MrVago
				.getCurrentPlayer().getScore()));
		prizeNumber.setBounds(120, 120, 70, 15);
		panel.add(prizeNumber);

		validate();
		repaint();
	}

	public void renderWin() {
		contentPane.remove(panel_1);
		contentPane.remove(panel_2);
		contentPane.remove(panel);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 12, 294, 305);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(318, 12, 170, 90);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(318, 114, 170, 203);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		MrVago.getCurrentPlayer().setScore(MrVago.getPrizes()[15]);
		JOptionPane.showMessageDialog(null, "YoU aRe WiNnEr!!!4!!!!!!!44", "",
				1);

		JLabel prizeLabel = new JLabel("Prize:");
		prizeLabel.setBounds(30, 120, 70, 15);
		panel.add(prizeLabel);

		JLabel prizeNumber = new JLabel(Integer.toString(MrVago
				.getCurrentPlayer().getScore()));
		prizeNumber.setBounds(120, 120, 70, 15);
		panel.add(prizeNumber);

		validate();
		repaint();
	}

	public void renderUpdate() {
		if(!wasHalf)
			MrVago.update();

		contentPane.remove(panel);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 12, 294, 305);
		contentPane.add(panel);
		panel.setLayout(null);
		
		contentPane.remove(panel_2);
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(318, 114, 170, 203);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel roundLabel = new JLabel("Round: ");
		roundLabel.setBounds(12, 12, 70, 15);
		panel.add(roundLabel);

		JLabel roundNumber = new JLabel(Integer.toString(MrVago.getRound()));
		roundNumber.setBounds(94, 12, 70, 15);
		panel.add(roundNumber);

		JLabel prizeLabel = new JLabel("Prize:");
		prizeLabel.setBounds(12, 39, 70, 15);
		panel.add(prizeLabel);

		JLabel prizeNumber = new JLabel(Integer.toString(MrVago
				.getCurrentPlayer().getScore()));
		prizeNumber.setBounds(94, 39, 70, 15);
		panel.add(prizeNumber);

		JLabel questionLabel = new JLabel(MrVago.getActQuestion().getQuestion());
		questionLabel.setBounds(12, 85, 70, 15);
		panel.add(questionLabel);

		button1String = MrVago.getActQuestion().getDisplayAnswers().get(0)
				.getAnswer();
		JButton button1 = new JButton(button1String);
		button1.setBounds(12, 135, 270, 25);
		panel.add(button1);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (button1String.equals(MrVago.getActQuestion().getCorrect()
						.getAnswer())) {
					if (MrVago.getRound() != 15)
						renderUpdate();
					else
						renderWin();
				} else {
					renderFail();
				}
			}
		});

		button2String = MrVago.getActQuestion().getDisplayAnswers().get(1)
				.getAnswer();
		JButton button2 = new JButton(button2String);
		button2.setBounds(12, 172, 270, 25);
		panel.add(button2);
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (button2String.equals(MrVago.getActQuestion().getCorrect()
						.getAnswer())) {
					if (MrVago.getRound() != 15)
						renderUpdate();
					else
						renderWin();
				} else {
					renderFail();
				}
			}
		});

		button3String = MrVago.getActQuestion().getDisplayAnswers().get(2)
				.getAnswer();
		JButton button3 = new JButton(button3String);
		button3.setBounds(12, 209, 270, 25);
		panel.add(button3);
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (button3String.equals(MrVago.getActQuestion().getCorrect()
						.getAnswer())) {
					if (MrVago.getRound() != 15)
						renderUpdate();
					else
						renderWin();
				} else {
					renderFail();
				}
			}
		});

		button4String = MrVago.getActQuestion().getDisplayAnswers().get(3)
				.getAnswer();
		JButton button4 = new JButton(button4String);
		button4.setBounds(12, 246, 270, 25);
		panel.add(button4);
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (button4String.equals(MrVago.getActQuestion().getCorrect()
						.getAnswer())) {
					if (MrVago.getRound() != 15)
						renderUpdate();
					else
						renderWin();
				} else {
					renderFail();
				}
			}
		});

		validate();
		repaint();

	}
}
