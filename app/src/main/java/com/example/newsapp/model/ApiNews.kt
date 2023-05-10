package com.example.newsapp.model

data class ApiNews(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)

data class Article(
    var id: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

data class Source(
    val id: Any,
    val name: String
)


fun ApiNews.toNews(): List<News> {
    val newsList = arrayListOf<News>()
    articles.forEach {
        newsList.add(
            News(
                id = it.id,
                title = it.title ?: "DEFAULT TITLE",
                imageUrl = it.urlToImage ?: "https://www.google.com/"
            )
        )
    }
//    articles.map { News(it.id, it.title ?: "", it.urlToImage ?: "") }
    return newsList
}