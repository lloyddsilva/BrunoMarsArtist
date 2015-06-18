package com.lloyddsilva.brunomarsartist.ui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.VideoView;

import com.lloyddsilva.brunomarsartist.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VideosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VideosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideosFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private VideoView videoView;
    private RadioGroup videosRadioGroup;
    private RadioButton selectedVideoRadioButton;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VideosFragment.
     */
    public static VideosFragment newInstance() {
        VideosFragment fragment = new VideosFragment();
        return fragment;
    }

    public VideosFragment() {
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
        View v = inflater.inflate(R.layout.fragment_videos, container, false);

        videosRadioGroup = (RadioGroup) v.findViewById(R.id.videosRadioGroup);
        Button startPlayerBtn = (Button) v.findViewById(R.id.startPlayerBtn);

        startPlayerBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int videoId = videosRadioGroup.getCheckedRadioButtonId();
                selectedVideoRadioButton = (RadioButton) view.findViewById(videoId);

                try
                {
                    playVideo(videoId);
                } catch (Exception e)
                {
                    e.printStackTrace();
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

    private void playVideo(int videoId)throws Exception
    {
        VideoView videoView = (VideoView)getActivity().findViewById(R.id.videoView);

        MediaController mc = new MediaController(getActivity());
        videoView.setMediaController(mc);

        switch(videoId) {
            case R.id.lazyDayRadioButton:
                videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.lazyday_video));
                break;

            case R.id.treasureRadioButton:
                videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.treasure_video));
                break;

            case R.id.uptownRadioButton:
                videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.uptown_video));
                break;

            default: break;
        }

        videoView.requestFocus();
        videoView.start();
    }

}
