package com.grepy.msx.cabaca.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.DetailBook
import com.grepy.msx.cabaca.ui.detail.adapter.ChapterAdapter
import com.grepy.msx.cabaca.ui.detail.adapter.GenresAdapter
import com.grepy.msx.cabaca.ui.detail.adapter.HashtagsAdapter
import com.grepy.msx.cabaca.ui.detail.adapter.RelatedBookAdapter
import com.grepy.msx.cabaca.utils.Constant
import kotlinx.android.synthetic.main.fragment_detail_book.*

class DetailBookFragment : Fragment() {

    private val detailBookViewModel : DetailBookViewModel by lazy { ViewModelProvider(this).get(DetailBookViewModel::class.java) }
    private lateinit var relatedBookAdapter: RelatedBookAdapter
    private lateinit var genres: GenresAdapter
    private lateinit var hashtagsAdapter: HashtagsAdapter
    private lateinit var chapterAdapter: ChapterAdapter
    private var data : Book? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = arguments?.getParcelable("bookItem")
        prepareObserver(data!!.bookId)
    }

    private fun prepareObserver(id : Int) {
        detailBookViewModel.getDetailBookById(id).observe(viewLifecycleOwner, Observer {
            for (i in 0 until it.size) {
                prepareView(it[i])
            }
        })
    }

    private fun prepareView(detailBook: DetailBook) {
        val url = Constant.BASE_URL_IMAGE+detailBook.writerId.userByUser.profilePic+ Constant.API_KEY_IMAGE
        Glide.with(this).load(url).placeholder(R.drawable.ic_writer_icon).into(img_writer)
        tv_writer_name.text = detailBook.writerId.userByUser.name
        tv_writer_username.text = detailBook.writerId.userByUser.username
        tv_book_rate.text = "Rating : " + data?.rateSum.toString()
        tv_book_status.text = "Status : " + detailBook.status
        tv_book_desc.text = detailBook.desc

        relatedBookAdapter = RelatedBookAdapter()
        rv_related.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_related.adapter = relatedBookAdapter
        relatedBookAdapter.addItem(detailBook.relatedBook)

        genres = GenresAdapter()
        rv_genres_detail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_genres_detail.adapter = genres
        genres.addListGenres(detailBook.genres)

        hashtagsAdapter = HashtagsAdapter()
        rv_tags.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_tags.adapter = hashtagsAdapter
        hashtagsAdapter.addHashtags(detailBook.hashtags)

        chapterAdapter = ChapterAdapter()
        rv_chapter_detail.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_chapter_detail.adapter = chapterAdapter
        chapterAdapter.addChapter(detailBook.bookChapter)
    }

}