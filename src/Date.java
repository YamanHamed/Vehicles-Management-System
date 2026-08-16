public class Date {
    private int day;
    private int month;
    private int year;

    public Date() {
        this(1, 1, 2000);
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Comparison methods (before, after, equal)
    public boolean isBefore(Date other) {
        if (this.year != other.year)
            return this.year < other.year;
        if (this.month != other.month)
            return this.month < other.month;
        return this.day < other.day;
    }

    public boolean isAfter(Date other) {
        if (this.year != other.year)
            return this.year > other.year;
        if (this.month != other.month)
            return this.month > other.month;
        return this.day > other.day;
    }

    public boolean isEqual(Date other) {
        return this.day == other.day && this.month == other.month && this.year == other.year;
    }

    // Add days to the date
    public Date addDays(int days) {
        int newDay = this.day + days;
        int newMonth = this.month;
        int newYear = this.year;

        if (days >= 0) {
            while (newDay > daysInMonth(newMonth, newYear)) {
                newDay -= daysInMonth(newMonth, newYear);
                newMonth++;
                if (newMonth > 12) {
                    newMonth = 1;
                    newYear++;
                }
            }
        } else {
            while (newDay <= 0) {
                newMonth--;
                if (newMonth < 1) {
                    newMonth = 12;
                    newYear--;
                }
                newDay += daysInMonth(newMonth, newYear);
            }
        }
        return new Date(newDay, newMonth, newYear);
    }

    // Calculate days between 2 dates
    public int calcDaysBetween(Date other) {
        if (this.isEqual(other))
            return 0;
        else if (this.isAfter(other)) {
            // return a negative number ( because the "this" date is after the "other" date)
            return -other.calcDaysBetween(this);
        } else {
            // return a positive number (because the "this" date is before the "other" date)
            int days = 0;
            Date current = new Date(this.day, this.month, this.year);
            while (current.isBefore(other)) {
                current = current.addDays(1);
                days++;
            }
            return days;
        }

    }

    // 30 , 31 or 28
    public int daysInMonth(int month, int year) {
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else if (month == 2) {
            // Leap year check:
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                return 29;
            } else {
                return 28;
            }
        } else {
            return 31;
        }
    }

    public void printDate() {
        System.out.println(day + "/" + month + "/" + year);
    }

    // Getters
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}