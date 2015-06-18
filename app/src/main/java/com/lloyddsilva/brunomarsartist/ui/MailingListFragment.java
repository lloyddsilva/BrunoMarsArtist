package com.lloyddsilva.brunomarsartist.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.lloyddsilva.brunomarsartist.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MailingListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MailingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MailingListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Button joinMailingListBtn;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MailingListFragment.
     */
    public static MailingListFragment newInstance() {
        MailingListFragment fragment = new MailingListFragment();
        return fragment;
    }

    public MailingListFragment() {
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
        View v = inflater.inflate(R.layout.fragment_mailing_list, container, false);

        joinMailingListBtn = (Button) v.findViewById(R.id.joinMailingListBtn);

        joinMailingListBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","bruno@mars", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Add me to your mailing list");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi - Please add me to your mailing list. Your loving fan. XOXOXO");

                //Send mail
                startActivity(Intent.createChooser(emailIntent, "Send email"));
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

}
