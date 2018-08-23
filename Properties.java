import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class Properties
{
	public Properties(AudioTracks AT)
	{
		JFrame frame = new JFrame("Properties");
		frame.setBounds(500, 200, 400, 500);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 50, 20, 10);
		gbc.anchor = GridBagConstraints.WEST;
		
		JLabel lblInstrument = new JLabel("Instrument");
		gbc.gridy = 0;
		gbc.gridx = 0;
		frame.add(lblInstrument, gbc);
		
		String[] instruments = new String[] {"PIANO", "GUITAR", "VIOLIN",
				"STEEL_DRUMS", "SYNTH_DRUM", "ACCORDIAN",
				"ELECTRIC_CLEAN_GUITAR", "TUBA", "RAIN", "FLUTE",
				"SHANAI"};
		JComboBox<String> cmbInstruments = new JComboBox<>(instruments);
		gbc.gridy = 0;
		gbc.gridx = 1;
		frame.add(cmbInstruments, gbc);
		cmbInstruments.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				AT.instrument = cmbInstruments.getSelectedItem().toString();
				AT.txtTitle.setText(AT.instrument);
				AT.txtTitle.setEditable(true);
			}
		});

		JLabel lblPattern = new JLabel("Pattern");
		gbc.gridy = 1;
		gbc.gridx = 0;
		frame.add(lblPattern, gbc);
		
		JButton btnPattern = new JButton("Create Pattern");
		gbc.gridy = 1;
		gbc.gridx = 1;
		frame.add(btnPattern, gbc);

		btnPattern.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				new Synthesizer(AT);
			}
		});
		
		JLabel lblTempo = new JLabel("Tempo");
		gbc.gridy = 2;
		gbc.gridx = 0;
		frame.add(lblTempo, gbc);
		
		JTextField txtTempo = new JTextField(""+AT.tempo, 5);
		gbc.gridy = 2;
		gbc.gridx = 1;
		frame.add(txtTempo, gbc);
		
		JLabel lblRepeat = new JLabel("Repeat");
		gbc.gridy = 3;
		gbc.gridx = 0;
		frame.add(lblRepeat, gbc);
		
		JTextField txtRepeat = new JTextField(""+AT.repeat, 5);
		gbc.gridy = 3;
		gbc.gridx = 1;
		frame.add(txtRepeat, gbc);
		
		JLabel lblVolume = new JLabel("Volume");
		gbc.gridy = 4;
		gbc.gridx = 0;
		frame.add(lblVolume, gbc);

		JSlider sldVolume = new JSlider(JSlider.HORIZONTAL, 0, 10, AT.volume);
		sldVolume.setPaintTicks(true);
		sldVolume.setPaintLabels(true);
		sldVolume.setMajorTickSpacing(1);
		sldVolume.createStandardLabels(1);
		gbc.gridy = 4;
		gbc.gridx = 1;
		frame.add(sldVolume, gbc);
		
		JButton btnSave = new JButton("Save");
		gbc.gridy = 5;
		gbc.gridx = 0;
		frame.add(btnSave, gbc);
		
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				AT.tempo = Integer.parseInt(txtTempo.getText());
				AT.repeat = Integer.parseInt(txtRepeat.getText());
				AT.volume = sldVolume.getValue();
				//AT.trk.clear();
				//AT.trk.add(":CON(935,"+(AT.volume*1500)+") "+"T"+AT.tempo+" I["+AT.instrument+"] "+AT.pattern);
				//AT.trk.repeat(AT.repeat);
			}
		});
		
		frame.setVisible(true);
		frame.setResizable(false);
	}
}