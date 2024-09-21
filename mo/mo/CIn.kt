package com.example.yujie.mo

import com.example.yujie.model.CinemaItem

data class MajorItem(
    val title: String,
    val Address: String,
)
val majorItems = listOf(
    MajorItem(
        title = "Major Aeon Mall Phnom Penh",
        Address = "#132,Street Samdach Sothearos , Sangkat Tonle Bassac, Phnom Penh(Aeon1)"
    ),
    MajorItem(
        title = "MAJOR CINEPLEX AEON SEN SOK",
        Address = "Street 1003, Phnom Penh (Aeon Mall Sen Sok)"
    ),
    MajorItem(
        title = "MAJOR CINEPLEX AEON MEAN CHEY",
        Address = "Phum Prek Talong 3, Dangkat Chak Angre Krom, Khan Mean Chey, Phnom Penh"
    ),
    MajorItem(
        title = "MAJOR CINEPLEX SORYA",
        Address = "#13-61, Street 63, Sangkat Phsar Thmei 1,\n" +
                "Khan Daun Penh Phnom Penh (Sorya Center Point)"
    ),  MajorItem(
        title = "MAJOR PLATINUM SIEM REAP",
        Address = "MAJOR PLATINUM SIEM REAP\n" +
                "Stung Thmey Village Svay Dongkom District\n" +
                "Siem Reap City Siem Reap Province"
    ),  MajorItem(
        title = "MAJOR CINEPLEX BIG C POIPET",
        Address = "Kbal Spean 1 Village, Poipet commune, Poipet district, Banteay Meanchey province (Big C Poipet)"
    ),



    )