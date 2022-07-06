package sc.fx.appaplication.model.interfaces;

import java.util.List;

import sc.fx.appaplication.model.enums.Theme;

public interface ThemeRepository {
    Theme getSavedTheme();
    void saveTheme(Theme theme);
    List<Theme> getAll();
}
