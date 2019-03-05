package example.org.test.week04day01sol;

import android.os.AsyncTask;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class AsyncThreating extends AsyncTask<String, String, String>  {
        MainActivity mainActivity;

    @Override
    protected void onPreExecute() {
        Log.d("TAG", "onPreExecute: ABOUT TO RUN");
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String reverseUserInput = "";
        for (int i = mainActivity.passUserInput().length()-1; i !=-1; i--){
            reverseUserInput += mainActivity.passUserInput().charAt(i);
        }
        return reverseUserInput;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        EventBus.getDefault().post(new AsyncTaskEvent(values[0]));
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        EventBus.getDefault().post(new AsyncTaskEvent(s));
    }
}
