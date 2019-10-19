package com.killersssurprise.applicationarguments;

import com.killersssurprise.сonverter.SimpleImageConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    public static final String KEY_HELP = "-help";

    public static final String[] ARGUMENTS = {KEY_WIDTH,KEY_HEIGHT,KEY_RED_CORRECTION,KEY_GREEN_CORRECTION, KEY_BLUE_CORRECTION,
    KEY_INPUT_PATH, KEY_OUTPUT_PATH,KEY_MEDIAN,KEY_BILATERAL,KEY_GAUSS,KEY_DILATE,KEY_ERODE,KEY_COLORS};

    private static ArrayList<String> arguments_order;

    private HashMap<String, String> keysAndValues;

    public ApplicationArguments(String[] args){

        arguments_order = new ArrayList<>();

        if(Arrays.toString(args).toLowerCase().contains(KEY_HELP)){
            SimpleImageConverter.printHelp();
            System.exit(1);
        }

        if(args.length%2!=0){
            System.out.println("Wrong params, can't run!");
            System.exit(1);
        }


        for(int i=0;i<args.length;i+=2){

            if(!Arrays.asList(ARGUMENTS).contains(args[i].toLowerCase())) {
                System.out.println("Wrong arg "+args[i]);
                System.exit(1);
            }

        }

        keysAndValues = new HashMap<>();

        for(int i=0;i<args.length;i+=2){

            if(keysAndValues.containsKey(args[i].toLowerCase())){
//                System.out.println("Already contain this key! Skipping...");
                int counter=1;
                while(true){
                    if(keysAndValues.containsKey(args[i].toLowerCase()+""+counter)){
                        counter++;
                    }else{
                        keysAndValues.put(args[i].toLowerCase()+""+counter,args[i+1].toLowerCase());
                        arguments_order.add(args[i].toLowerCase()+""+counter);
                        break;
                    }
                }

            }else{
                keysAndValues.put(args[i].toLowerCase(),args[i+1].toLowerCase());
                arguments_order.add(args[i].toLowerCase());
            }


        }



        System.out.println(toString());

    }

    public HashMap<String, String> getKeysAndValues() {
        return keysAndValues;
    }

    public List<String> getOrderedKeys(){
        return arguments_order;
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
