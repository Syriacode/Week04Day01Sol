package example.org.test.week04day01sol;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etUserInput;
    TextView tvResultsJavaThread;
    TextView tvResultsAsyncResults;
    TextView tvLooperResults;
    Button btnStartJavaThread;
    Button btnStartAsyncTask;
    Button btnStartLooperTask;
    public static int threadId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUserInput = findViewById(R.id.etUserInput);
        tvResultsJavaThread = findViewById(R.id.tvResultsJavaThread);
        tvResultsAsyncResults = findViewById(R.id.tvResultsAsyncResults);
        tvLooperResults = findViewById(R.id.btnStartLooperTask);
        btnStartJavaThread = findViewById(R.id.btnStartJavaThread);
        btnStartJavaThread.setOnClickListener(this);
        btnStartAsyncTask = findViewById(R.id.btnStartAsyncTask);
        btnStartAsyncTask.setOnClickListener(this);
        btnStartLooperTask = findViewById(R.id.btnStartLooperTask);
        btnStartLooperTask.setOnClickListener(this);

        // Declare Async task, instantiate the task, and start the task

        AsyncThreating asyncThreating;
        asyncThreating = new AsyncThreating();
        asyncThreating.execute();

        //startThread();


        //Looper
        LooperThread looperThread;

        looperThread = new LooperThread(new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //get info back out of message received
                Bundle bundle = msg.getData();
                tvLooperResults.setText(bundle.getString("key"));
            }
        });
        looperThread.start();
        looperThread.workerThreadHandler.sendMessage(new Message());

    }
    @Override
        protected void onStart() {
            super.onStart();
            EventBus.getDefault().register(this);
    }

    @Override
        protected void onStop() {
            super.onStop();
            EventBus.getDefault().unregister(this);
    }

    public String passUserInput(){
        return  etUserInput.getText().toString();
    }

    //Java Runnable for Thread
    public Runnable runnableForThread(final int id){
        return new Runnable() {
            @Override
            public void run() {
                String userInput = etUserInput.getText().toString();
             int length = userInput.length();
             tvResultsJavaThread.setText("Length on User Input is: " + length);
            }
        };
    }
    // standard Java Thread

    private void startThread(){
        threadId++;
        Thread javaThread = new Thread(runnableForThread(threadId));
        javaThread.start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartJavaThread:
                startThread();
                break;

            case R.id.btnStartAsyncTask:
                return; //reverseUserInput;

        }

    }
}
