package com.grepy.msx.cabaca.ui.detail.viewpager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.ui.detail.DetailBookFragment
import com.grepy.msx.cabaca.ui.detail.ReviewFragment

class ViewPager(private val context: Context, private val book: Book, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragment : Fragment? = null
    private var title = listOf(R.string.book_text, R.string.reviews_text)

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                fragment = DetailBookFragment()
                val bundle = Bundle().apply {
                    this.putParcelable("bookItem", book)
                }
                (fragment as DetailBookFragment).arguments = bundle
            }
            1 -> {
                fragment = ReviewFragment()
                val bundle = Bundle().apply {
                    this.putParcelable("bookReviews", book)
                }
                (fragment as ReviewFragment).arguments = bundle
            }
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(title[position])
    }

}