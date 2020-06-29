package com.grepy.msx.cabaca.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.DetailBook
import com.grepy.msx.cabaca.ui.detail.adapter.ReviewsAdapter
import kotlinx.android.synthetic.main.fragment_review.*

class ReviewFragment : Fragment() {

    private val detailBookViewModel : DetailBookViewModel by lazy { ViewModelProvider(this).get(DetailBookViewModel::class.java) }
    private lateinit var reviewsAdapter: ReviewsAdapter
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
        prepareObserver(data)
    }

    private fun prepareObserver(book: Book?) {
        detailBookViewModel.getDetailBookById(data!!.bookId).observe(viewLifecycleOwner, Observer {
            it.forEach {
                prepareView(it)
            }
        })
    }

    private fun prepareView(detailBook: DetailBook) {
        reviewsAdapter = ReviewsAdapter()
        rv_reviewer.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_reviewer.adapter = reviewsAdapter
        reviewsAdapter.addReviewer(detailBook.reviews)
    }

}