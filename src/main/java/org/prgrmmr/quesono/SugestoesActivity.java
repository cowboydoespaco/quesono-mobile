package org.prgrmmr.quesono;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

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
        ((TextView) findViewById(R.id.txtSugestoes1)).setText(sugestoes[3]);
        ((TextView) findViewById(R.id.txtSugestoes2)).setText(sugestoes[2]);
        ((TextView) findViewById(R.id.txtSugestoes3)).setText(sugestoes[1]);
        ((TextView) findViewById(R.id.txtSugestoes4)).setText(sugestoes[0]);
    }
}