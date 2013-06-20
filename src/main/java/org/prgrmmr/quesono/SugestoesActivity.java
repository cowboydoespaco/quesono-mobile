package org.prgrmmr.quesono;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SugestoesActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestoes);

        TextView titulo = (TextView) findViewById(R.id.txtTituloSugestoes);

        if (getIntent().getBooleanExtra(MainActivity.SUGESTOES_DORMIR, true)) {
            titulo.setText(R.string.descricaoSugestoesDormir);
        } else {
            titulo.setText(R.string.descricaoSugestoesAcordar);
        }

        String[] sugestoes = getIntent().getStringArrayExtra(MainActivity.SUGESTOES);
        ((Button) findViewById(R.id.btnDespertador1)).setText(sugestoes[3]);
        ((Button) findViewById(R.id.btnDespertador2)).setText(sugestoes[2]);
        ((Button) findViewById(R.id.btnDespertador3)).setText(sugestoes[1]);
        ((Button) findViewById(R.id.btnDespertador4)).setText(sugestoes[0]);
    }

    public void setarDespertador(View v) {
        Intent novoAlarme = new Intent(AlarmClock.ACTION_SET_ALARM);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new SimpleDateFormat("HH:mm").parse(((Button) v).getText().toString()));
            novoAlarme.putExtra(AlarmClock.EXTRA_HOUR, cal.get(Calendar.HOUR_OF_DAY));
            novoAlarme.putExtra(AlarmClock.EXTRA_MINUTES, cal.get(Calendar.MINUTE));
            startActivity(novoAlarme);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Ops, não deu pra arrumar seu despertador. =/", 5).show();
        }
    }
}