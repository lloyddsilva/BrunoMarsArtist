package com.lloyddsilva.brunomarsartist.ui;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lloyddsilva.brunomarsartist.R;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WallpaperFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WallpaperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WallpaperFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Button setWallpaperBtn;
    private ImageView selectedWallpaperImageView;
    private Gallery gallery;

    private Integer[] imageIds = { R.drawable.brunomars1, R.drawable.brunomars2, R.drawable.brunomars3};

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WallpaperFragment.
     */
    public static WallpaperFragment newInstance() {
        WallpaperFragment fragment = new WallpaperFragment();
        return fragment;
    }

    public WallpaperFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wallpaper, container, false);

        gallery = (Gallery) v.findViewById(R.id.gallery);
        selectedWallpaperImageView = (ImageView) v.findViewById(R.id.selectedWallpaperImageView);

        gallery.setSpacing(1);
        gallery.setAdapter(new ImageAdapter(getActivity()));

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedWallpaperImageView.setImageResource(imageIds[position]);
            }
        });

        setWallpaperBtn = (Button) v.findViewById(R.id.setWallpaperBtn);

        setWallpaperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWallpaper(view);
            }
        });



        return v;
    }

    private void setWallpaper(View view) {

        selectedWallpaperImageView.buildDrawingCache();
        Bitmap mBitmap = selectedWallpaperImageView.getDrawingCache();

        WallpaperManager wm = WallpaperManager
                .getInstance(getActivity().getApplicationContext());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels << 1;
        width = width/2;

        mBitmap = Bitmap.createScaledBitmap(mBitmap,width, height, true);

        try {
            wm.setBitmap(mBitmap);
            wm.suggestDesiredDimensions(width, height);
            Toast.makeText(getActivity(), "Wallpaper Set", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getActivity(), "Error Setting Wallpaper", Toast.LENGTH_SHORT).show();
        }
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
