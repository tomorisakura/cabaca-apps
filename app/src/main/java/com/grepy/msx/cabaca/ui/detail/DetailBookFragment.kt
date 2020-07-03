package com.grepy.msx.cabaca.ui.detail




import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.DetailBook
import com.grepy.msx.cabaca.model.RelatedBook
import com.grepy.msx.cabaca.ui.detail.adapter.ChapterAdapter
import com.grepy.msx.cabaca.ui.detail.adapter.GenresAdapter
import com.grepy.msx.cabaca.ui.detail.adapter.HashtagsAdapter
import com.grepy.msx.cabaca.ui.detail.adapter.RelatedBookAdapter
import com.grepy.msx.cabaca.utils.Constant
import com.grepy.msx.cabaca.utils.RelatedBookHelper
import com.grepy.msx.cabaca.utils.ResultResponse
import com.grepy.msx.cabaca.utils.Status
import kotlinx.android.synthetic.main.fragment_detail_book.*

class DetailBookFragment : Fragment() {

    private val detailBookViewModel : DetailBookViewModel by lazy { ViewModelProvider(this).get(DetailBookViewModel::class.java) }
    private lateinit var relatedBookAdapter: RelatedBookAdapter
    private lateinit var genres: GenresAdapter
    private lateinit var hashtagsAdapter: HashtagsAdapter
    private lateinit var chapterAdapter: ChapterAdapter
    private var data : Book? = null

    private var expanded : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareObserver(view)
    }

    private fun prepareObserver(view: View) {
        data = arguments?.getParcelable("bookItem")
        detailBookViewModel.getDetailBookById(data!!.id).observe(viewLifecycleOwner, Observer {item ->
            when(item.status) {
                Status.LOADING -> startShimmer()
                Status.ERROR -> toast(view.context, item.msg.toString())
                Status.SUCCESS -> {
                    item.data?.result?.let {
                        prepareView(it)
                    }
                    disableShimmer()
                }
            }
        })
    }

    private fun prepareView(detailBook: DetailBook) {
        val url = Constant.BASE_URL_IMAGE+detailBook.writerId?.userByUser?.profilePic+ Constant.API_KEY_IMAGE
        Glide.with(this).load(url).placeholder(R.drawable.ic_writer_icon).into(img_writer)
        tv_writer_name.text = detailBook.writerId?.userByUser?.name ?: "-"
        tv_writer_username.text = detailBook.writerId?.userByUser?.username ?: "-"
        tv_book_rate.text = "Rating : " + data?.rateSum.toString()
        tv_book_status.text = "Status : " + detailBook.status
        tv_book_desc.text = detailBook.desc

        relatedBookAdapter = RelatedBookAdapter()
        rv_related.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_related.adapter = relatedBookAdapter
        relatedBookAdapter.addItem(detailBook.relatedBook)
        onItemRelatedClicked()

        genres = GenresAdapter()
        rv_genres_detail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_genres_detail.adapter = genres
        genres.addListGenres(detailBook.genres)

        hashtagsAdapter = HashtagsAdapter()
        rv_tags.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false)
        rv_tags.adapter = hashtagsAdapter
        hashtagsAdapter.addHashtags(detailBook.hashtags)

        card_bab.setOnClickListener {
            if (!expanded) {
                expanded_sub_chapter.visibility = ViewGroup.GONE
                expanded = true
            } else {
                expanded_sub_chapter.visibility = ViewGroup.VISIBLE
                expanded = false
            }
        }

        chapterAdapter = ChapterAdapter()
        rv_chapter_detail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_chapter_detail.adapter = chapterAdapter
        chapterAdapter.addChapter(detailBook.bookChapter)
    }

    private fun onItemRelatedClicked() {
        relatedBookAdapter.onItemRelatedClicked(object : RelatedBookHelper{
            override fun itemClickRelated(relatedBook: RelatedBook) {
                Toast.makeText(activity, relatedBook.title, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun toast(context: Context, msg : String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun disableShimmer() {
        shimmer_writer.visibility = View.GONE
        shimmer_genres.visibility = View.GONE
        shimmer_tags.visibility = View.GONE
        shimmer_desc.visibility = View.GONE
        shimmer_chapter.visibility = View.GONE
        shimmer_related.visibility = View.GONE
    }

    private fun startShimmer() {
        shimmer_writer.startShimmer()
        shimmer_genres.startShimmer()
        shimmer_tags.startShimmer()
        shimmer_desc.startShimmer()
        shimmer_chapter.startShimmer()
        shimmer_related.startShimmer()
    }

}