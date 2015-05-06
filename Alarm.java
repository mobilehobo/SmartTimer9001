import java.time.LocalTime;

public class Alarm 
{
	public LocalTime alarmTime;
	public String alarmMessage;
	public String reminderMessage;
	public LocalTime reminderTime;
	public Boolean repeat;
	public Boolean enable;
	public String filePath;

	public Alarm(LocalTime aT, String aM, LocalTime rT, String rM, Boolean r, Boolean e, String f)
	{
		alarmTime = aT;
		alarmMessage = aM;
		reminderMessage = rM;
		reminderTime = rT;
		repeat  = r;
		enable = e;
		filePath = f;
	}
	
	public void setAlarmTime(LocalTime timeParse)
	{
		this.alarmTime = timeParse;
	}
	
	public void setAlarmMessage(String newAlarmMessage)
	{
		this.alarmMessage = newAlarmMessage;
	}
	
	public void setReminderTime(LocalTime reminderParse)
	{
		this.reminderTime = reminderParse;
	}
	
	public void setReminderMessage(String newReminderMessage)
	{
		this.reminderMessage = newReminderMessage;
	}
	
	public void setRepeat(Boolean newRepeat)
	{
		this.repeat = newRepeat;
	}
	
	public void setEnable(Boolean newEnable)
	{
		this.enable = newEnable;
	}
	
	public void setFilePath(String newFilePath)
	{
		this.filePath = newFilePath;
	}
	
	public LocalTime getAlarmTime()
	{
		return this.alarmTime;
	}
	
	public String getAlarmMessage()
	{
		return this.alarmMessage;
	}
	
	public LocalTime getReminderTime()
	{
		return this.reminderTime;
	}
	
	public String getReminderMessage()
	{
		return this.reminderMessage;
	}
	
	public Boolean getRepeat()
	{
		return this.repeat;
	}

	public Boolean isEnabled()
	{
		return this.enable;
	}
	
	public String getFilePath()
	{
		return this.filePath;
	}
}
