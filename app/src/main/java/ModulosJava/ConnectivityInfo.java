package ModulosJava;

import android.content.Context;
import android.net.ConnectivityManager;

public final class ConnectivityInfo {
    /**
     *Verifica a disponibilidade da rede  de dados<br>
     *Tanto WIFI quanto 3G
     *@return  true ou false
     *@see android.net.ConnectivityManager
     */

    public static boolean isConnected(Context cont){
        boolean conectado = false;
        ConnectivityManager conmag;
        conmag = (ConnectivityManager)cont.getSystemService(Context.CONNECTIVITY_SERVICE);
        conmag.getActiveNetworkInfo();
        //Verifica o WIFI
        if(conmag.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()){
            conectado = true;
        }
        //Verifica o 3G
        else if(conmag.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()){
            conectado = true;
        }
        else{
            conectado = false;
        }
        return conectado;
    }
}
