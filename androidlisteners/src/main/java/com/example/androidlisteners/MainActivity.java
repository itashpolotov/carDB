package com.example.androidlisteners;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView text;
    private static final String TAG="MyLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.btn);
        text=(TextView) findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*          try{
                   int i=4/0;
                   text.setText("Результат: "+i);

               }catch (Exception e){
                   Log.i(TAG,"Делить на ноль нельзя",e);
               }
*/

      Toast toast= Toast.makeText(MainActivity.this,"Вы нажали кнопку",Toast.LENGTH_SHORT); //уведомления
      // toast.setGravity(Gravity.CENTER,0,0);
                LinearLayout toastImage=(LinearLayout) toast.getView();
                ImageView imageView=new ImageView(MainActivity.this);
                imageView.setImageResource(R.drawable.baseline_notification_important_white_18dp);
                toastImage.addView(imageView);
       toast.show();

            }
        });
    }
}
