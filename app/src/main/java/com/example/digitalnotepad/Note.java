package com.example.digitalnotepad;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable, Comparable<Note>
{
    private int id;
    private String title;
    private String note;
    private String date;
    private String hour;
    private String adress;
    private int color;
    private int priority;

    public Note(){}

    public Note(String title, String note, String date, String hour, String adress, int color, int priority)
    {
        this.title = title;
        this.note = note;
        this.date = date;
        this.hour = hour;
        this.adress = adress;
        this.color = color;
        this.priority = priority;
    }

    public Note(String title, String note, int color, int priority)
    {
        this.title = title;
        this.note = note;
        this.color = color;
        this.priority = priority;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        note = in.readString();
        date = in.readString();
        hour = in.readString();
        adress = in.readString();
        color = in.readInt();
        priority = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int _getId() {
        return id;
    }

    public void _setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getHour()
    {
        return hour;
    }

    public void setHour(String hour)
    {
        this.hour = hour;
    }

    public String getAdress()
    {
        return adress;
    }

    public void setAdress(String adress)
    {
        this.adress = adress;
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(note);
        dest.writeString(date);
        dest.writeString(hour);
        dest.writeString(adress);
        dest.writeInt(color);
        dest.writeInt(priority);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", adress='" + adress + '\'' +
                ", color=" + color +
                ", priority=" + priority +
                '}';
    }

    @Override
    public int compareTo(Note o)
    {
        return 0;
    }
}
