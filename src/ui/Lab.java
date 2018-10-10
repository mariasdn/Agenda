package ui;

public class Lab extends ScheduleItem {
    private String code;
    private String num;

    public Lab (String code, String num, int startTime, int length, boolean[] weekDays) throws IllegalArgumentException {
        if (inputsValid (code, num, startTime, length, weekDays)) {
            this.itemType = "lab";
            this.code = code;
            this.num = num;
            this.length = length;
            this.startTime = startTime;
            endTime = startTime + length;
            this.weekDays = new boolean[5];
            this.weekDays[0] = weekDays[0];
            this.weekDays[1] = weekDays[1];
            this.weekDays[2] = weekDays[2];
            this.weekDays[3] = weekDays[3];
            this.weekDays[4] = weekDays[4];
        } else {
            throw new IllegalArgumentException("Invalid arguments");
        }

    }

    public boolean inputsValid (String code, String num, int startTime, int length, boolean[] weekDays) {
        return (code != null && num != null && startTime > 0 && length > 0 && weekDays.length == 5);
    }

    @Override
    public String getName() {
        return this.code + " " + this.num;
    }

    @Override
    public String toSave() {
        return itemType + "," + code + "," + num + "," + startTime + "," + length + ","
                + weekDays[0] + "," + weekDays[1] + "," + weekDays[2] + "," + weekDays[3] + "," + weekDays[4];
    }

    @Override
    public boolean checkItemName(String name) {
        return this.code.equals(name);
    }
}
