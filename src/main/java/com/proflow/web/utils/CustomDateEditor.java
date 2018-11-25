package com.proflow.web.utils;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化编辑器
 * Created by Leonid on 17/11/29.
 */
public class CustomDateEditor extends PropertyEditorSupport {
    private final boolean allowEmpty;
    private final int exactDateLength;
    /*
    private static DateFormat dateFormat_YMD;
    private static DateFormat dateFormat_YMDHMS;


    static {
        dateFormat_YMD = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat_YMD.setLenient(false);
        dateFormat_YMDHMS.setLenient(false);
    }
    */

    public CustomDateEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
        this.exactDateLength = -1;
    }

    public CustomDateEditor(boolean allowEmpty, int exactDateLength) {
        this.allowEmpty = allowEmpty;
        this.exactDateLength = exactDateLength;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if(this.allowEmpty && !StringUtils.hasText(text)) {
            this.setValue((Object)null);
        } else {
            if(text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
                throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
            }
            try {
                //2017-12-12
                if (text.length() == 10) {
                    DateFormat dateFormat_YMD = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat_YMD.setLenient(false);
                    this.setValue(dateFormat_YMD.parse(text));
                } else {
                    DateFormat dateFormat_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    dateFormat_YMDHMS.setLenient(false);
                    this.setValue(dateFormat_YMDHMS.parse(text));
                }
            } catch (ParseException var3) {
                throw new IllegalArgumentException("Could not parse date: " + var3.getMessage(), var3);
            }
        }

    }

    public String getAsText() {
        Date value = (Date)this.getValue();
        DateFormat dateFormat_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat_YMDHMS.setLenient(false);
        return value != null?dateFormat_YMDHMS.format(value):"";
    }
}
