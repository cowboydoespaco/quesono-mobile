package org.prgrmmr.quesono;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Calculadora {

    public static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

    public static String[] calcularHoraDormir(Integer horas, Integer minutos) {
        String[] sugestoes = new String[4];
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, horas);
        cal.set(Calendar.MINUTE, minutos);

        Log.d("time", formatter.format(cal.getTime()));

        cal.add(Calendar.MINUTE, -15);
        int j=0;
        for (int i=1; i <= 6; i++) {
            cal.add(Calendar.MINUTE, -90);
            if (i > 2) {
                sugestoes[j] = formatter.format(cal.getTime());
                j++;
            }
        }

        return sugestoes;
    }

    public static String[] calcularHoraAcordar() {
        String[] sugestoes = new String[4];
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.MINUTE, 15);
        int j=0;
        for (int i=1; i <= 6; i++) {
            cal.add(Calendar.MINUTE, 90);
            if (i > 2) {
                sugestoes[j] = formatter.format(cal.getTime());
                j++;
            }
        }

        return sugestoes;
    }


}
