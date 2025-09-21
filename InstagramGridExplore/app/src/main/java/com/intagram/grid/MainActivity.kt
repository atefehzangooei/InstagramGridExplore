
package com.intagram.grid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.intagram.grid.ui.theme.InstagramGridExploreTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstagramGridExploreTheme {
                InstagramExplore()
            }
        }
    }
}

@Composable
fun StaggeredExplore() {

    val data = getData()

    RightToLeftLayout {

        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalItemSpacing = 5.dp
        ) {
            items(data) { image ->
                //for image URL use AsyncImage
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(Random.nextInt(100,300).dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(image)

                )
            }
        }
    }
}

@Composable
fun InstagramExplore() {

    val data = getData()

    RightToLeftLayout {

        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = StaggeredGridCells.Fixed(3),
        ) {
            itemsIndexed(data) { index, image ->
                //for image URL use AsyncImage
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(image)
                    .border(width = 1.dp, color = Color.White)
                    .aspectRatio(
                        if (index % 10 in listOf(0, 7)) 0.5f else 1f
                    ))
            }
        }
    }
}

@Composable
fun HybridStaggeredExplore() {

    val data = getData()

    RightToLeftLayout {

        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = StaggeredGridCells.Fixed(3),
        ) {
            itemsIndexed(items =data,
                span = { index, _ ->
                    if (index % 11 == 0) {
                        StaggeredGridItemSpan.FullLine
                    }
                    else{
                        StaggeredGridItemSpan.SingleLane
                    }
                }
            ) { index, image ->
                //for image URL use AsyncImage
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(image)
                    .border(width = 1.dp, color = Color.White)
                    .aspectRatio(
                        if (checkIndex(index)) 0.5f else 1f
                    ))
            }
        }
    }
}


fun checkIndex(index : Int) : Boolean
{
    return when {
        (index - 1) % 11 == 0 -> true
        (index - 8) % 11 == 0 -> true
        else -> false
    }
}

fun getData() : List<Color>{

    val colors = listOf(
        0xFFFF99AA,
        0xFFFFE066,
        0xFF6EC5FF,
        0xFF78D478,
        0xFFB080FF,
        0xFFFF9E6B,
        0xFFFF6F91)


    return (1..20).map { Color(colors.random()) }
}


@Composable
fun RightToLeftLayout(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalLayoutDirection
                provides LayoutDirection.Rtl
    ) {
        content()
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InstagramGridExploreTheme {
    }
}