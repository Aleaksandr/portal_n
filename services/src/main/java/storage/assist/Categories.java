package storage.assist;

public enum Categories {
    SHOES(1),
    SHIRTS(2),
    CAPS(3),
    PANTS(4);

    private final int code;

    Categories(){
        this.code = 0;
    }

    Categories(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Categories parse(int code) {
        switch (code) {
            case 1:
                return SHOES;
            case 2:
                return SHIRTS;
            case 3:
                return CAPS;
            case 4:
                return PANTS;
        }
        return null;
    }
    
    public static String getText(Categories category) {
        switch (category) {
            case SHOES:
                return "Ботинки";
            case SHIRTS:
                return "Футболки";
            case CAPS:
                return "Головные уборы";
            case PANTS:
                return "Штаны";
        }
        return "";
    }
}
