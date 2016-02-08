package roee.zolantz.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {

    ListView listViewThemes;
    ArrayList<String> lstThemes;
    int savedThemeID;
    String selectedThemeID;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.lstThemes = new ArrayList<>();
        this.loadThemesList();

        this.setTitle("LALALALLALA");

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        prefs.edit().putInt("def_theme", R.style.AppTheme1);
        prefs.edit().apply();
        prefs.edit().commit();

        boolean bDoesExists = prefs.contains("def_theme");
        savedThemeID = prefs.getInt("def_theme", 0);
        int resID;

        switch (savedThemeID) {
            case 0:
                resID = R.style.AppTheme;
                break;
            case 1:
                resID = R.style.AppTheme1;
                break;
            default:
                resID = R.style.AppTheme;
                break;
        }

        setTheme(resID);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        //Toast.makeText(this, savedThemeID, Toast.LENGTH_LONG).show();

        listViewThemes = (ListView) findViewById(R.id.lstStyles);
        ThemesAdapter themesAdapter = new ThemesAdapter(this, lstThemes);
        listViewThemes.setAdapter(themesAdapter);

        final Context zibik = this;

        listViewThemes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(zibik, position + "", Toast.LENGTH_SHORT).show();
                selectedThemeID = view.getTag(R.id.textView).toString();
            }
        });
    }

    public void loadThemesList() {

        for (int i = 0; i < 2; i++) {
            String curr = "R.style.AppTheme" + (i > 0 ? i:"").toString() + "";

            lstThemes.add(i, curr);
        }
    }

    public void doSomeShit(View view) {
        Snackbar.make(view, "Created..", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();

        try {
            int resID = getResources().getIdentifier("R.style.AppTheme1", "style", getPackageName());
            prefs.edit().putInt("def_theme", R.style.AppTheme1);
            prefs.edit().apply();
            prefs.edit().commit();
        } catch (Exception ex) {
            Log.e(null, ex.getMessage());
        }

        Snackbar.make(view,
                      "Changed the default theme to " +
                              selectedThemeID,
                      Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();

        recreate();
    }
}
