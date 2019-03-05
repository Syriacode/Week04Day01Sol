package example.org.test.week04day01sol;

import android.os.Looper;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.os.Bundle;


public class LooperThread extends Thread {
    // our handlers
    Handler workerThreadHandler;
    Handler mainThreadHandler;
    MainActivity mainActivity;


    public LooperThread(Handler handler) {
        super();
        mainThreadHandler = handler;
        workerThreadHandler = new android.os.Handler(Looper.myLooper()){

            @Override
            public void handleMessage(Message msg) {
                Log.d("CHILD_THREAD", "Receive message from main thread." );
                Message message = new Message();
                message.what = msg.what;
                Bundle bundle = new Bundle();
                for (int i = 0; i < mainActivity.passUserInput().length(); i++){
                    for (int j = i+1; j<mainActivity.passUserInput().length(); j++){
                        if (mainActivity.passUserInput().charAt(i) == mainActivity.passUserInput().charAt(j)){
                            bundle.putString("Key" , "There is a duplicate");
                        } else {
                            bundle.putString("Key" , "There is a NO duplicate");
                        }
                }
                message.setData(bundle);
                    mainThreadHandler.sendMessage(message);
            }

                bundle.putString("key", "From Child Handler");
                message.setData(bundle);
                mainThreadHandler.sendMessage(message);
            }
        };

        }

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        Looper.loop();
    }
}

