package com.example.shiwanandchaurasiya.acadguildassignment81;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/*
  This is the main Activity means the launcher activity of our Application.And through this activity we are showing the UI
*/
/*
 * This activity is extending AppCompatActivity to make this activity compatible with backword
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /*
     * Creating object of ListView , Buttons, ArrayAdapter
     * */
    private Button asc_btn,desc_btn;
    private ListView listView;
    ArrayAdapter<String> adapter;

    //Array of names of months
    private String listItems[] = {
            "January","February","March","April","May","June","July","August","September","October","November","December"
    };
    List<String> list;//object of list to store name of months
    /*
   @Override means this is an overrriden method of the Activity LifeCycle and this call back is used to create the UI of the Acivity.
   @return This method is not returning anything as it's return type is void.
   @Param This method is taking the Bundle's instance as an arguement named as savedInstanceState used to fetch the details sent by another activity or method.
   This method is responsible for initializing all the views and setting the content view through the layout file.

   */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * setContentView is method to bind layout xml file with activity
         * */
        setContentView(R.layout.activity_main);
        /*
         * Initializing textView and btn using findViewById method
         * */
        asc_btn = (Button)findViewById(R.id.btn_asc);
        desc_btn = (Button)findViewById(R.id.btn_desc);
        listView = (ListView)findViewById(R.id.listView);

        /*
        * Setting onClickListener to buttons
        * */
        asc_btn.setOnClickListener(this);
        desc_btn.setOnClickListener(this);

        list = new ArrayList<>();//Initializing list
        //Adding array of months to the list
        //This is beacause to sort the list in ascending and descding order
        for(int i=0;i<listItems.length;i++){
            list.add(listItems[i]);
        }
        sortListItemsAndShow(0);//calling sortListItemsAndShow method to sort it initially in ascending order and set adapter to the listView
    }
/*
* overriding onClick method of View.OnClickListener Interface
* */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_asc :
                sortListItemsAndShow(0);//This is for Ascending button click
                break;
            case R.id.btn_desc :
                sortListItemsAndShow(1);//This is for Descending button click
                break;
        }
    }
/*
* Implementation of sortListItemsAndShow method
* */
    private void sortListItemsAndShow(int mode){
        if(mode==0){
            Collections.sort(list);//sorting in ascending order
        }else if(mode==1){
          Collections.sort(list,new MyComparator());//sorting in descding order using MyCom[arator class
        }
        adapter=new ArrayAdapter<>(this,R.layout.row_layout,list);//initializing ArrayAdapter I used here custom row_layout
        listView.setAdapter(adapter);//setting adapter to the listView
    }

    //This is MyComparator class to check which string should come first in descding order sorting
    private class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1); // magic line
        }
    }
}
