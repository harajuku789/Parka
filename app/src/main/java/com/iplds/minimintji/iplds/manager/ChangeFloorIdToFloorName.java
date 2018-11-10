package com.iplds.minimintji.iplds.manager;

public class ChangeFloorIdToFloorName {

    public static String changeName(int floorId) {
        String name = "";
        switch (floorId) {
            case 5001:
                name = "2A";
                break;
            case 5002:
                name = "2B";
                break;
            case 5003:
                name = "3A";
                break;
            case 5004:
                name = "3B";
                break;
            case 5005:
                name = "4A";
                break;
            case 5006:
                name = "4B";
                break;
            case 5007:
                name = "5A";
                break;
            case 5008:
                name = "5B";
                break;
            case 5009:
                name = "6A";
                break;
            case 5010:
                name = "6B";
                break;
            case 5011:
                name = "7A";
                break;
            case 5012:
                name = "7B";
                break;
            case 5013:
                name = "8A";
                break;
            case 5014:
                name = "8B";
                break;
            case 5015:
                name = "9A";
                break;
            case 5016:
                name = "9B";
                break;
            case 5017:
                name = "10A";
                break;
            case 5018:
                name = "10B";
                break;
        }

        return name;
    }
}
