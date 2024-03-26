package com.prathamngundikere.whatsapp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.prathamngundikere.whatsapp.ui.theme.helvetica
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {

    val chats = BottomTabBarItems(
        title = "Chats",
        selectedIcon = painterResource(id = R.drawable.chat_selected),
        unselectedIcon = painterResource(id = R.drawable.chat_unselected)
    )

    val updates = BottomTabBarItems(
        title = "Updates",
        selectedIcon = painterResource(id = R.drawable.status),
        unselectedIcon = painterResource(id = R.drawable.status)
    )

    val groups = BottomTabBarItems(
        title = "Communities",
        selectedIcon = painterResource(id = R.drawable.groups_selected),
        unselectedIcon = painterResource(id = R.drawable.groups_unselected)
    )

    val calls = BottomTabBarItems(
        title = "Calls",
        selectedIcon = painterResource(id = R.drawable.call_selected),
        unselectedIcon = painterResource(id = R.drawable.call_unselected)
    )

    val bottomTabBarItems = listOf(chats, updates, groups, calls)

    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState {
        bottomTabBarItems.size
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { page ->
                selectedTabIndex = page
            }
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontFamily = helvetica,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = colorResource(id = R.color.green)
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.photo_camera),
                            contentDescription = "Camera",
                            tint = Color.Black
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "Search",
                            tint = Color.Black
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.more_vert),
                            contentDescription = "Menu",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                contentColor = Color.Black,
                containerColor = colorResource(id = R.color.green)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.chat_plus),
                    contentDescription = "Add Chat",
                    tint = Color.Black
                )
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = colorResource(id = R.color.light_green)
            ) {
                bottomTabBarItems.forEachIndexed{ index, bottomTabBarItem ->
                    NavigationBarItem(
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        icon = {
                            BadgedBox(
                                badge = {

                                }
                            ) {
                                Icon(
                                    painter = if (selectedTabIndex == index)
                                    {bottomTabBarItem.selectedIcon}
                                    else {bottomTabBarItem.unselectedIcon},
                                    contentDescription = "Icons"
                                )
                            }
                        },
                        label = {
                            Text(
                                text = bottomTabBarItem.title,
                                fontFamily = helvetica,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorResource(id = R.color.green),
                            selectedTextColor = Color.Black,
                            indicatorColor = colorResource(id = R.color.light_green),
                            unselectedIconColor = Color.Black,
                            unselectedTextColor = Color.Black
                        )
                    )
                }
            }
        }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {page: Int ->
            when(page) {
                0 -> ChatScreen()
                1 -> UpdateScreen()
                2 -> GroupScreen()
                3 -> CallScreen()
            }
        }
    }

}

data class BottomTabBarItems(
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter
)