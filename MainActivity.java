package com.example.armin.taschenrechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.app.Activity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    String msg = "Android: ";
    String calculation = getString(R.string.calculation);

    /**
     * Called when the activity is first created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                return true;
            case R.id.action_settings:
                return true;
            default:
                return false;
        }
    }

    public void sendMessage(View view) {
        EditText calc = ((EditText)findViewById(R.id.calculation));
        switch (view.getId()) {
            case R.id.one:
                calc.append("1");
                break;
            case R.id.two:
                calc.append("2");
                break;
        }
    }

    public void calculate() {
        String calc = ((EditText)findViewById(R.id.calculation)).getText().toString();
        List<Integer> list = new LinkedList<>();
        int counter=0;
        for(int i=0;i<calc.length();i++) {
            if(calc.charAt(i)!=(int)calc.charAt(i)) {
                switch (calc.charAt(i)) {
                    case '+':
                        list.add(1);
                        break;
                    case '-':
                        list.add(1);
                        break;
                    case '*':
                        list.add(2);
                        break;
                    case '/':
                        list.add(2);
                        break;
                }
                counter++;
            }
        }
        for(int j=0;j<counter;j++) {
            int max=0;
            int temp=0;
            for(int i=0;i<list.size();i++) {
                if (max<list.get(i)) {
                    max=i;
                }
            }
            list.remove(max);
            for(int i=0;i< calc.length();i++) {
                if(calc.charAt(i)!=(int)calc.charAt(i)) {
                    temp++;
                }
                if(temp>=max) {
                    max=i;
                }
            }
            StringBuilder op1 = new StringBuilder();
            StringBuilder op2 = new StringBuilder();
            int before=0;
            int after=calc.length()-1;
            for(int i=1;i<=calc.length()/2;i++) {
                if(calc.charAt(max-i)==(int)calc.charAt(max-i)) {
                    op1.insert(0, calc.charAt(max-i));
                } else {
                    before=max-i;
                    break;
                }
            }
            for(int i=1;i<=calc.length()/2;i++) {
                if(calc.charAt(max+i)==(int)calc.charAt(max+i)) {
                    op2.append(calc.charAt(max+i));
                } else {
                    after=max+i;
                    break;
                }
            }
            String one=calc.substring(0, before);
            String two=calc.substring(after, calc.length()-1);
            switch (calc.charAt(max)) {
                case '+':
                    calc=one+(Integer.parseInt(op1.toString())+Integer.parseInt(op2.toString()))+two;
                    break;
                case '-':
                    calc=one+(Integer.parseInt(op1.toString())-Integer.parseInt(op2.toString()))+two;
                    break;
                case '*':
                    calc=one+(Integer.parseInt(op1.toString())*Integer.parseInt(op2.toString()))+two;
                    break;
                case '/':
                    calc=one+(Integer.parseInt(op1.toString())/Integer.parseInt(op2.toString()))+two;
                    break;
            }
        }

    }

    /**
     * Called when the activity is about to become visible
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Called when the activity has become visible
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Called when another activity is taking focus
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Called when the activity is no longer visible
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Called just before the activity is destroyed
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
