package org.prgrmmr.quesono;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SugestoesActivity extends Activity {

    private static final String DORMINDO_X_CICLOS = "DORMINDO X CICLOS";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //In order to make the gradient look good on all devices..
        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
        setContentView(R.layout.activity_sugestoes);

        TextView titulo = (TextView) findViewById(R.id.txtTituloSugestoes);

        if (getIntent().getBooleanExtra(MainActivity.SUGESTOES_DORMIR, true)) {
            titulo.setText(R.string.descricaoSugestoesDormir);
        } else {
            titulo.setText(R.string.descricaoSugestoesAcordar);
        }

        String[] from = { "horario", "numCiclos" };
        int[] to = { R.id.txtHorario, R.id.txtNumCiclos };

        String[] sugestoes = getIntent().getStringArrayExtra(MainActivity.SUGESTOES);
        ArrayList<Map<String, String>> list = construirMapa(sugestoes);
        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.list_item, from, to);

        ListView listView = (ListView) findViewById(R.id.listViewSugestoes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                confirmacaoDespertador(view);
            }
        });
    }

    private ArrayList<Map<String, String>> construirMapa(String[] sugestoes) {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(createMap(sugestoes[3], DORMINDO_X_CICLOS.replace('X', '6')));
        list.add(createMap(sugestoes[2], DORMINDO_X_CICLOS.replace('X', '5')));
        list.add(createMap(sugestoes[1], DORMINDO_X_CICLOS.replace('X', '4')));
        list.add(createMap(sugestoes[0], DORMINDO_X_CICLOS.replace('X', '3')));
        return list;
    }

    private HashMap<String, String> createMap(String horario, String numCiclos) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("horario", horario);
        item.put("numCiclos", numCiclos);
        return item;
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
            cal.setTime(new SimpleDateFormat("HH:mm").parse(((TextView) v.findViewById(R.id.txtHorario)).getText().toString()));
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