package app.ipsaous.com.espacemembretuto.myrequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyRequest {

    private Context context;
    private RequestQueue queue;

    public MyRequest(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    public void register(final String nom,final String prenom, final String email ,final String tel,final String age,final String idcni, final String password, final String password2, final RegisterCallback callback){
        String url = "http://10.0.4.152/hh/register.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Map<String, String> errors=new HashMap<>();
                try {
                    JSONObject json=new JSONObject(response);
                    Boolean error=json.getBoolean("error");

                    if (!error){
                        //inscription bien déroulée
                        callback.onSuccess("Vous etes bien inscrit");
                    }else
                    {
                        JSONObject messages=json.getJSONObject("message");

                        if (messages.has("nom")){
                            errors.put("nom",messages.getString("nom"));
                        }
                        if (messages.has("prenom")){
                            errors.put("prenom",messages.getString("prenom"));
                        }


                        if (messages.has("email")){
                            errors.put("email",messages.getString("email"));
                        }
                        if (messages.has("tel")){
                            errors.put("tel",messages.getString("tel"));
                        }
                        if (messages.has("age")){
                            errors.put("age",messages.getString("age"));
                        }
                        if (messages.has("idcni")){
                            errors.put("idcni",messages.getString("idcni"));
                        }
                        if (messages.has("password")){
                            errors.put("password",messages.getString("password"));
                        }
                        callback.inputErrors(errors);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){

                if (error instanceof NetworkError){
                    callback.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callback.onError("une error s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("nom", nom);
                map.put("prenom", prenom);
                map.put("email", email);
                map.put("tel", tel);
                map.put("age", age);
                map.put("idcni",idcni);
                map.put("password", password);
                map.put("password2", password2);

                return map;
            }
        };

        queue.add(request);

    }
    public interface RegisterCallback{
        void onSuccess(String message);
        void inputErrors(Map<String, String> errors);
        void onError(String message);

    }


    public void connection(final String email, final String password, final LoginCallback callback){

        String url = "http://10.0.4.152/hh/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject json= null;
                try {
                    json = new JSONObject(response);
                    Boolean error=json.getBoolean("error");
                    if(!error){
                        String nom=json.getString("nom");
                        String id=json.getString("id");
                        String email=json.getString("email");
                        String idcni=json.getString("idcni");

                        callback.onSuccess(id,nom,email,idcni);

                    }else
                    {
                        callback.onError(json.getString("message"));
                    }



                } catch (JSONException e) {
                    callback.onError("une error s'est produite exception");
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){

                if (error instanceof NetworkError){
                    callback.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callback.onError("une error s'est produite callabck");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("email", email);

                map.put("password", password);


                return map;
            }
        };

        queue.add(request);


    }
   public interface LoginCallback{
        void onSuccess(String id,String nom,String email,String idcni);
        void onError(String message);

    }

//    public void confirme(final String nom,final String descr,final String date,final String heure ,final String sexe ,final Confirm cc){
//
//        String url = "http://192.168.1.3/test.php";
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//
//                Map<String, String> errors=new HashMap<>();
//               try{
//                    JSONObject json=new JSONObject(response);
//                    Boolean error=json.getBoolean("error");
//
//                    if (!error){
//                        //inscription bien déroulée
//                        cc.onSuccess("Votre rendez vous est bien etes enregistrer  ");
//                    }else
//                        {
//                        JSONObject messages=json.getJSONObject("message");
//                        }
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//      , new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error){
//
//                if (error instanceof NetworkError){
//                    cc.onError("Impossible de se connecter");
//                }else if (error instanceof VolleyError){
//                    cc.onError("une error s'est produite");
//                }
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> map = new HashMap<>();
//                map.put("nom", nom);
//                map.put("descr", descr);
//                map.put("date", date);
//                map.put("heure", heure);
//                map.put("sexe",sexe);
//
//
//                return map;
//            }
//        };
//
//        queue.add(request);
//        }
//    public interface Confirm{
//        void onSuccess(String message);
//
//        void onError(String message);
//}
}
