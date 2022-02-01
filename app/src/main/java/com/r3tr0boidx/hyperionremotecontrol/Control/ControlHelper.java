package com.r3tr0boidx.hyperionremotecontrol.Control;

public class ControlHelper {

    public static final String COMMAND_KEY = "command";

    static boolean checkRange(int _value, int _min, int _max){
        return (_value <= _max && _value >= _min);
    }

    static boolean checkRange(double _value, double _min, double _max){
        return (_value <= _max && _value >= _min );
    }

    static String getRangeErrorMessage(double _value, double _min, double _max, String _name){
        return (_name + "is to high or low. Valid inputs are between " + _min + " and " + _max + ". Give is " + _value);
    }
}
