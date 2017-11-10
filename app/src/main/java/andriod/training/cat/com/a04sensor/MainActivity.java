package andriod.training.cat.com.a04sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private TextView tv_sensor_name;
    private TextView tv_sensor_status_x;
    private TextView tv_sensor_status_y;
    private TextView tv_sensor_status_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Referent TextView Total Counter
        tv_sensor_name = (TextView) findViewById(R.id.lo_tv_sensor_name);
        tv_sensor_status_x = (TextView) findViewById(R.id.lo_tv_sensor_status_x);
        tv_sensor_status_y = (TextView) findViewById(R.id.lo_tv_sensor_status_y);
        tv_sensor_status_z = (TextView) findViewById(R.id.lo_tv_sensor_status_z);
        //Set TextView Total Counter
        //tv_counter.setText(String.valueOf(total_counter));

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            String title = "Sensor:" + event.sensor.getName();
            String text_1 = String.format(Locale.getDefault(), "X: %.4f", event.values[0]);
            String text_2 = String.format(Locale.getDefault(), "Y: %.4f", event.values[1]);
            String text_3 = String.format(Locale.getDefault(), "Z: %.4f", event.values[2]);
            tv_sensor_name.setText(title);
            tv_sensor_status_x.setText(text_1);
            tv_sensor_status_y.setText(text_2);
            tv_sensor_status_z.setText(text_3);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

}
