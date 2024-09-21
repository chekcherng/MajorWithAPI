package com.example.yujie.mo



data class Cin(
    val name: String,
    val movies: List<MovieMajor>
)

data class MovieMajor(
    val format: String,
    val language: List<String>,
    val showTimes: List<String>
)
val cin = listOf(
    Cin(
        name = "Major Aeon Mall Phnom Penh",
        movies = listOf(
            MovieMajor(
                format = "2D",
                language = listOf("ENG", "KH"),
                showTimes = listOf("11:15 PM","1:00 PM","03:50 PM", "07:40 PM", "09:45 PM")
            )

        )

    ),
    Cin(
        name = "MAJOR CINEPLEX AEON SEN SOK",
        movies = listOf(
            MovieMajor(
                format = "2D",
                language = listOf("ENG", "KH"),
                showTimes = listOf("10:00 AM", "01:50 PM", "03:50 PM","07:50 PM")
            )
        )
    ),

    Cin(
        name = "MAJOR CINEPLEX AEON MEAN CHEY",
        movies = listOf(
            MovieMajor(
                format = "2D",
                language = listOf("ENG", "KH"),
                showTimes = listOf("10:00 AM", "01:50 PM", "03:50 PM","07:50 PM")
            )
        )
    ),
    Cin(
        name = "MAJOR CINEPLEX SORYA",
        movies = listOf(
            MovieMajor(
                format = "2D",
                language = listOf("ENG", "KH"),
                showTimes = listOf("10:00 AM", "01:50 PM", "03:50 PM","07:50 PM")
            )
        )
    ),
    Cin(
        name = "MAJOR PLATINUM SIEM REAP",
        movies = listOf(
            MovieMajor(
                format = "2D",
                language = listOf("ENG", "KH"),
                showTimes = listOf("10:00 AM", "01:50 PM", "03:50 PM","07:50 PM")
            )
        )
    ),
    Cin(
        name = "MAJOR CINEPLEX BIG C POIPET",
        movies = listOf(
            MovieMajor(
                format = "2D",
                language = listOf("ENG", "KH"),
                showTimes = listOf("11:00 AM", "12:50 PM", "01:50 PM","04:50 PM")
            )
        )
    ),


    )
