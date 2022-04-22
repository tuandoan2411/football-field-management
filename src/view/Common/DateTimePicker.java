package view.Common;

import org.jdesktop.swingx.calendar.SingleDaySelectionModel;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.awt.*;

public class DateTimePicker extends JXDatePicker {
    private JSpinner timeSpinner;
    private JPanel timePanel;
    private DateFormat timeFormat;
    private Date commitTime;
    private Date result;

    public DateTimePicker() {
        super();
        Date now = new Date();
        setDate(now);
        commitTime = now;
        /*try {
            super.commitEdit();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        getMonthView().setSelectionModel(new SingleDaySelectionModel());
    }

    public DateTimePicker( Date d ) {
        this();
        setDate(d);
    }

    public void commitEdit() throws ParseException {
        commitTime();
        super.commitEdit();
    }

    public void cancelEdit() {
        super.cancelEdit();
        setTimeSpinners();
    }

    @Override
    public JPanel getLinkPanel() {
        super.getLinkPanel();
        if( timePanel == null ) {
            timePanel = createTimePanel();
        }
        setTimeSpinners();
        return timePanel;
    }

    private JPanel createTimePanel() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        //newPanel.add(panelOriginal);

        SpinnerDateModel dateModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(dateModel);
        if( timeFormat == null ) timeFormat = DateFormat.getTimeInstance( DateFormat.SHORT );
        updateTextFieldFormat();
        newPanel.add(new JLabel( "Time:" ) );
        newPanel.add(timeSpinner);
        newPanel.setBackground(Color.WHITE);
        return newPanel;
    }

    private void updateTextFieldFormat() {
        if( timeSpinner == null ) return;
        JFormattedTextField tf = ((JSpinner.DefaultEditor) timeSpinner.getEditor()).getTextField();
        DefaultFormatterFactory factory = (DefaultFormatterFactory) tf.getFormatterFactory();
        DateFormatter formatter = (DateFormatter) factory.getDefaultFormatter();
        // Change the date format to only show the hours
        formatter.setFormat( timeFormat );
    }

    private void commitTime() {
        GregorianCalendar commit = new GregorianCalendar();
        commit.setTime( new Date() );
        commit.set(Calendar.SECOND, 0);
        commitTime = commit.getTime();
        Date date = getDate();
        //System.out.println("++++++++++++++ " + date);
        if (date != null) {
            Date time = (Date) timeSpinner.getValue();
            //System.out.println("#################### " + time);
            GregorianCalendar timeCalendar = new GregorianCalendar();
            timeCalendar.setTime( time );

            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            //System.out.println("calendar.setTime(date); " + calendar);
            calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get( Calendar.HOUR_OF_DAY ) );
            calendar.set(Calendar.MINUTE, timeCalendar.get( Calendar.MINUTE ) );
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            System.out.println("ttttt " + calendar);

            Date newDate = calendar.getTime();
            result = newDate;
            System.out.println("Down Date newDate = calendar.getTime(); " + newDate);
            //System.out.println("newDate " + newDate);
            setDate(newDate);
            System.out.println("DOWN setDate(newDate); " + getDate());
        }

    }

    public Date getResult() {
        return result;
    }

    public void setResult(Date result) {
        this.result = result;
    }

    public Date getCommitTime(){
        return commitTime;
    }

    private void setTimeSpinners() {
        Date date = getDate();
        if (date != null) {
            timeSpinner.setValue( date );
        }
    }

    public DateFormat getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(DateFormat timeFormat) {
        this.timeFormat = timeFormat;
        updateTextFieldFormat();
    }

    public static void main(String[] args) {
        Date date = new Date();

        JFrame frame = new JFrame();
        frame.setTitle("Date Time Picker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setFormats( DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.MEDIUM ) );
        dateTimePicker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );

        dateTimePicker.setDate(date);



        frame.getContentPane().add(dateTimePicker);
        frame.pack();
        frame.setVisible(true);
    }
}
