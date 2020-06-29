package com.grepy.msx.cabaca.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import kotlinx.android.synthetic.main.fragment_review.*

class ReviewFragment : Fragment() {

    private var data : Book? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = arguments?.getParcelable("bookReviews")
        tv_fragment_review.text = data?.titleBook
    }

}