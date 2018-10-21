package graphiks;

public class Date {
    private int m_day;
    private int m_month;
    private int m_year;
    private boolean space_day;
    private boolean space_month;

    public Date(int day, int month, int year){
        m_day = day;
        m_month = month;
        m_year = year;
        space_day = String.valueOf(m_day).length() == 1;
        space_month = String.valueOf(m_month).length() == 1;
    }

    public String getDateString(){
        return String.valueOf(m_day) + "-" + String.valueOf(m_month) + "-" + String.valueOf(m_year);
    }

    private String getDateStringRaw(){
        String result = "";
        if (space_day) result += "9";
        result += String.valueOf(m_day);
        if (space_month) result += "9";
        result += String.valueOf(m_month);
        result += String.valueOf(m_year);

        return result;
    }

    public int getDateInteger(){
        return Integer.valueOf(getDateStringRaw());
    }

    public static Date decodeInteger(int date){
        String dates = String.valueOf(date);
        String day;
        String month;

        // Check is the day has only one integer
        if (dates.charAt(0) == 9) {
            day = String.valueOf(dates.charAt(0)) + String.valueOf(dates.charAt(1));
        } else{
            day = String.valueOf(dates.charAt(1));
        }

        // Check is the month has only one integer
        if (dates.charAt(2) == 9) {
            month = String.valueOf(dates.charAt(2)) + String.valueOf(dates.charAt(3));
        } else{
            month = String.valueOf(dates.charAt(3));
        }

        String year = String.valueOf(dates.charAt(4)) + String.valueOf(dates.charAt(5)) + String.valueOf(dates.charAt(6)) + String.valueOf(dates.charAt(7));

        return new Date(Integer.valueOf(day), Integer.valueOf(month), Integer.valueOf(year));
    }
}
