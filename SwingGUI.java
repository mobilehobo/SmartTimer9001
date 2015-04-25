import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.stream.Stream;
import java.awt.*;
import java.awt.event.*;

public class SwingGUI 
{
	public static Alarm alarm1;
	private static JTextField alarmTime;
	private static JTextField reminderTime;
	private static JTextArea alarmMessage;
	private static JTextArea reminderMessage;
	private static JTextField repeat;
	private static JTextField enable;
	private static JTextField filePathField;
	private static File settings = new File("C:\\settings.txt");
	
	public static void main(String[] args) throws ParseException
	{
		alarm1 = new Alarm(null,"Default", null, "Default", false, false, "Default");
		
		JFrame mainFrame = new JFrame("SmartTimer 9001");
		mainFrame.setVisible(true);
		mainFrame.setSize(500,500);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		JPanel grisettingPanel = new JPanel(new GridBagLayout());
		mainFrame.getContentPane().add(grisettingPanel, BorderLayout.NORTH);
		GridBagConstraints gridBag = new GridBagConstraints();
		JLabel mainLabel = new JLabel("Welcome to our SmartTimer 9001.  Please Select the Alarm to set.");
		gridBag.gridx = 0;
		gridBag.gridy = 0;
		gridBag.insets = new Insets(10,10,10,10);
		grisettingPanel.add(mainLabel, gridBag);
		JButton setAlarmButton = new JButton("Set Alarm");
		gridBag.gridx = 0;
		gridBag.gridy = 10;
		grisettingPanel.add(setAlarmButton, gridBag);
		setAlarmButton.addActionListener(new AlarmFrame());
	}		
	static class AlarmFrame implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			JFrame alarmJFrame = new JFrame("Alarm 1");
			alarmJFrame.setVisible(true);
			alarmJFrame.setSize(1000,1000);
			JPanel alarmFramePanel = new JPanel(new GridBagLayout());
			alarmJFrame.getContentPane().add(alarmFramePanel, BorderLayout.NORTH);
			GridBagConstraints alarmGridBag = new GridBagConstraints();
			
			System.out.println("\nCurrent Settings:");
			
			try (Stream<String> inputStream = Files.lines(Paths.get(settings.toString()), Charset.defaultCharset()))
			{
				inputStream.forEach(System.out::println);
			}
			catch (IOException e1){}
			
			JLabel alarmTimeLabel = new JLabel("Please set alarm alarmTime in HH:mm (military alarmTime only)");
			alarmGridBag.gridx = 0;
			alarmGridBag.gridy = 0;
			alarmGridBag.anchor = GridBagConstraints.WEST;
			alarmGridBag.fill = GridBagConstraints.BOTH;
			alarmGridBag.insets = new Insets(40,15,15,0);
			alarmFramePanel.add(alarmTimeLabel, alarmGridBag);
			
			alarmTime = new JTextField("HH:mm");
			alarmGridBag.gridx = 1;
			alarmGridBag.gridy = 0;
			alarmGridBag.fill = GridBagConstraints.HORIZONTAL;
			alarmGridBag.weightx = 1;
			alarmFramePanel.add(alarmTime, alarmGridBag);
			
			alarmMessage = new JTextArea("Alarm Message");
			alarmGridBag.gridx = 1;
			alarmGridBag.gridy = 1;
			alarmGridBag.fill = GridBagConstraints.HORIZONTAL;
			alarmGridBag.weightx = 1;
			alarmFramePanel.add(alarmMessage, alarmGridBag);
			
			reminderMessage = new JTextArea("Reminder Message");
			alarmGridBag.gridx = 1;
			alarmGridBag.gridy = 3;
			alarmGridBag.fill = GridBagConstraints.HORIZONTAL;
			alarmGridBag.weightx = 1;
			alarmFramePanel.add(reminderMessage, alarmGridBag);
			
			JButton saveButton = new JButton("Save");
			alarmGridBag.gridx = 0;
			alarmGridBag.gridy = 10;
			alarmFramePanel.add(saveButton, alarmGridBag);
			
			JLabel rLabel = new JLabel("Please set reminder alarmTime in HH:mm (military alarmTime only)");
			alarmGridBag.gridx = 0;
			alarmGridBag.gridy = 2;
			alarmFramePanel.add(rLabel, alarmGridBag);
			
			JLabel alarmMessageLabel = new JLabel("Please set alarm message");
			alarmGridBag.gridx = 0;
			alarmGridBag.gridy = 1;
			alarmFramePanel.add(alarmMessageLabel, alarmGridBag);
			
			JLabel reminderMessageLabel = new JLabel("Please set reminder message");
			alarmGridBag.gridx = 0;
			alarmGridBag.gridy = 3;
			alarmFramePanel.add(reminderMessageLabel, alarmGridBag);

			JLabel repeatLabel = new JLabel("Repeat?");
			alarmGridBag.gridx = 0;
			alarmGridBag.gridy = 4;
			alarmFramePanel.add(repeatLabel, alarmGridBag);
			
			JLabel enableLabel = new JLabel("Enable?");
			alarmGridBag.gridx = 0;
			alarmGridBag.gridy = 5;
			alarmFramePanel.add(enableLabel, alarmGridBag);
			
			JButton browse = new JButton("Browse files");
			alarmGridBag.gridx = 1;
			alarmGridBag.gridy = 7;
			alarmFramePanel.add(browse, alarmGridBag);
			
			browse.addActionListener(new Browse());
			
			reminderTime = new JTextField("HH:mm");
			alarmGridBag.gridx = 1;
			alarmGridBag.gridy = 2;
			alarmGridBag.fill = GridBagConstraints.HORIZONTAL;
			alarmGridBag.weightx = 1;
			alarmFramePanel.add(reminderTime, alarmGridBag);
			
			filePathField = new JTextField("No File Chosen");
			filePathField.setEditable(false);
			alarmGridBag.gridx = 1;
			alarmGridBag.gridy = 6;
			alarmGridBag.fill = GridBagConstraints.HORIZONTAL;
			alarmGridBag.weightx = 1;
			alarmFramePanel.add(filePathField, alarmGridBag);
			
			repeat = new JTextField("y/n");
			alarmGridBag.gridx = 1;
			alarmGridBag.gridy = 4;
			alarmGridBag.fill = GridBagConstraints.HORIZONTAL;
			alarmGridBag.weightx = 1;
			alarmFramePanel.add(repeat, alarmGridBag);
			
			enable = new JTextField("y/n");
			alarmGridBag.gridx = 1;
			alarmGridBag.gridy = 5;
			alarmFramePanel.add(enable, alarmGridBag);
			
			saveButton.addActionListener(new alarmTimeGUI());
		}
	}
	
	static class Browse implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			JFileChooser browse = new JFileChooser();
			browse.setDialogTitle("Select file to be opened at specified alarmTime");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("EXE Files", "exe");
			browse.setFileFilter(filter);
			
			if(browse.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				alarm1.setFilePath(browse.getSelectedFile().toString());
				filePathField.setText(alarm1.getFilePath());
			}	
		}
	}
	static class alarmTimeGUI implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			LocalTime alarmParse = LocalTime.parse(alarmTime.getText());
			alarm1.setAlarmTime(alarmParse);
			
			alarm1.setAlarmMessage(alarmMessage.getText());
			LocalTime reminderParse = LocalTime.parse(reminderTime.getText());
			alarm1.setReminderTime(reminderParse);
			alarm1.setReminderMessage(reminderMessage.getText());
			if(repeat.getText().toLowerCase().equals("y"))
				alarm1.setRepeat(true);
			else
				alarm1.setRepeat(false);
			if(enable.getText().toLowerCase().equals("y"))
				alarm1.setEnable(true);
			else
				alarm1.setEnable(false);
			
			JFrame settingFrame = new JFrame("Alarm Info");
			settingFrame.setVisible(true);
			settingFrame.setSize(500,500);	
			JPanel settingPanel = new JPanel(new GridBagLayout());
			settingFrame.getContentPane().add(settingPanel, BorderLayout.NORTH);
			GridBagConstraints settingGridBag = new GridBagConstraints();
			
			JLabel aalarmTimeLabel = new JLabel(alarm1.getAlarmTime().toString());
			settingGridBag.gridx = 0;
			settingGridBag.gridy = 0;
			settingPanel.add(aalarmTimeLabel, settingGridBag);
			
			JLabel aMessageLabel = new JLabel(alarm1.getAlarmMessage());
			settingGridBag.gridx = 0;
			settingGridBag.gridy = 1;
			settingPanel.add(aMessageLabel, settingGridBag);
			
			JLabel reminderTimeLabel = new JLabel(alarm1.getReminderTime().toString());
			settingGridBag.gridx = 0;
			settingGridBag.gridy = 2;
			settingPanel.add(reminderTimeLabel, settingGridBag);
			
			JLabel rMessageLabel = new JLabel(alarm1.getReminderMessage());
			settingGridBag.gridx = 0;
			settingGridBag.gridy = 3;
			settingPanel.add(rMessageLabel, settingGridBag);
			
			JLabel repeatLabel = new JLabel(alarm1.getRepeat().toString());
			settingGridBag.gridx = 0;
			settingGridBag.gridy = 4;
			settingPanel.add(repeatLabel, settingGridBag);
			
			JLabel enableLabel = new JLabel(alarm1.isEnabled().toString());
			settingGridBag.gridx = 0;
			settingGridBag.gridy = 5;
			settingPanel.add(enableLabel, settingGridBag);
			
			JLabel fileLabel = new JLabel(alarm1.getFilePath());
			settingGridBag.gridx = 0;
			settingGridBag.gridy = 6;
			settingPanel.add(fileLabel,settingGridBag);
			
			try
			{
				FileWriter output = new FileWriter(settings, false);
				output.write(alarm1.getAlarmTime().toString() + "\n");
				output.write(alarm1.getAlarmMessage() + "\n");
				output.write(alarm1.getReminderTime().toString() + "\n");
				output.write(alarm1.getReminderMessage() + "\n");
				output.write(alarm1.getRepeat().toString() + "\n");
				output.write(alarm1.isEnabled().toString() + "\n");
				output.write(alarm1.getFilePath());
				output.close();
			}
			catch (IOException ee){}
			
			System.out.println("\nNew Settings:");
			
			try (Stream<String> inputStream = Files.lines(Paths.get(settings.toString()), Charset.defaultCharset()))
			{
				inputStream.forEach(System.out::println);
			}
			catch (IOException e1){}
		}
	}
}
