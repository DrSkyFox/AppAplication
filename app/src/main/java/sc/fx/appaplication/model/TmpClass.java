package sc.fx.appaplication.model;

import android.content.Context;

public class TmpClass {

    private static TmpClass INSTANCE;

    private final Context context;

    private TmpClass(Context context) {
        this.context = context;
    }

    public static TmpClass getINSTANCE(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new TmpClass(context);
        }
        return INSTANCE;
    }
}
