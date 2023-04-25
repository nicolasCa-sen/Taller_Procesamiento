package Control;

import Logic.Modules;
import Logic.ShiftSystem;

public class LogicControl {

    private ShiftSystem shiftSystem;
    private Modules modules;

    public LogicControl() {
        this.shiftSystem = new ShiftSystem();
    }

    public Modules getModules() {
        return modules;
    }
}
