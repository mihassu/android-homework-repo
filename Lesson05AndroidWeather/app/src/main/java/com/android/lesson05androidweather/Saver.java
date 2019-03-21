package com.android.lesson05androidweather;

public final class Saver {

    private static Saver instance = null;
    boolean windIsChecked;
    boolean pressureIsChecked;

    private Saver(){
        this.windIsChecked = false;
        this.pressureIsChecked = false;
    }

    public boolean isWindIsChecked() {
        return windIsChecked;
    }

    public void setWindIsChecked(boolean windIsChecked) {
        this.windIsChecked = windIsChecked;
    }

    public boolean isPressureIsChecked() {
        return pressureIsChecked;
    }

    public void setPressureIsChecked(boolean pressureIsChecked) {
        this.pressureIsChecked = pressureIsChecked;
    }

    public static Saver getInstance(){
        if(instance == null){
            instance = new Saver();
        }
        return instance;
    }
}
