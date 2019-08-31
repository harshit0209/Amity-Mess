package apps.harshitc.aumpmess.models;

public class MyListData2 {
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int color;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    private String title,date,day;


    public MyListData2(String title, String date, String day, int color) {
        this.title = title;
        this.date = date;
        this.color=color;
        this.day = day;

    }


    public boolean setNew(String title,String date, String day, int color)
    {
        boolean ret=true;
        try{
            setDate(date);
            setDay(day);
            setColor(color);
            setTitle(title);}catch (Exception e)
        {ret =false;}
        return  ret;
    }

   }

