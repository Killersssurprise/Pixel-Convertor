package com.killersssurprise.applicationarguments;

import com.killersssurprise.сonverter.SimpleImageConverter;

import java.util.Arrays;
import java.util.HashMap;

public class ApplicationArguments {

    public static final int FINISH_ERROR_CODE = -1;
    public static final String KEY_WIDTH = "-width";
    public static final String KEY_HEIGHT = "-height";
    public static final String KEY_RED_CORRECTION = "-r";
    public static final String KEY_GREEN_CORRECTION = "-g";
    public static final String KEY_BLUE_CORRECTION = "-b";

    public static final String KEY_INPUT_PATH = "-input";
    public static final String KEY_OUTPUT_PATH = "-output";

    public static final String KEY_MEDIAN = "-median";
    public static final String KEY_BILATERAL = "-bilateral";
    public static final String KEY_GAUSS = "-gauss";
    public static final String KEY_DILATE = "-dilate";
    public static final String KEY_ERODE = "-erode";

    public static final String KEY_COLORS = "-colors";

    private HashMap<String, String> keysAndValues;

    public ApplicationArguments(String[] args){

        if(Arrays.toString(args).toLowerCase().contains("-help")){
            SimpleImageConverter.printHelp();
            System.exit(1);
        }

        if(args.length%2!=0){
            System.out.println("Wrong params, can't run!");
            System.exit(1);
        }


        keysAndValues = new HashMap<>();

        for(int i=0;i<args.length;i+=2){
            keysAndValues.put(args[i].toLowerCase(),args[i+1].toLowerCase());
        }

    }

    public boolean containKey(String key){
        return(keysAndValues.containsKey(key));
    }

    public String getValue(String key){
        assert !containKey(key.toLowerCase());

        return keysAndValues.get(key.toLowerCase());
    }

    public int getIntValue(String key){
        assert !containKey(key.toLowerCase());

        return Integer.parseInt(keysAndValues.get(key.toLowerCase()));
    }

    @Override
    public String toString() {
        if(keysAndValues.isEmpty())
            return "applicationarguments is empty";
        else
            return "applicationarguments{" +
                "keysAndValues=" + keysAndValues +
                '}';
    }
}
