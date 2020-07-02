package com.grepy.msx.cabaca.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class BookResponse(
    @SerializedName("success") val success : Boolean?,
    @SerializedName("result") val result : MutableList<Book>
)

data class CategoryResponse(
    @SerializedName("resource") val resource : MutableList<Category>
)

data class DetailBookResponse(
    @SerializedName("success") val status: Boolean?,
    @SerializedName("result") val result : DetailBook
)

data class WriterResponse(
    @SerializedName("result") val result : WriterProfile?
)

@Parcelize
data class Book(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val titleBook : String,
    @SerializedName("writer_id") val writerId : Int,
    @SerializedName("cover_url") val coverUrl : String,
    @SerializedName("created_at") val createdAt : String,
    @SerializedName("status") val status : String,
    @SerializedName("category") val category : String?,
    @SerializedName("Writer_by_writer_id") val writerByWriterId : Writer,
    @SerializedName("book_id") val bookId : Int,
    @SerializedName("isNew") val isNew : Boolean?,
    @SerializedName("view_count") val viewCount : Int,
    @SerializedName("rate_sum") val rateSum : Double,
    @SerializedName("chapter_count") val chapterCount : Int
) : Parcelable

@Parcelize
data class DetailBook(
    @SerializedName("id") val bookId : Int,
    @SerializedName("title") val title : String,
    @SerializedName("synopsis") val synopsis : String,
    @SerializedName("cover_url") val coverUrl : String,
    @SerializedName("status") val status : String,
    @SerializedName("desc") val desc : String,
    @SerializedName("Writer_by_writer_id") val writerId : Writer?,
    @SerializedName("hashtags") val hashtags : MutableList<Hashtags>,
    @SerializedName("genres") val genres : MutableList<Genres>,
    @SerializedName("Related_by_book") val relatedBook : MutableList<RelatedBook>,
    @SerializedName("BookChapter_by_book_id") val bookChapter : MutableList<BookChapter>,
    @SerializedName("reviews") val reviews : MutableList<Reviews>
) : Parcelable

@Parcelize
data class Reviews(
    @SerializedName("id") val id : Int,
    @SerializedName("rating") val rating : Int,
    @SerializedName("review") val review : String,
    @SerializedName("User_by_reviewer_id") val reviewer : Reviewer
) : Parcelable

@Parcelize
data class Reviewer(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("email") val email : String,
    @SerializedName("photo_url") val photo : String
) : Parcelable

@Parcelize
data class Genres(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String
) : Parcelable

@Parcelize
data class Writer(
    @SerializedName("id") val id : Int,
    @SerializedName("user_id") val user_id : Int,
    @SerializedName("status") val status : String?,
    @SerializedName("User_by_user_id") val userByUser : User
) : Parcelable

@Parcelize
data class WriterProfile(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("username") val username : String?,
    @SerializedName("photo_url") val photo : String?,
    @SerializedName("email") val email : String,
    @SerializedName("deskripsi") val desc : String?,
    @SerializedName("karya") val karya : MutableList<Karya>

) : Parcelable

@Parcelize
data class Karya(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("status") val status: String,
    @SerializedName("cover_url") val cover : String,
    @SerializedName("rate_sum") val rateSum: Double
) : Parcelable

@Parcelize
data class User(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("username") val username : String?,
    @SerializedName("photo_url") val profilePic : String?
) : Parcelable

@Parcelize
data class Category(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("icon_url") val icons : String?,
    @SerializedName("count") val count : Int
) : Parcelable

@Parcelize
data class RelatedBook(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("cover_url") val coverUrl : String
) : Parcelable

@Parcelize
data class BookChapter(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title: String,
    @SerializedName("chapter_sequence") val chapterCount: Int
) : Parcelable

@Parcelize
data class Hashtags(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
) : Parcelable