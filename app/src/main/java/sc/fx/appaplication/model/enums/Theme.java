package sc.fx.appaplication.model.enums;



import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import sc.fx.appaplication.R;

public enum Theme {
    ONE(R.style.Theme_MyCalculator_LightMode, R.string.theme_light, "LightMode"),
    TWO(R.style.Theme_MyCalculator_DarkMode, R.string.theme_darkMode, "DarkMode");

    @StyleRes
    private final int themeRes;
    @StringRes
    private final int title;

    private final String key;


    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    public int getTitle() {
        return title;
    }
    public int getThemeRes() {
        return themeRes;
    }
}
