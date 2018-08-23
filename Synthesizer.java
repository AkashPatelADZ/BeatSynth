import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Synthesizer implements ActionListener
{
	JFrame frame;
	JLabel lblHeading, lblKey, lblNotes;
	JButton btnC, btnD, btnE, btnF, btnG, btnA, btnB, btnCS, btnDS, btnFS, btnGS, btnBB, btnPlay, btnPause, btnStop, btnEdit;
	JTextField txtKey, txtNotes;
	Player player = new Player();
	Pattern pattern;
	int tempo;
	String instrument;
	
	Synthesizer(AudioTracks AT)
	{
		tempo = AT.tempo;
		instrument = AT.instrument;
		
		frame = new JFrame("Synthesizer");
		frame.setLayout(null);
		
		lblHeading = new JLabel("Synthesizer");
		lblHeading.setBounds(100, 20, 300, 100);
		lblHeading.setFont(new Font("Serif", Font.PLAIN, 35));
		frame.add(lblHeading);
		
		lblKey = new JLabel("Key");
		lblKey.setBounds(600, 20, 50, 100);
		lblKey.setFont(new Font("Serif", Font.PLAIN, 25));
		frame.add(lblKey);
		
		txtKey = new JTextField("5");
		txtKey.setBounds(650, 50, 50, 40);
		txtKey.setFont(new Font("Serif", Font.PLAIN, 15));
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(txtKey);
		
		btnC = new JButton("C");
		btnC.setBounds(50, 100, 60, 300);
		btnC.setVerticalAlignment(SwingConstants.BOTTOM);
		btnC.setMargin(new Insets(10, 10, 10, 10));
		btnC.setBackground(Color.WHITE);
		btnC.addActionListener(this);
		frame.add(btnC);
		
		btnCS = new JButton("C#");
		btnCS.setBounds(110, 100, 40, 200);
		btnCS.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCS.setMargin(new Insets(10, 0, 10, 0));
		btnCS.setBackground(Color.DARK_GRAY);
		btnCS.setForeground(Color.WHITE);
		btnCS.addActionListener(this);
		frame.add(btnCS);
		
		btnD = new JButton("D");
		btnD.setBounds(150, 100, 60, 300);
		btnD.setVerticalAlignment(SwingConstants.BOTTOM);
		btnD.setMargin(new Insets(10, 10, 10, 10));
		btnD.setBackground(Color.WHITE);
		btnD.addActionListener(this);
		frame.add(btnD);

		btnDS = new JButton("D#");
		btnDS.setBounds(210, 100, 40, 200);
		btnDS.setVerticalAlignment(SwingConstants.BOTTOM);
		btnDS.setMargin(new Insets(10, 0, 10, 0));
		btnDS.setBackground(Color.DARK_GRAY);
		btnDS.setForeground(Color.WHITE);
		btnDS.addActionListener(this);
		frame.add(btnDS);
		
		btnE = new JButton("E");
		btnE.setBounds(250, 100, 60, 300);
		btnE.setVerticalAlignment(SwingConstants.BOTTOM);
		btnE.setMargin(new Insets(10, 10, 10, 10));
		btnE.setBackground(Color.WHITE);
		btnE.addActionListener(this);
		frame.add(btnE);

		btnF = new JButton("F");
		btnF.setBounds(350, 100, 60, 300);
		btnF.setVerticalAlignment(SwingConstants.BOTTOM);
		btnF.setMargin(new Insets(10, 10, 10, 10));
		btnF.setBackground(Color.WHITE);
		btnF.addActionListener(this);
		frame.add(btnF);

		btnFS = new JButton("F#");
		btnFS.setBounds(410, 100, 40, 200);
		btnFS.setVerticalAlignment(SwingConstants.BOTTOM);
		btnFS.setMargin(new Insets(10, 0, 10, 0));
		btnFS.setBackground(Color.DARK_GRAY);
		btnFS.setForeground(Color.WHITE);
		btnFS.addActionListener(this);
		frame.add(btnFS);
		
		btnG = new JButton("G");
		btnG.setBounds(450, 100, 60, 300);
		btnG.setVerticalAlignment(SwingConstants.BOTTOM);
		btnG.setMargin(new Insets(10, 10, 10, 10));
		btnG.setBackground(Color.WHITE);
		btnG.addActionListener(this);
		frame.add(btnG);

		btnGS = new JButton("G#");
		btnGS.setBounds(510, 100, 40, 200);
		btnGS.setVerticalAlignment(SwingConstants.BOTTOM);
		btnGS.setMargin(new Insets(10, 0, 10, 0));
		btnGS.setBackground(Color.DARK_GRAY);
		btnGS.setForeground(Color.WHITE);
		btnGS.addActionListener(this);
		frame.add(btnGS);
		
		btnA = new JButton("A");
		btnA.setBounds(550, 100, 60, 300);
		btnA.setVerticalAlignment(SwingConstants.BOTTOM);
		btnA.setMargin(new Insets(10, 10, 10, 10));
		btnA.setBackground(Color.WHITE);
		btnA.addActionListener(this);
		frame.add(btnA);

		btnBB = new JButton("Bb");
		btnBB.setBounds(610, 100, 40, 200);
		btnBB.setVerticalAlignment(SwingConstants.BOTTOM);
		btnBB.setMargin(new Insets(10, 0, 10, 0));
		btnBB.setBackground(Color.DARK_GRAY);
		btnBB.setForeground(Color.WHITE);
		btnBB.addActionListener(this);
		frame.add(btnBB);
		
		btnB = new JButton("B");
		btnB.setBounds(650, 100, 60, 300);
		btnB.setVerticalAlignment(SwingConstants.BOTTOM);
		btnB.setMargin(new Insets(10, 10, 10, 10));
		btnB.setBackground(Color.WHITE);
		btnB.addActionListener(this);
		frame.add(btnB);
		
		lblNotes = new JLabel("Notes :");
		lblNotes.setBounds(50, 400, 100, 100);
		lblNotes.setFont(new Font("Serif", Font.PLAIN, 25));
		frame.add(lblNotes);
		
		txtNotes = new JTextField(AT.pattern.toString());
		txtNotes.setBounds(150, 440, 560, 30);
		txtNotes.setEditable(false);
		frame.add(txtNotes);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(720, 440, 70, 30);
		frame.add(btnEdit);
		btnEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent AE)
			{
				if(txtNotes.isEditable() == false)
				{
					txtNotes.setEditable(true);
					btnEdit.setText("Save");
				}
				else
				{
					txtNotes.setEditable(false);
					btnEdit.setText("Edit");
				}
			}
		});
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(250, 490, 100, 50);
		btnPlay.addActionListener(this);
		frame.add(btnPlay);
		btnPlay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent AE)
			{
				if(player.getManagedPlayer().isPlaying())
				{
					try
					{
						player.getManagedPlayer().pause();
					}
					catch(Exception ex){};
				}
				else if(player.getManagedPlayer().isPaused())
				{
					try
					{
						player.getManagedPlayer().resume();
					}
					catch(Exception ex){};
				}
				else
				{
					try
					{
						player.getManagedPlayer().start(player.getSequence("T"+AT.tempo+" I["+AT.instrument+"] "+txtNotes.getText()));
					}
					catch(Exception ex){};
				}
			}
		});
		

		btnPause= new JButton("Pause");
		btnPause.setBounds(350, 490, 100, 50);
		btnPause.addActionListener(this);
		frame.add(btnPause);
		btnPause.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent AE)
			{
				if(player.getManagedPlayer().isPlaying())
				{
					try
					{
						player.getManagedPlayer().pause();
					}
					catch(Exception ex){};
				}
			}
		});

		btnStop = new JButton("Stop");
		btnStop.setBounds(450, 490, 100, 50);
		btnStop.addActionListener(this);
		frame.add(btnStop);
		btnStop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent AE)
			{
				if(player.getManagedPlayer().isPlaying())
				{
					try
					{
						player.getManagedPlayer().reset();
					}
					catch(Exception ex){};
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent E)
				{
					player.getManagedPlayer().reset();
					AT.pattern.clear();
					AT.pattern.add(txtNotes.getText());
				}
			});
		
		frame.setBounds(0, 0, 800, 600);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public void actionPerformed(ActionEvent AE)
	{
		if(AE.getSource() == btnC)
		{
			txtNotes.setText(txtNotes.getText()+" C"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] C"+txtKey.getText());
		}
		else if(AE.getSource() == btnD)
		{
			txtNotes.setText(txtNotes.getText()+" D"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] D"+txtKey.getText());
		}
		else if(AE.getSource() == btnE)
		{
			txtNotes.setText(txtNotes.getText()+" E"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] E"+txtKey.getText());
		}
		else if(AE.getSource() == btnF)
		{
			txtNotes.setText(txtNotes.getText()+" F"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] F"+txtKey.getText());
		}
		else if(AE.getSource() == btnG)
		{
			txtNotes.setText(txtNotes.getText()+" G"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] G"+txtKey.getText());
		}
		else if(AE.getSource() == btnA)
		{
			txtNotes.setText(txtNotes.getText()+" A"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] A"+txtKey.getText());
		}
		else if(AE.getSource() == btnB)
		{
			txtNotes.setText(txtNotes.getText()+" B"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] B"+txtKey.getText());
		}
		else if(AE.getSource() == btnCS)
		{
			txtNotes.setText(txtNotes.getText()+" C#"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] C#"+txtKey.getText());
		}
		else if(AE.getSource() == btnDS)
		{
			txtNotes.setText(txtNotes.getText()+" D#"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] D#"+txtKey.getText());
		}
		else if(AE.getSource() == btnFS)
		{
			txtNotes.setText(txtNotes.getText()+" F#"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] F#"+txtKey.getText());
		}
		else if(AE.getSource() == btnGS)
		{
			txtNotes.setText(txtNotes.getText()+" G#"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] G#"+txtKey.getText());
		}
		else if(AE.getSource() == btnBB)
		{
			txtNotes.setText(txtNotes.getText()+" Bb"+txtKey.getText());
			player.play("T"+tempo+" I["+instrument+"] Bb"+txtKey.getText());
		}
	}
}