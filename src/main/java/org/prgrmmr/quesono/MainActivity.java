package org.prgrmmr.quesono;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TimePicker;

public class MainActivity extends Activity {

    public static final String SUGESTOES = "sugestoes";
    public static final String SUGESTOES_DORMIR = "sugestoesDormir";

    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
        setContentView(R.layout.activity_main);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
    }

    public void sugerirHoraDormir(View view) {
        Intent intent = new Intent(this, SugestoesActivity.class);
        intent.putExtra(SUGESTOES_DORMIR, true);
        intent.putExtra(SUGESTOES, Calculadora.calcularHoraDormir(timePicker.getCurrentHour(), timePicker.getCurrentMinute()));
        Log.d("horas", timePicker.getCurrentHour().toString());
        startActivity(intent);
    }

    public void sugerirHoraAcordar(View view) {
        Intent intent = new Intent(this, SugestoesActivity.class);
        intent.putExtra(SUGESTOES_DORMIR, false);
        intent.putExtra(SUGESTOES, Calculadora.calcularHoraAcordar());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
