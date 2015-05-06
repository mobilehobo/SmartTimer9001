import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerWrapper implements ActionListener
{
	private Alarm alarm1;
	private Timer timer;
	private String alarmDate;
	private String reminderDate;
	
	public TimerWrapper(Alarm alarm)
	{
		this.alarm1 = alarm;
		this.timer = new Timer(59700, this);
	}
	
	public void start()
	{
		timer.start();
	}
	
	public void stop()
	{
		timer.stop();
	}
	
	public void setRepeats(boolean repeats)
	{
		timer.setRepeats(repeats);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		timer.stop();
		if(alarm1.isEnabled())
    	{
    		timer.start();
    		alarmDate = new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(System.currentTimeMillis()));
    		reminderDate = new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(System.currentTimeMillis()));
    		if(reminderDate.equals(alarm1.getReminderTime().toString()))
    		{    			
    			JFrame mainFrame = new JFrame("Reminder for Alarm");
    			mainFrame.setVisible(true);
    			mainFrame.setSize(400,100);	
    			JPanel reminderGridPanel = new JPanel(new GridBagLayout());
    			mainFrame.getContentPane().add(reminderGridPanel, BorderLayout.NORTH);
    			GridBagConstraints reminderGridBag = new GridBagConstraints();
    			
    			JLabel reminder = new JLabel(alarm1.getReminderMessage());
    			reminderGridBag.gridx = 0;
    			reminderGridBag.gridy = 5;
    			reminderGridPanel.add(reminder, reminderGridBag);
    		}
    		if(alarmDate.equals(alarm1.getAlarmTime().toString()))
    		{
    			try
    			{
    				JFrame mainFrame = new JFrame("Alarm");
        			mainFrame.setVisible(true);
        			mainFrame.setSize(400,100);
        			JPanel alarmGridPanel = new JPanel(new GridBagLayout());
        			mainFrame.getContentPane().add(alarmGridPanel, BorderLayout.NORTH);
        			GridBagConstraints alarmGridBag = new GridBagConstraints();
        			
        			JLabel alarmMessage = new JLabel(alarm1.getAlarmMessage());
        			alarmGridBag.gridx = 0;
        			alarmGridBag.gridy = 5;
        			alarmGridPanel.add(alarmMessage, alarmGridBag);
    				Runtime.getRuntime().exec(new String[] {alarm1.getFilePath()} );
    			}
    			catch(IOException ioe)
    			{
    				System.err.println("Unable to open file: " + alarm1.getFilePath());
    			}
    		}
    	}
        else
        {
        	timer.stop();
        }
        if(alarm1.getRepeat())
        {
        	timer.setRepeats(true);
        }
        else
        {
        	timer.stop();
        	timer.setRepeats(false);
        }
	}
}