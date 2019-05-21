/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModulosJava;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author Guilherme
 */
public class Distance {
    public static double getDistance(String adress1, String adress2){
        
        String apiKEY = "sj6xn9FBZF3xMP9QrcA4yj41qTjPxxFy";
        String ad1 = adress1;
        String ad2 = adress2;
        
        String cidadeEstadoPais = ",Salvador, Bahia, Brazil";
        ad1 += cidadeEstadoPais;
        ad2 += cidadeEstadoPais;
        
        //Mudar espaços por +'s
        ad1 = ad1.replace(" ", "+");
        ad2= ad2.replace(" ", "+");
        
        //Cria o URL de acesso ao API
        String sUrl= "https://www.mapquestapi.com/directions/v2/route?key=" + apiKEY + "&from="+ ad1 + "&to=" + ad2 + "&outFormat=json&ambiguities=ignore&routeType=fastest&doReverseGeocode=false&enhancedNarrative=false&avoidTimedConditions=false";

        //Cria um objeto JSONObject e chama a função para pegar o JSON do URL
        JSONObject object = JSONParser.getJSONFromUrl(sUrl);

        try{
            //Acessa a parte da rota do JSON
            JSONObject route = object.getJSONObject("route");
            //Acessa a parte da distância em milhas do JSON
            double distanceInMiles = route.getDouble("distance");
            //Transforma a distância em KM's
            double distance = (distanceInMiles * 1.609);
            BigDecimal exactValue = new BigDecimal(distance)
                    .setScale(2, RoundingMode.UP);
            distance = exactValue.doubleValue();


            return distance;
        }catch(JSONException e){
            return 0.0;
        }
        //return 0.0;
    }
}
