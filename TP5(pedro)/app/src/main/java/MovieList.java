import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ducklings_corp.tp5.R;

import java.util.ArrayList;

public class MovieList extends BaseAdapter {
    private ArrayList<Movie> _movies;
    private Context _context;

    public MovieList (ArrayList<Movie> movies, Context context){
        _movies = movies;
        _context = context;
    }

    @Override
    public int getCount() {
        return _movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return _movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater layoutInflater;

        layoutInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.movie_list_item, parent, false);

        ((TextView)view.findViewById(R.id.movieTitle)).setText(getItem(position).title);
        ((TextView)view.findViewById(R.id.movieYear)).setText(getItem(position).year);

        return view;
    }

    // Crear asyntask para descargar posters
}
