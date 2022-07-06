package sc.fx.appaplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.List;

import sc.fx.appaplication.R;
import sc.fx.appaplication.model.ThemeRepositoryImpl;
import sc.fx.appaplication.model.enums.Theme;
import sc.fx.appaplication.model.interfaces.ThemeRepository;

public class SelectTheme extends AppCompatActivity {
    public static final String EXTRA_THEME = "EXTRA_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_theme);

        findViewById(R.id.how_to).setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.ru"));
            startActivity(Intent.createChooser(browserIntent, null));
        });

        ThemeRepository themeRepository = ThemeRepositoryImpl.getInstance(this);

        List<Theme> themes = themeRepository.getAll();
        LinearLayout container = findViewById(R.id.container);
        Intent intent = getIntent();
        Theme selectedThem = (Theme) intent.getSerializableExtra(EXTRA_THEME);

        for (Theme theme : themes) {
            View itemView = getLayoutInflater().inflate(R.layout.item_theme, container, false);
            TextView title = itemView.findViewById(R.id.title);
            title.setText(theme.getTitle());
            CardView cardView = itemView.findViewById(R.id.theme_card);
            cardView.setOnClickListener(view -> {
                Intent data = new Intent();
                data.putExtra(EXTRA_THEME, theme);
                setResult(Activity.RESULT_OK, data);
                finish();
            });

            ImageView checked = itemView.findViewById(R.id.checked);

            if (theme.equals(selectedThem)) {
                checked.setVisibility(View.VISIBLE);
            } else {
                checked.setVisibility(View.GONE);
            }
            container.addView(itemView);
        }
    }
}