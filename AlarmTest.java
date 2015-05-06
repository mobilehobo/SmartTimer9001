import static org.junit.Assert.*;
import java.time.LocalTime;
import org.junit.Test;
public class AlarmTest
{
	@Test
	public void testAlarm()
	{
		LocalTime alarmParse = LocalTime.parse("12:00");
		LocalTime reminderParse = LocalTime.parse("11:00");
		Alarm test = new Alarm(alarmParse, "alarm", reminderParse, "reminder", false, true, "file");
		assertEquals("12:00", test.getAlarmTime().toString());
		assertEquals("alarm", test.getAlarmMessage());
		assertEquals("11:00", test.getReminderTime().toString());
		assertEquals("reminder", test.getReminderMessage());
		assertEquals(false, test.getRepeat());
		assertEquals(true, test.isEnabled());
		assertEquals("file", test.getFilePath());
		
		alarmParse = LocalTime.parse("14:33");
		reminderParse = LocalTime.parse("13:22");
		test.setAlarmTime(alarmParse);
		test.setAlarmMessage("alarm changed");
		test.setReminderTime(reminderParse);
		test.setReminderMessage("reminder changed");
		test.setRepeat(true);
		test.setEnable(false);
		test.setFilePath("new file path");
		
		assertEquals("14:33", test.getAlarmTime().toString());
		assertEquals("alarm changed", test.getAlarmMessage());
		assertEquals("13:22", test.getReminderTime().toString());
		assertEquals("reminder changed", test.getReminderMessage());
		assertEquals(true, test.getRepeat());
		assertEquals(false, test.isEnabled());
		assertEquals("new file path", test.getFilePath());
	}
}
