package com.grepy.msx.cabaca.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.DetailBook
import com.grepy.msx.cabaca.ui.detail.adapter.ReviewsAdapter
import com.grepy.msx.cabaca.utils.ResultResponse
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
        prepareObserver()
    }

    private fun prepareObserver() {
        detailBookViewModel.getDetailBookById(data!!.id).observe(viewLifecycleOwner, Observer { item ->
            when(item.status) {
                ResultResponse.Status.SUCCESS -> {
                    item.data?.result?.let {
                        if (it.reviews.isNullOrEmpty()) {
                            toast("Belum ada review pada buku ${it.title}")
                        } else {
                            prepareView(it)
                        }
                    }
                }
                ResultResponse.Status.LOADING -> toast("Loading")
                ResultResponse.Status.ERROR -> toast("RTO")
            }
        })
    }

    private fun prepareView(detailBook: DetailBook) {
        reviewsAdapter = ReviewsAdapter()
        rv_reviewer.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_reviewer.adapter = reviewsAdapter
        reviewsAdapter.addReviewer(detailBook.reviews)
    }

    private fun toast(message : String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}