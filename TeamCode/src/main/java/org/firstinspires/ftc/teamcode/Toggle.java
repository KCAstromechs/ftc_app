package org.firstinspires.ftc.teamcode;

public class Toggle {

    private boolean toggleVal = false;
    private boolean previousVal = false;

    boolean update(boolean button) {
        if(button) {
            if((!previousVal) && (!toggleVal)) {
                toggleVal = true;
            }
            else if((!previousVal)) {
                toggleVal = false;
            }
            previousVal = true;
        } else {
            previousVal = false;
        }
        return toggleVal;
    }
}
