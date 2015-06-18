package com.lloyddsilva.brunomarsartist.ui;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lloyddsilva.brunomarsartist.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SongsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SongsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private MediaPlayer mediaPlayer;
    private int playbackPosition=0;
    private RadioGroup songsRadioGroup;
    private RadioButton selectedSongRadioButton;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SongsFragment.
     */
    public static SongsFragment newInstance() {
        SongsFragment fragment = new SongsFragment();
        return fragment;
    }

    public SongsFragment() {
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
        View v = inflater.inflate(R.layout.fragment_songs, container, false);

        songsRadioGroup = (RadioGroup) v.findViewById(R.id.songsRadioGroup);
        Button startPlayerBtn = (Button) v.findViewById(R.id.startPlayerBtn);
        Button pausePlayerBtn = (Button) v.findViewById(R.id.pausePlayerBtn);
        Button restartPlayerBtn = (Button) v.findViewById(R.id.restartPlayerBtn);

        startPlayerBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int songId = songsRadioGroup.getCheckedRadioButtonId();
                selectedSongRadioButton = (RadioButton) view.findViewById(songId);

                try
                {
                    playLocalAudio(songId);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        pausePlayerBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mediaPlayer!=null)
                {
                    playbackPosition = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                }
            }
        });

        restartPlayerBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mediaPlayer!=null && !mediaPlayer.isPlaying())
                {
                    mediaPlayer.seekTo(playbackPosition);
                    mediaPlayer.start();
                }
            }
        });


        return v;
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

    private void killMediaPlayer()
    {
        if(mediaPlayer!=null)
        {
            try
            {
                mediaPlayer.release();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void playLocalAudio(int songId)throws Exception
    {
        switch(songId) {
            case R.id.lazyDayRadioButton: mediaPlayer = MediaPlayer.create(getActivity(), R.raw.lazyday_song);
                break;

            case R.id.treasureRadioButton: mediaPlayer = MediaPlayer.create(getActivity(), R.raw.treasure_song);
                break;

            case R.id.uptownRadioButton: mediaPlayer = MediaPlayer.create(getActivity(), R.raw.uptown_song);
                break;

            default: break;
        }

        mediaPlayer.start();
    }

}
