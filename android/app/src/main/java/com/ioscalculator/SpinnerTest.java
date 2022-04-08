package com.ioscalculator;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.RCTModernEventEmitter;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Map;


public class SpinnerTest extends SimpleViewManager<Spinner> {

    public static final String REACT_CLASS = "SpinnerModule";
    private Spinner codesSpinner;


    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put(
                        "topChange",
                        MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onChange"))
                )
                .build();
    }


    @ReactProp(name = "dropDownWidth")
    public void setDropDownWidth( Spinner codesSpinner, int dropDownWidth){
        codesSpinner.setDropDownWidth(dropDownWidth);
    }



    @NonNull
    @Override
    protected Spinner createViewInstance(ThemedReactContext reactContext) {

        codesSpinner = new Spinner(reactContext, 1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(reactContext,
                R.array.code_zones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayList<String> testList = new ArrayList<>();
        codesSpinner.setAdapter(adapter);
        codesSpinner.performClick();
        codesSpinner.setGravity(1);
        codesSpinner.onSaveInstanceState();


       codesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
            public void onItemSelected(@NonNull AdapterView<?> adapterView, View view, int i, long l) {
               String text = adapterView.getItemAtPosition(i).toString();
                WritableMap event = Arguments.createMap();
               event.putString("data", text);
               try{
                   ((ReactContext) codesSpinner.getContext())
                           .getJSModule(RCTEventEmitter.class)
                           .receiveEvent(
                                   codesSpinner.getId(),
                                   "topChange",
                                   event);
               } catch (Exception e){

               }

            }
            @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }


       });
           return codesSpinner;
    }



}
