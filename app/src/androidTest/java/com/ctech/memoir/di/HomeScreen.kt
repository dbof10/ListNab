package com.ctech.memoir.di

import android.view.View
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import org.hamcrest.Matcher
import com.memoir.home.R

class HomeScreen : Screen<HomeScreen>() {


    class ItemProperty(parent: Matcher<View>) : KRecyclerItem<ItemProperty>(parent) {
        val fav: KImageView = KImageView(parent) {
            withId(R.id.ivFav)
        }
    }

    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.rvFeeds)
    }, itemTypeBuilder = {
        itemType(::ItemProperty)
    })
}