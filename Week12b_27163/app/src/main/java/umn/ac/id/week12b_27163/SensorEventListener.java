package umn.ac.id.week12b_27163;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

public interface SensorEventListener {
    void onSensorChanged(SensorEvent event);

    void onAccuracyChanged(Sensor sensor, int accuracy);
}
