import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.ManagedPlayerListener;
import org.jfugue.player.Player;

public class BeatSynth implements ActionListener
{
	int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	AudioTracks[] AT = new AudioTracks[10];
	JSlider sldVolume = new JSlider(JSlider.VERTICAL, 0, 100, 70);
	
	Player player = new Player();
	Pattern song = new Pattern();
	
	public BeatSynth()
	{
		JFrame frame = new JFrame("BeatSynth Music Synthesizer");
		frame.setLayout(null);
		frame.setMinimumSize(new Dimension(1200, 700));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel pnlLogo = new JPanel(new FlowLayout());
		pnlLogo.setBounds(0, 0, width, height/7);
		pnlLogo.setLayout(new FlowLayout());
		ImageIcon image = new ImageIcon("Capture.png");
		JLabel lblLogo = new JLabel("", image, JLabel.CENTER);
		pnlLogo.add(lblLogo);
		pnlLogo.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel pnlVolume = new JPanel();
		pnlVolume.setBounds(0, height/7, width/5, height-height/7); 
		pnlVolume.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 20, 5);
		pnlVolume.setBackground(Color.blue);
		JLabel txtVolume = new JLabel("Volume");
		txtVolume.setFont(new Font("Serif", Font.PLAIN, 20));
		txtVolume.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		pnlVolume.add(txtVolume, gbc);
		sldVolume = new JSlider(JSlider.VERTICAL, 0, 100, 80);
		sldVolume.setPaintTicks(true);
		sldVolume.setPaintLabels(true);
		sldVolume.setMajorTickSpacing(20);
		sldVolume.setMinorTickSpacing(10);
		sldVolume.createStandardLabels(20);
		gbc.gridx = 0;
		gbc.gridy = 1;
		pnlVolume.add(sldVolume, gbc);
		JLabel lblVolume = new JLabel("80");
		sldVolume.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent CE)
			{
				lblVolume.setText(""+sldVolume.getValue());
			}
		});
		lblVolume.setForeground(Color.WHITE);
		lblVolume.setFont(new Font("Serif", Font.PLAIN, 15));
		gbc.gridx = 0;
		gbc.gridy = 2;
		pnlVolume.add(lblVolume, gbc);
		JButton btnPlay = new JButton("Play");
		gbc.gridy = 3;
		btnPlay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(player.getManagedPlayer().isPlaying())
				{
					try { player.getManagedPlayer().pause(); }
					catch(Exception ex){}
				}
				else if(player.getManagedPlayer().isPaused())
				{
					try { player.getManagedPlayer().resume(); }
					catch(Exception ex){}
				}
				
				else
				{
		//			try { player.getManagedPlayer().start(player.getSequence("V0 I[PIANO] b g c d V1 I[SYNTH_DRUM] g g c c")); }
		//			catch(Exception e){}
					
					song.clear();
					player.getManagedPlayer().finish();
					int v = sldVolume.getValue()*160;
					for(int i=0; i<9; i++)
						if(AT[i].chk.isSelected())
							song.add(" V"+AT[i].index+" :CON(935, "+(v*AT[i].volume/10)+") T"+AT[i].tempo+" I["+AT[i].instrument+"] "+
									(new Pattern(AT[i].pattern)).repeat(AT[i].repeat));
					try { player.getManagedPlayer().start(player.getSequence(song.toString())); }
					catch(Exception ex){}
				}
			}
		});
		pnlVolume.add(btnPlay, gbc);
		JButton btnPause = new JButton("Pause");
		gbc.gridy = 4;
		btnPause.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try { player.getManagedPlayer().pause(); }
				catch(Exception ex){ }
			}
		});
		pnlVolume.add(btnPause, gbc);
		JButton btnStop = new JButton("Stop");
		gbc.gridy = 5;
		btnStop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{	player.getManagedPlayer().finish();
					player.getManagedPlayer().reset();
				}
				catch(Exception ex){ }
			}
		});
		pnlVolume.add(btnStop, gbc);
		pnlVolume.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel pnlTracks = new JPanel();
		pnlTracks.setBounds(width/7, height/7, width-width/7, height-height/7);
		pnlTracks.setBackground(Color.green);
		pnlTracks.setLayout(new BoxLayout(pnlTracks, BoxLayout.Y_AXIS));
		for(int i=0; i<9; i++)
		{
			AT[i] = new AudioTracks(pnlTracks, i);
			AT[i].btnCustomize.addActionListener(this);
		}
		
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent E)
			{
				player.getManagedPlayer().reset();
			}
		});
		
		frame.add(pnlVolume);
		frame.add(pnlLogo);
		frame.add(pnlTracks);
		frame.setResizable(true);
		frame.setVisible(true);
	}
	
	public static void main(String args[])
	{
		new BeatSynth();
	}

	public void actionPerformed(ActionEvent ae)
	{
		for(int i=0; i<9; i++)
			if(ae.getSource() == AT[i].btnCustomize)
			{
				AT[i].txtTitle.setEditable(true);
				AT[i].txtTitle.setText("PIANO");
				new Properties(AT[i]);
			}
	}
}