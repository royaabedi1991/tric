package com.a3rick.a3rick.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.activities.BannerActivity;
import com.a3rick.a3rick.activities.MainActivity;
import com.a3rick.a3rick.adapters.BannerAdapter;
import com.a3rick.a3rick.core.RecyclerItemClickListener;
import com.a3rick.a3rick.fragments.dummy.DummyContent;
import com.a3rick.a3rick.fragments.dummy.DummyContent.DummyItem;
import com.a3rick.a3rick.models.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class VitrinFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VitrinFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static VitrinFragment newInstance(int columnCount) {
        VitrinFragment fragment = new VitrinFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));


            RecyclerView bannerRecycler = (RecyclerView)view.findViewById(R.id.recyceler_banner);
            final BannerAdapter bannerAdapter = new BannerAdapter(this, getBanners());

            bannerRecycler.setAdapter(bannerAdapter);
            LinearLayoutManager bannerManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
            bannerRecycler.setLayoutManager(bannerManager);

            bannerRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    startActivity(new Intent(MainActivity.this, BannerActivity.class));
                }
            }));
        }
        return view;
    }
    private List<Banner> getBanners() {

        List<Banner> banners = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            Banner current = new Banner();
            current.setRecource(R.drawable.banner);
            current.setTitle("طرز تهیه سبزی پلو");
            banners.add(current);


        }
        return banners;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
