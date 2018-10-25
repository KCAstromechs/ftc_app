package org.firstinspires.ftc.teamcode;

public class Toggle {
    private boolean button = false;
    private boolean toggleVal = false;
    private boolean previousVal = false;

    void update(boolean _button) {
        button = _button;
        if(button) {
            if((!previousVal) && (!toggleVal)) {
                toggleVal = true;
            }
            else if((!previousVal) && (toggleVal)) {
                toggleVal = false;
            }
            previousVal = true;
        }
        else {
            previousVal = false;
        }

    }
    boolean getVal() {
        return toggleVal;
    }
}
