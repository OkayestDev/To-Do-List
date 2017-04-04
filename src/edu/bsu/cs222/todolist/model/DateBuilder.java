package edu.bsu.cs222.todolist.model;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateBuilder {
    private GregorianCalendar gregorianCalendar;

    public static Builder setDay(int day) {
        return new Builder(day);
    }

    private static final class Builder {
        private int day;
        private int month;
        private int year;

         Builder(int day) {
            this.day = day;
        }

        public Builder AndMonth(int month) {
            this.month = month;
            return this;
        }

        public DateBuilder AndYear(int year) {
            this.year = year;
            return new DateBuilder(this);
        }

    }

    public DateBuilder(Builder builder) {
        int day = builder.day;
        int month = builder.month;
        int year = builder.year;
        gregorianCalendar = new GregorianCalendar(year, month, day);
    }

    public Date getDate() {
        return gregorianCalendar.getTime();
    }
}
