package com.grepy.msx.cabaca.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.grepy.msx.cabaca.R
import com.grepy.msx.cabaca.model.Book
import com.grepy.msx.cabaca.model.Category
import com.grepy.msx.cabaca.ui.detail.DetailActivity
import com.grepy.msx.cabaca.utils.CategoryBookHelper
import com.grepy.msx.cabaca.utils.ItemClickedHelper
import com.grepy.msx.cabaca.utils.ResultResponse
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var navController : NavController
    private lateinit var homeViewModel : HomeViewModel
    private lateinit var categoryItemAdapter : CategoryItemAdapter
    private lateinit var newBookAdapter: NewBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        navController = Navigation.findNavController(view)
        view.let {
            getCategoryRv(it)
            getNewBookRv(it)
        }
    }


    override fun onResume() {
        super.onResume()
        shimmer_category.startShimmer()
        shimmer_new_book.startShimmer()
    }

    override fun onPause() {
        shimmer_category.stopShimmer()
        shimmer_new_book.stopShimmer()
        super.onPause()
    }

    private fun getCategoryRv(view: View) {
        categoryItemAdapter = CategoryItemAdapter()
        rv_category.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false)
        rv_category.adapter = categoryItemAdapter
        homeViewModel.getCategory().observe(viewLifecycleOwner, Observer { item ->
            when(item.status) {
             ResultResponse.Status.SUCCESS -> {
                 try {
                     item.data?.resource?.let {
                         categoryItemAdapter.addItems(it)
                     }
                     shimmer_category.stopShimmer()
                     shimmer_category.visibility = View.GONE
                 }catch (e : Exception) {
                     Log.e("Error", e.message.toString())
                 }
             }
                ResultResponse.Status.LOADING -> toast(view.context, "Loading")
                ResultResponse.Status.ERROR -> toast(view.context, "RTO")
            }
        })

        categoryItemAdapter.itemCategoryClicked(object  : CategoryBookHelper{
            override fun itemCategoryClicked(category: Category) {
                sendToCategoryItem(category)
            }

        })
    }

    private fun getNewBookRv(view: View) {
        newBookAdapter = NewBookAdapter()
        rv_book_home.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_book_home.adapter = newBookAdapter
        homeViewModel.getNewBook().observe(viewLifecycleOwner, Observer {item ->
            when(item.status) {
                ResultResponse.Status.SUCCESS -> {
                    item.data?.result?.let {
                        newBookAdapter.addBook(it)
                        shimmer_new_book.stopShimmer()
                        shimmer_new_book.visibility = View.GONE
                    }
                }
                ResultResponse.Status.LOADING -> toast(view.context, "Loading")
                ResultResponse.Status.ERROR -> toast(view.context, "RTO")
            }
        })

        newBookAdapter.onItemClicked(object : ItemClickedHelper{
            override fun itemClickedNewBook(book: Book) {
                sendToDetail(book)
            }
        })
    }

    private fun sendToDetail(book: Book) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.NEW_BOOK, book)
        startActivity(intent)
    }

    private fun sendToCategoryItem(category: Category) {
        val action = HomeFragmentDirections.actionHomeFragmentToCategoryActivity(category)
        navController.navigate(action)
    }

    private fun toast(context: Context, msg : String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}