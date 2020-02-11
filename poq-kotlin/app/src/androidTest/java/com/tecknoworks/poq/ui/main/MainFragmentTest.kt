package com.tecknoworks.poq.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.tecknoworks.poq.*
import com.tecknoworks.poq.api.RepoRequest
import com.tecknoworks.poq.api.model.RepositoryDTO
import com.tecknoworks.poq.data.RepositoriesViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Mihai Ionescu on 2020-02-03.
 */
@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    private lateinit var app: PoqApplication

    @Inject
    lateinit var viewModel: RepositoriesViewModel
    @Inject
    lateinit var repoRequest: RepoRequest

    @get:Rule
    val testRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        // Context of the app under test.
        app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as PoqApplication
        val component = DaggerAndroidTestAppComponent.builder()
            .apiModule(AndroidTestApiModule())
            .build()
        component.androidTestRepositoriesComponent().build().inject(this)
        app.poqApplicationComponent = component
    }

    @Test
    fun errorIOException() {

        // given

        coEvery { repoRequest.getRepos() } coAnswers {
            delay(2000)
            throw IOException("Mock exception")
        }

        // when

        testRule.launchActivity(null)

        // then

        runBlocking { delay(3000) }

        onView(withId(R.id.errorView))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.retryButton))
            .perform(click())
        onView(withId(R.id.errorView))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))

    }

    @Test
    fun error404() {

        // given

        coEvery { repoRequest.getRepos() } coAnswers {
            delay(2000)
            Response.error(404, null)
        }

        // when

        testRule.launchActivity(null)

        // then

        runBlocking { delay(3000) }

        onView(withId(R.id.errorView))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.retryButton))
            .perform(click())
        onView(withId(R.id.errorView))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))

    }

    @Test
    fun responseOk() {

        // given
        val repositoryMock = mockk<RepositoryDTO>{
            this.name = "test name"
            this.description = "test description"
        }
        coEvery { repoRequest.getRepos() } returns Response.success(listOf(repositoryMock))

        // when

        testRule.launchActivity(null)

        // then

        runBlocking { delay(3000) }

        onView(withId(R.id.errorView))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))

        val rv = testRule.activity.findViewById<RecyclerView>(R.id.repositoriesView)
        assertNotNull(rv)
        assertNotNull(rv.adapter)
        assertEquals(1, rv.adapter!!.itemCount)

    }

}