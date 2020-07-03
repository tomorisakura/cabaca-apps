package com.grepy.msx.cabaca.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.WriterProfile
import com.grepy.msx.cabaca.ui.detail.adapter.KaryaAdapter
import com.grepy.msx.cabaca.utils.Constant
import com.grepy.msx.cabaca.utils.ResultResponse
import com.grepy.msx.cabaca.utils.Status
import kotlinx.android.synthetic.main.fragment_writer.*

class WriterFragment : Fragment() {

    private val detailBookViewModel by viewModels<DetailBookViewModel>()
    private lateinit var karyaAdapter: KaryaAdapter
    private var data : Book? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_writer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = arguments?.getParcelable("bookWriter")
        observer(view)
    }

    private fun observer(view: View) {
        data?.let {
            detailBookViewModel.getWriterId(it.writerByWriterId.user_id).observe(viewLifecycleOwner, Observer { item ->
                when(item.status) {
                    Status.SUCCESS -> {
                        item.data?.result?.let {
                            prepareView(it, view)
                            shimmer_bio.visibility = View.GONE
                            shimmer_karya.visibility = View.GONE
                        }
                    }
                    Status.LOADING -> startShimmer()
                    Status.ERROR -> toast(item.msg.toString())
                }
            })
        }
    }

    private fun prepareView(writerProfile: WriterProfile, view: View) {
        val url = Constant.BASE_URL_IMAGE+writerProfile.photo+ Constant.API_KEY_IMAGE
        Glide.with(view.context).load(url).placeholder(R.drawable.ic_writer_icon).into(writer_image)
        writer_name.text = writerProfile.name
        email_writer.text = writerProfile.email
        desc_writer.text = writerProfile.desc

        karyaAdapter = KaryaAdapter()
        rv_karya.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        rv_karya.adapter = karyaAdapter
        karyaAdapter.addKarya(writerProfile.karya)
    }

    private fun toast(msg : String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    private fun startShimmer() {
        shimmer_bio.startShimmer()
        shimmer_karya.startShimmer()
    }
}