import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfugue.pattern.Pattern;

public class AudioTracks
{
	int index;
	Pattern trk = new Pattern("");
	Pattern pattern = new Pattern("");
	String instrument = "PIANO";
	int tempo = 120;
	int repeat = 1;
	int volume = 8;
	
	JButton btnCustomize;
	JCheckBox chk;
	JTextField txtTitle = new JTextField("Empty track", 30);
	
	public AudioTracks(JPanel pnlTracks, int i)
	{
		index = i;
		
		JPanel panel= new JPanel(new FlowLayout());
		panel.setPreferredSize(new Dimension(pnlTracks.getHeight()/8, pnlTracks.getWidth()));
		chk = new JCheckBox();
		chk.setPreferredSize(new Dimension(50, 50));
		txtTitle.setEditable(false);
		btnCustomize = new JButton("Customize");
		panel.add(chk);
		panel.add(txtTitle);
		panel.add(btnCustomize);
		
		pnlTracks.add(panel);
	}
}