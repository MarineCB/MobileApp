package tp1.androidproject.lifequality;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyController {
    private static VolleyController instance;
    private RequestQueue reqQueue;
    private Context ctx;

    private VolleyController(Context context) {
        ctx = context;
        reqQueue = getRequestQueue();
    }

    public static synchronized VolleyController getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyController(context);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (reqQueue == null) {
            reqQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return reqQueue;
    }

    public  void addToRequestQueue(Request req) {
        getRequestQueue().add(req);
    }
}
