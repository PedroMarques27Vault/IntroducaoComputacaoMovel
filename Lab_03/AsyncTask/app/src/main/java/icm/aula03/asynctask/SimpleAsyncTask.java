package icm.aula03.asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {
    private WeakReference<TextView> mTextView;
    public SimpleAsyncTask(TextView text){
        mTextView = new WeakReference<TextView>(text);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.i("Operation","BackTaskStarted");
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n*200;
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

}
