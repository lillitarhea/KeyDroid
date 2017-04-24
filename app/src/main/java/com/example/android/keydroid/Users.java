package com.example.android.keydroid;

/**
 * Created by Lill_Rhea on 30/03/2017.
 */
public class Users {
    public long id;
    public String Name;
    public String Age;
    public String Duration;
    public String Latency;
    public String Valence;
    public String Arousal;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getLatency() {
        return Latency;
    }

    public void setLatency(String latency) {
        Latency = latency;
    }

    public String getValence() {
        return Valence;
    }

    public void setValence(String valence) {
        Valence = valence;
    }

    public String getArousal() {
        return Arousal;
    }

    public void setArousal(String arousal) {
        Arousal = arousal;
    }
}
