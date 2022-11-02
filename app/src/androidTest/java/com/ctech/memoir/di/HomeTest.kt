package com.ctech.memoir.di

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.ActivityAction
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.memoir.home.R
import com.memoir.home.ui.HomeActivity
import io.github.kakaocup.kakao.screen.Screen.Companion.idle
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class HomeTest {



    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
    }
    @Test
    fun loadList() {
        onScreen<HomeScreen> {

            idle(5000L) // wait for APIs
            recycler {
                firstChild<HomeScreen.ItemProperty> {
                    isVisible()
                }

                scrollToEnd()
            }
        }

    }

    @Test
    fun favoriteClick() {
        onScreen<HomeScreen> {

            idle(5000L) // wait for APIs
            recycler {
                firstChild<HomeScreen.ItemProperty> {
                    isVisible()
                    fav {
                        hasDrawable(R.drawable.ic_favorite_empty)
                    }
                    fav.click()
                }
                idle(500L)
                firstChild<HomeScreen.ItemProperty> {
                    fav {
                        hasDrawable(R.drawable.ic_favorite_full)
                    }
                }
            }
        }

    }
}