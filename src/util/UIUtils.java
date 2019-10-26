/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.JFormattedTextField;
import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Admin
 */
public class UIUtils {

    public static JDatePickerImpl showDatePicker() {
        UtilDateModel model = new UtilDateModel();

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateFormatter());
//        Date d = (Date) datePicker.getModel().getValue();
//        System.out.println(d.toString());

        return datePicker;
    }

    private static class DateFormatter extends JFormattedTextField.AbstractFormatter {

        private final String DATE_PATTERN = "dd/MM/yyyy";
        private SimpleDateFormat f = new SimpleDateFormat(DATE_PATTERN);

        @Override
        public Object stringToValue(String dateStr) throws ParseException {
            return f.parse(dateStr);
        }

        @Override
        public String valueToString(Object dateObj) throws ParseException {
            if (dateObj != null) {
                return f.format(dateObj);
            }

            return "";
        }

    }

}
