package org.prgrmmr.quesono;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    public void confirmacaoDespertador(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmacaoDespertador).setTitle(R.string.tituloConfirmacaoDespertador);
        builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                setarDespertador(v);
            }
        });

        builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // nada?
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setarDespertador(View v) {
        Intent novoAlarme = new Intent(AlarmClock.ACTION_SET_ALARM);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new SimpleDateFormat("HH:mm").parse(((Button) v).getText().toString()));
            novoAlarme.putExtra(AlarmClock.EXTRA_HOUR, cal.get(Calendar.HOUR_OF_DAY));
            novoAlarme.putExtra(AlarmClock.EXTRA_MINUTES, cal.get(Calendar.MINUTE));
            novoAlarme.putExtra(AlarmClock.EXTRA_MESSAGE, "QueSono.com");
            startActivity(novoAlarme);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Ops, não deu pra arrumar seu despertador. =/", 5).show();
        }
    }
}