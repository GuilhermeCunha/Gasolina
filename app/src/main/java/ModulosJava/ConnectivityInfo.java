package ModulosJava;

import android.content.Context;
import android.net.ConnectivityManager;
import static android.content.Context.CONNECTIVITY_SERVICE;


public final class ConnectivityInfo {
    private static final String TAG = "ConnectivityInfo";
    /**
     *Verifica a disponibilidade da rede  de dados<br>
     *Tanto WIFI quanto 3G
     *@return  true ou false
     *@see android.net.ConnectivityManager
     */

    public static boolean isConnected(Context cont){
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) cont.getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
